package com.lexicards.ui.study

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.lexicards.R
import com.lexicards.databinding.FragmentStudyBinding
import com.lexicards.model.Language
import com.lexicards.utils.SpacedRepetition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StudyFragment : Fragment() {

    private var _binding: FragmentStudyBinding? = null
    private val binding get() = _binding!!
    private val vm: StudyViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, saved: Bundle?): View {
        _binding = FragmentStudyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Study mode toggle
        binding.rgPromptLang.setOnCheckedChangeListener { _, _ -> updateStudyMode() }
        binding.rgAnswerLang.setOnCheckedChangeListener { _, _ -> updateStudyMode() }

        binding.btnSubmit.setOnClickListener { submitAnswer() }

        binding.etAnswer.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                submitAnswer(); true
            } else false
        }

        binding.btnNext.setOnClickListener {
            binding.etAnswer.text?.clear()
            vm.nextCard()
        }

        binding.btnFinish.setOnClickListener {
            findNavController().popBackStack()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.uiState.collect { state -> render(state) }
            }
        }
    }

    private fun updateStudyMode() {
        val prompt = if (binding.rbPromptForeign.isChecked) Language.FOREIGN else Language.GEORGIAN
        val answer = if (binding.rbAnswerGeorgian.isChecked) Language.GEORGIAN else Language.FOREIGN
        vm.setStudyMode(prompt, answer)
    }

    private fun submitAnswer() {
        val input = binding.etAnswer.text.toString()
        if (input.isBlank()) return
        vm.submitAnswer(input)
    }

    private fun render(state: StudyUiState) {
        // Session complete screen
        binding.layoutComplete.isVisible = state.sessionComplete
        binding.layoutStudy.isVisible = !state.sessionComplete

        if (state.sessionComplete) {
            binding.tvCompleteCount.text =
                getString(R.string.session_complete, state.reviewedCount)
            return
        }

        // Progress
        binding.tvProgress.text = "${state.reviewedCount} / ${state.totalCards}"
        binding.progressBar.max = state.totalCards
        binding.progressBar.progress = state.reviewedCount

        // Prompt word
        binding.tvPrompt.text = state.promptText

        // Answer area
        val feedbackVisible = state.isAnswerRevealed
        binding.etAnswer.isEnabled = !feedbackVisible
        binding.btnSubmit.isVisible = !feedbackVisible
        binding.layoutFeedback.isVisible = feedbackVisible
        binding.btnNext.isVisible = feedbackVisible

        state.answerFeedback?.let { feedback ->
            when (feedback.result) {
                SpacedRepetition.AnswerResult.CORRECT -> {
                    binding.tvFeedback.text = getString(R.string.correct)
                    binding.tvFeedback.setTextColor(requireContext().getColor(R.color.correct_green))
                }
                SpacedRepetition.AnswerResult.TYPO -> {
                    binding.tvFeedback.text = getString(R.string.almost, feedback.correctAnswer)
                    binding.tvFeedback.setTextColor(requireContext().getColor(R.color.typo_orange))
                }
                SpacedRepetition.AnswerResult.WRONG -> {
                    binding.tvFeedback.text = getString(R.string.wrong, feedback.correctAnswer)
                    binding.tvFeedback.setTextColor(requireContext().getColor(R.color.wrong_red))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
