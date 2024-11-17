package com.example.noteapp.ui.Activity.Onboard.Adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.noteapp.ui.Activity.Onboard.Fragment.OnBoardFragment
import com.example.noteapp.ui.Activity.Onboard.Fragment.OnBoardPagerFragment

class OnBoardAdapter(fragmentActivity: OnBoardFragment) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3 // Количество экранов

    override fun createFragment(position: Int): Fragment {
        val fragment = OnBoardPagerFragment()
        fragment.arguments = Bundle().apply {
            putInt(OnBoardPagerFragment.ARG_ONBOARD_POSITION, position)
        }
        return fragment
    }
}
