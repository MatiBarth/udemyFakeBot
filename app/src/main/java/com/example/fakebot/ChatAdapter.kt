package com.example.fakebot

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fakebot.databinding.FbListItemBinding

class ChatAdapter(private val context: Context) : ListAdapter<ChatMessages, ChatAdapter.MessagesHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<ChatMessages>() {
        override fun areItemsTheSame(oldItem: ChatMessages, newItem: ChatMessages): Boolean {
            return oldItem.timestamp == newItem.timestamp
        }

        override fun areContentsTheSame(oldItem: ChatMessages, newItem: ChatMessages): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatAdapter.MessagesHolder {
        val binding = FbListItemBinding.inflate(LayoutInflater.from(parent.context))
        return MessagesHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatAdapter.MessagesHolder, position: Int) {
        val message = getItem(position)
        holder.bind(message)
    }

    inner class MessagesHolder(private val binding: FbListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chatMessages: ChatMessages) {
            val chatListMessage = binding.fbMessage
            if (chatMessages.isQuestion){
                chatListMessage.gravity = Gravity.END
                chatListMessage.setBackgroundColor(ContextCompat.getColor(context,R.color.purple_500))
            }else {
                chatListMessage.gravity = Gravity.START
            }
            chatListMessage.text = chatMessages.message
        //    binding.executePendingBindings()
        }
    }
}