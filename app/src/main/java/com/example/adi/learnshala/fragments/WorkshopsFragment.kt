package com.example.adi.learnshala.fragments

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.adi.learnshala.Constants
import com.example.adi.learnshala.activities.LogInActivity
import com.example.adi.learnshala.adapters.WorkshopRecyclerViewAdapter
import com.example.adi.learnshala.databinding.FragmentWorkshopsBinding
import com.example.adi.learnshala.databinding.RegistrationDialogBinding
import com.example.adi.learnshala.db.WorkshopDao
import com.example.adi.learnshala.db.WorkshopDatabase
import com.example.adi.learnshala.db.WorkshopEntity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class WorkshopsFragment : Fragment() {

    private var binding: FragmentWorkshopsBinding? = null

    private var auth: FirebaseAuth? = null
    private var userAvailable = false

    private var activityContext: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkshopsBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth?.currentUser
        userAvailable = currentUser != null

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
                    if (workshops.registered == Constants.WORKSHOP_NOT_REGISTERED) {
                        availableList.add(workshops)
                    }
                }
                setUpRecyclerView(availableList, workshopDao)
            }
        }
    }

    private fun setUpRecyclerView(list: ArrayList<WorkshopEntity>, workshopDao: WorkshopDao) {
        val adapter = WorkshopRecyclerViewAdapter(context?.applicationContext!!, list, false) { ws ->
            if (userAvailable) {
                val dialog = Dialog(activityContext!!)
                val dialogBinding = RegistrationDialogBinding.inflate(layoutInflater)
                dialog.setContentView(dialogBinding.root)
                dialogBinding.btnYes.setOnClickListener {
                    lifecycleScope.launch {
                        val newWorkshop = WorkshopEntity(ws.id, ws.title, ws.location, ws.from, ws.to, ws.organisation, Constants.WORKSHOP_REGISTERED, ws.description)
                        workshopDao.update(newWorkshop)

                        getAvailableWorkshops()
                    }
                    dialog.dismiss()
                }
                dialogBinding.btnNo.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.show()
            } else {
                startActivity(Intent(context, LogInActivity::class.java))
            }
        }
        binding?.workshopRv?.adapter = adapter
        binding?.workshopRv?.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}