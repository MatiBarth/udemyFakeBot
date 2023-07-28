package com.example.fakebot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fakebot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val messagesList: MutableList<Messages> = mutableListOf<Messages>()
        messagesList.add(Messages("1","Si"))
        messagesList.add(Messages("2","No"))
        messagesList.add(Messages("3","Pregunta de nuevo"))
        messagesList.add(Messages("4","Es muy probable"))
        messagesList.add(Messages("5","No lo creo"))
        messagesList.add(Messages("6","No sé"))
        messagesList.add(Messages("7","Tal vez"))
        messagesList.add(Messages("8","Porqué no?"))

        val adapter = MessagesAdapter()
        binding.fakeBotRecycler.adapter = adapter

    }
}