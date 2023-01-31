package com.example.adi.learnshala.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.adi.learnshala.Constants
import com.example.adi.learnshala.activities.MainActivity
import com.example.adi.learnshala.databinding.FragmentProfileBinding
import com.example.adi.learnshala.db.WorkshopDatabase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null

    override fun onResume() {
        getData()
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        getData()

        binding?.logOutBtn?.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val i = Intent(activity, MainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            deleteUserName()
            startActivity(i)
            activity?.finish()
        }

        return binding?.root
    }

    private fun deleteUserName() {
        val pref = context?.applicationContext?.getSharedPreferences(Constants.WORKSHOP_ADDITION, AppCompatActivity.MODE_PRIVATE)
        val prefEditor = pref?.edit()
        prefEditor?.putString(Constants.USER_NAME, "Guest")
        prefEditor?.apply()
    }

    private fun getData() {
        val workshopDb = Room.databaseBuilder(context?.applicationContext!!, WorkshopDatabase::class.java, "workshop_database").build()
        val workshopDao = workshopDb.workshopDao()
        lifecycleScope.launch{
            var registeredWorkshop = 0
            workshopDao.fetchAllPlaces().collect{
                for (workshops in it) {
                    if (workshops.registered == Constants.WORKSHOP_REGISTERED) {
                        registeredWorkshop++
                    }
                }
                setUpUi(registeredWorkshop)
            }
        }
    }

    private fun setUpUi(registeredWorkshop: Int) {
        binding?.registeredTV?.text = registeredWorkshop.toString()

        val pref = context?.applicationContext?.getSharedPreferences(Constants.WORKSHOP_ADDITION, AppCompatActivity.MODE_PRIVATE)
        val name = pref?.getString(Constants.USER_NAME, "Guest")
        binding?.nameTV?.text = name

        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val email = currentUser.email
            binding?.emailTV?.text = email
            binding?.logOutBtn?.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}