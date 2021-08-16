package com.rafaelmartorano.sicredichallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rafaelmartorano.sicredichallenge.R
import com.rafaelmartorano.sicredichallenge.data.model.EventItem
import com.rafaelmartorano.sicredichallenge.databinding.EventListItemBinding
import java.text.SimpleDateFormat
import java.util.*

class EventAdapter: RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<EventItem>(){
        override fun areItemsTheSame(oldItem: EventItem, newItem: EventItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EventItem, newItem: EventItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = EventListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = differ.currentList[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class EventViewHolder(val binding: EventListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(eventItem: EventItem){
            binding.apply {
                tvEventTitle.text = eventItem.title
                tvEventDescription.text = eventItem.description
                tvEventDate.text = getDate(eventItem.date.toLong())
                tvEventPrice.text = "R$ ${eventItem.price}"
                Glide.with(ivEventImage.context)
                    .load(eventItem.image)
                    .placeholder(R.drawable.defaultimage)
                    .into(ivEventImage)
                eventItem.formattedDate = tvEventDate.text.toString()
            }

            binding.root.setOnClickListener{
                onItemClickListener?.let {
                    it(eventItem)
                }
            }

        }
    }

    private fun getDate(milliSeconds: Long): String{
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds*1000
        return formatter.format(calendar.time)
    }

    private var onItemClickListener: ((EventItem) -> Unit)? = null

    fun setOnClickListener(listener: (EventItem) -> Unit){
        onItemClickListener = listener
    }

}