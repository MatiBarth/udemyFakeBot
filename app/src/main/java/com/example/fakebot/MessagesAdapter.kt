package com.example.fakebot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fakebot.databinding.FbListItemBinding

class MessagesAdapter : ListAdapter<Messages, MessagesAdapter.MessagesHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Messages>() {
        override fun areItemsTheSame(oldItem: Messages, newItem: Messages): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Messages, newItem: Messages): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessagesAdapter.MessagesHolder {
        val binding = FbListItemBinding.inflate(LayoutInflater.from(parent.context))
        return MessagesHolder(binding)
    }

    override fun onBindViewHolder(holder: MessagesAdapter.MessagesHolder, position: Int) {
        val message = getItem(position)
        holder.bind(message)
    }

    inner class MessagesHolder(private val binding: FbListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(messages: Messages) {
            binding.fbMessage.text = messages.message.toString()
        }
    }
}