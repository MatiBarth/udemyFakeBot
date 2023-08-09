package com.example.fakebot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fakebot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.fakeBotRecycler.layoutManager = LinearLayoutManager(this)
        adapter = ChatAdapter(this)
        binding.fakeBotRecycler.adapter = adapter

        viewModel.chatMessageListLiveData.observe(this, Observer { chatMessageList ->
            adapter.submitList(chatMessageList)
            binding.fakeBotRecycler.scrollToPosition(chatMessageList.size - 1)
            binding.chatEmptyView.visibility = if (chatMessageList.isEmpty()) {
                View.VISIBLE

            } else {
                View.GONE
            }
        })

        sendMessage(binding)
    }

    fun sendMessage(binding: ActivityMainBinding) {
        binding.questionInsert.setOnClickListener {
            val messageText = binding.questionEditText.text.toString()
            if (messageText.isEmpty()) {
                Toast.makeText(
                    this,
                    getString(R.string.message_must_not_be_empty),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val chatMessage = ChatMessages(System.currentTimeMillis(), messageText, true)
                viewModel.addMessage(chatMessage)
                viewModel.createResponse()
                binding.questionEditText.setText("")
            }

        }
    }
}