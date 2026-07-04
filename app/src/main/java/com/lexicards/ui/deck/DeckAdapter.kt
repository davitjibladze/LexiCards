package com.lexicards.ui.deck

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lexicards.databinding.ItemDeckBinding
import com.lexicards.model.Deck

class DeckAdapter(
    private val onDeckClick: (Deck) -> Unit,
    private val onDeckLongClick: (Deck) -> Unit
) : ListAdapter<Deck, DeckAdapter.DeckViewHolder>(DiffCallback) {

    inner class DeckViewHolder(private val binding: ItemDeckBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(deck: Deck) {
            binding.tvDeckName.text = deck.name
            binding.tvDeckDesc.text = deck.description
            binding.tvCardCount.text = "${deck.cardCount} cards"
            binding.tvLanguage.text = deck.foreignLanguage

            binding.root.setOnClickListener { onDeckClick(deck) }
            binding.root.setOnLongClickListener {
                onDeckLongClick(deck)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        val binding = ItemDeckBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeckViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Deck>() {
        override fun areItemsTheSame(a: Deck, b: Deck) = a.id == b.id
        override fun areContentsTheSame(a: Deck, b: Deck) = a == b
    }
}
