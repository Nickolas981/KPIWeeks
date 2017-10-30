package com.example.nickolas.kpiweeks.model.enteties

/**
 * Created by Nickolas on 16.10.2017.
 */
 class Day : Item() {
    var day : String = ""
    var lesssons : MutableMap<String, Lesson> = mutableMapOf()
}