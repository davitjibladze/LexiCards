package com.lexicards.ui.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lexicards.databinding.ItemCardBinding
import com.lexicards.model.Card
import java.text.SimpleDateFormat
import java.util.*

class CardAdapter(
    private val onDeleteClick: (Card) -> Unit
) : ListAdapter<Card, CardAdapter.CardViewHolder>(DiffCallback) {

    inner class CardViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
            binding.tvForeign.text = card.foreignWord
            binding.tvGeorgian.text = card.georgianWord
            binding.tvInterval.text = "Next in ${card.interval}d"
            binding.btnDelete.setOnClickListener { onDeleteClick(card) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(a: Card, b: Card) = a.id == b.id
        override fun areContentsTheSame(a: Card, b: Card) = a == b
    }
}
