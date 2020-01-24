package com.example.vatron_navigation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Lets make Driving fun again\n\n" +
                "Click the camera icon below to start detection in real time"

    }
    val text: LiveData<String> = _text
}