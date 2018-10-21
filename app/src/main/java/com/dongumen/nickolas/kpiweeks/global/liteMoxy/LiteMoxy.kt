package com.dongumen.nickolas.kpiweeks.global.liteMoxy

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.koin.standalone.KoinComponent

abstract class MvpPresenter<T : Any> : KoinComponent {

    val view: MvpView<T> = BufferView()

    open fun attach(view: MvpView<T>) = (this.view as BufferView).attach(view)
    open fun detach() = (view as BufferView).detach()

    private class BufferView<T : Any> : MvpView<T> {

        private val buffer = ArrayList<T>()
        private var view: MvpView<T>? = null

        override fun update(event: T) {
            buffer.removeAll { it::class == event::class }
            if (view == null) buffer.add(event)
            launch(UI) { view?.update(event) }
        }

        fun attach(view: MvpView<T>) {
            buffer.forEach(view::update)
            buffer.clear()
            this.view = view
        }

        fun detach() {
            view = null
        }
    }
}

interface MvpView<T> {
    fun update(event: T)
}

abstract class MvpFragment<T : Any> : Fragment(), MvpView<T> {

    abstract val presenter: MvpPresenter<T>

    override fun onResume() {
        super.onResume()
        retainInstance = true
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }
}

abstract class MvpAppCompatActivity<T : Any> : AppCompatActivity(), MvpView<T> {

    abstract val presenter: MvpPresenter<T>

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }
}
