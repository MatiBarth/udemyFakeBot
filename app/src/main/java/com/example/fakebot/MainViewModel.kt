package com.example.fakebot

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {

    private var _chatMessageListLiveData = MutableLiveData<MutableList<ChatMessages>>()
    val chatMessageListLiveData: LiveData<MutableList<ChatMessages>>
        get() = _chatMessageListLiveData

    private val responses = arrayOf(
        "Si",
        "Pregunta de nuevo",
        "No",
        "Es muy probable",
        "No lo creo",
        "Tal vez",
        "No se"
    )

    private var handler: Handler = Handler()

    init {
        _chatMessageListLiveData.value = mutableListOf()
    }

    fun addMessage(chatMessages: ChatMessages) {
        val mutableList = _chatMessageListLiveData.value!!
        mutableList.add(chatMessages)
        _chatMessageListLiveData.value = mutableList
    }

    fun createResponse() {
        val mutableList = _chatMessageListLiveData.value!!
        val runnable = Runnable {
            val random = Random().nextInt(responses.size)
            val response = responses[random]
            val chatMessage = ChatMessages(System.currentTimeMillis(), response, false)
            mutableList.add(chatMessage)
            _chatMessageListLiveData.value = mutableList
        }
        handler.postDelayed(runnable, 2000)
    }
}