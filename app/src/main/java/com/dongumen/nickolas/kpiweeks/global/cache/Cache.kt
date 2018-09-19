package com.dongumen.nickolas.kpiweeks.global.cache

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

interface Cache<Key : Any, Value : Any> {
    fun get(key: Key): Deferred<Value?>
    fun set(key: Key, value: Value): Deferred<Unit>

    fun compose(b: Cache<Key, Value>): Cache<Key, Value> {
        return object : Cache<Key, Value> {
            override fun get(key: Key): Deferred<Value?> {
                return async(CommonPool) {
                    this@Cache.get(key).await() ?: let {
                        b.get(key).await()?.apply {
                            this@Cache.set(key, this).await()
                        }
                    }
                }
            }

            override fun set(key: Key, value: Value): Deferred<Unit> {
                return async(CommonPool) {
                    listOf(this@Cache.set(key, value),
                            b.set(key, value)).forEach { it.await() }
                }
            }
        }
    }
}