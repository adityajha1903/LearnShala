package com.example.adi.learnshala.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.example.adi.learnshala.R
import com.example.adi.learnshala.adapters.ViewPagerAdapter
import com.example.adi.learnshala.databinding.ActivityMain2Binding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity2 : AppCompatActivity() {

    private var binding: ActivityMain2Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBar)

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding?.viewPager?.adapter = adapter
        TabLayoutMediator(binding?.tab!!, binding?.viewPager!!) { tab, position ->
            when (position) {
                0 -> tab.icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_dashboard_24, null)
                1 -> tab.icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_format_list_bulleted_24, null)
                else -> tab.icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_person_24, null)
            }
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}