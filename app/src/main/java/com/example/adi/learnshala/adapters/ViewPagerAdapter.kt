package com.example.adi.learnshala.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.adi.learnshala.fragments.DashboardFragment
import com.example.adi.learnshala.fragments.ProfileFragment
import com.example.adi.learnshala.fragments.WorkshopsFragment

class ViewPagerAdapter(
    private var fragmentManager: FragmentManager,
    private var lifecycle: Lifecycle
): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> DashboardFragment()
            1 -> WorkshopsFragment()
            else -> ProfileFragment()
        }
    }

}