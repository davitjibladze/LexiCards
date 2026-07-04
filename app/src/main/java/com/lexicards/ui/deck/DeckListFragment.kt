package com.lexicards.ui.deck

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lexicards.R
import com.lexicards.databinding.FragmentDeckListBinding
import com.lexicards.model.Deck
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DeckListFragment : Fragment() {

    private var _binding: FragmentDeckListBinding? = null
    private val binding get() = _binding!!
    private val vm: DeckListViewModel by viewModels()
    private lateinit var adapter: DeckAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        Log.d("LexiCards", "DeckListFragment onCreateView")
        _binding = FragmentDeckListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("LexiCards", "DeckListFragment onViewCreated")

        try {
            adapter = DeckAdapter(
                onDeckClick = { deck -> navigateToDeck(deck) },
                onDeckLongClick = { deck -> confirmDeleteDeck(deck) }
            )

            binding.rvDecks.layoutManager = LinearLayoutManager(requireContext())
            binding.rvDecks.adapter = adapter

            binding.fabAddDeck.setOnClickListener { showCreateDeckDialog() }

            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    vm.decks.collect { decks ->
                        Log.d("LexiCards", "Decks updated: ${decks.size}")
                        adapter.submitList(decks)
                        binding.tvEmpty.visibility =
                            if (decks.isEmpty()) View.VISIBLE else View.GONE
                    }
                }
            }

            vm.syncFromRemote()

        } catch (e: Exception) {
            Log.e("LexiCards", "Error in DeckListFragment", e)
        }
    }

    private fun navigateToDeck(deck: Deck) {
        val action = DeckListFragmentDirections
            .actionDeckListToCardList(deck.id, deck.name)
        findNavController().navigate(action)
    }

    private fun showCreateDeckDialog() {
        val dialogBinding = com.lexicards.databinding.DialogCreateDeckBinding
            .inflate(layoutInflater)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.create_deck)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.create) { _, _ ->
                val name = dialogBinding.etDeckName.text.toString().trim()
                val desc = dialogBinding.etDeckDesc.text.toString().trim()
                val lang = dialogBinding.etLanguage.text.toString().trim().uppercase()
                if (name.isNotEmpty()) {
                    vm.createDeck(name, desc, lang.ifEmpty { "EN" })
                }
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun confirmDeleteDeck(deck: Deck) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.delete_deck)
            .setMessage(getString(R.string.delete_deck_confirm, deck.name))
            .setPositiveButton(R.string.delete) { _, _ -> vm.deleteDeck(deck) }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
