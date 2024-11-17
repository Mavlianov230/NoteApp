package com.example.noteapp.ui.Activity.Onboard.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardBinding
import com.example.noteapp.ui.Activity.App
import com.example.noteapp.ui.Activity.Onboard.Adapter.OnBoardAdapter

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (App.preferences.isOnBoardShown) {
            navigateToMainScreen()
            return
        }

        initialize()
        setupListener()
    }

    private fun initialize() {
        binding.viewpager2.adapter = OnBoardAdapter(this)
        setupDotsIndicator()
    }

    private fun setupDotsIndicator() {
        val dotsCount = 3
        for (i in 0 until dotsCount) {
            val dot = View(context)
            val params = LinearLayout.LayoutParams(32, 32)
            params.setMargins(14, 0, 14, 0)
            dot.layoutParams = params
            dot.setBackgroundResource(R.drawable.dot_inactive)

            dot.setOnClickListener {
                binding.viewpager2.setCurrentItem(i, true)
            }

            binding.dotsIndicator.addView(dot)
        }
    }

    private fun setupListener() = with(binding.viewpager2) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateDotsIndicator(position)

                if (position == 2) {
                    binding.txtSkip.visibility = View.INVISIBLE
                    App.preferences.isOnBoardShown = true
                } else {
                    binding.txtSkip.visibility = View.VISIBLE
                    binding.txtSkip.setOnClickListener {
                        setCurrentItem(position + 1, true)
                    }
                }
            }
        })
    }

    private fun updateDotsIndicator(selectedPosition: Int) {
        val dotsCount = binding.dotsIndicator.childCount
        for (i in 0 until dotsCount) {
            val dot = binding.dotsIndicator.getChildAt(i)
            if (i == selectedPosition) {
                dot.setBackgroundResource(R.drawable.dot_active)
            } else {
                dot.setBackgroundResource(R.drawable.dot_inactive)
            }
        }
    }

    private fun navigateToMainScreen() {

        findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
    }
}
