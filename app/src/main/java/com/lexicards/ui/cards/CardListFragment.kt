package com.lexicards.ui.cards

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.lexicards.R
import com.lexicards.databinding.FragmentCardListBinding
import com.lexicards.model.Card
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CardListFragment : Fragment() {

    private var _binding: FragmentCardListBinding? = null
    private val binding get() = _binding!!

    private val vm: CardListViewModel by viewModels()
    private val args: CardListFragmentArgs by navArgs()
    private lateinit var adapter: CardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, saved: Bundle?): View {
        _binding = FragmentCardListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CardAdapter(
            onDeleteClick = { card -> confirmDelete(card) }
        )

        binding.rvCards.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCards.adapter = adapter

        binding.fabAddCard.setOnClickListener { showAddCardDialog() }

        binding.btnStudy.setOnClickListener {
            if (adapter.itemCount < 10) {
                Snackbar.make(binding.root, R.string.need_ten_cards, Snackbar.LENGTH_SHORT).show()
            } else {
                val action = CardListFragmentDirections
                    .actionCardListToStudy(vm.deckId)
                findNavController().navigate(action)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.cards.collect { cards ->
                    adapter.submitList(cards)
                    binding.tvEmpty.visibility = if (cards.isEmpty()) View.VISIBLE else View.GONE
                    binding.btnStudy.isEnabled = cards.size >= 10
                }
            }
        }
    }

    private fun showAddCardDialog() {
        val dialogBinding = com.lexicards.databinding.DialogAddCardBinding
            .inflate(layoutInflater)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.add_card)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.add) { _, _ ->
                val foreign = dialogBinding.etForeign.text.toString().trim()
                val georgian = dialogBinding.etGeorgian.text.toString().trim()
                if (foreign.isNotEmpty() && georgian.isNotEmpty()) {
                    vm.addCard(foreign, georgian, "EN")
                }
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun confirmDelete(card: Card) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.delete_card)
            .setMessage(R.string.delete_card_confirm)
            .setPositiveButton(R.string.delete) { _, _ -> vm.deleteCard(card) }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
