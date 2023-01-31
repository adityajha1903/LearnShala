package com.example.adi.learnshala.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.adi.learnshala.Constants
import com.example.adi.learnshala.adapters.WorkshopRecyclerViewAdapter
import com.example.adi.learnshala.databinding.FragmentDashboardBinding
import com.example.adi.learnshala.db.WorkshopDatabase
import com.example.adi.learnshala.db.WorkshopEntity
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private var binding: FragmentDashboardBinding? = null

    override fun onResume() {
        getAvailableWorkshops()
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(layoutInflater)

        getAvailableWorkshops()

        return binding?.root
    }

    private fun getAvailableWorkshops() {
        val workshopDb = Room.databaseBuilder(context?.applicationContext!!, WorkshopDatabase::class.java, "workshop_database").build()
        val workshopDao = workshopDb.workshopDao()
        lifecycleScope.launch {
            workshopDao.fetchAllPlaces().collect{
                val availableList = ArrayList<WorkshopEntity>()
                for (workshops in it) {
                    if (workshops.registered == Constants.WORKSHOP_REGISTERED) {
                        availableList.add(workshops)
                    }
                }
                setUpRecyclerView(availableList)
            }
        }
    }

    private fun setUpRecyclerView(availableList: ArrayList<WorkshopEntity>) {
        if (availableList.isEmpty()) {
            binding?.warningTV?.visibility = View.VISIBLE
        } else {
            binding?.warningTV?.visibility = View.GONE
            val adapter = WorkshopRecyclerViewAdapter(context?.applicationContext!!, availableList, true) {
                Log.d(TAG, "setUpRecyclerView: Already Applied")
            }
            binding?.dashboardRV?.adapter = adapter
            binding?.dashboardRV?.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}