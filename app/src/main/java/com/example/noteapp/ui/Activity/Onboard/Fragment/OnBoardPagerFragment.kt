package com.example.noteapp.ui.Activity.Onboard.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieDrawable
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardPagerBinding

class OnBoardPagerFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        val position = requireArguments().getInt(ARG_ONBOARD_POSITION)
        when (position) {
            0 -> {
                txtTitle.text = "Удобство"
                txtBody.text = "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно."
                lottieView.setAnimation("Animation.json")
                btnNext.visibility = View.GONE
            }
            1 -> {
                txtTitle.text = "Организация"
                txtBody.text = "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
                lottieView.setAnimation("Animation2.json")
                btnNext.visibility = View.GONE
            }
            2 -> {
                txtTitle.text = "Синхронизация"
                txtBody.text = "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
                lottieView.setAnimation("Animation3.json")
                btnNext.setOnClickListener {
                    findNavController().navigate(R.id.signInFragment)
                }
                btnNext.visibility = View.VISIBLE
            }
        }
        lottieView.repeatCount = LottieDrawable.INFINITE
        lottieView.playAnimation()
    }

    companion object {
        const val ARG_ONBOARD_POSITION = "onBoard"
    }
}
