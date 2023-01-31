package com.example.adi.learnshala.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.adi.learnshala.Constants
import com.example.adi.learnshala.Constants.FIRST_START
import com.example.adi.learnshala.Constants.WORKSHOP_ADDITION
import com.example.adi.learnshala.R
import com.example.adi.learnshala.databinding.ActivityMainBinding
import com.example.adi.learnshala.db.WorkshopDatabase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private var auth: FirebaseAuth? = null
    public override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
        val currentUser = auth?.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, MainActivity2::class.java))
            finish()
        }

        val pref = getSharedPreferences(WORKSHOP_ADDITION, MODE_PRIVATE)
        val firstStart = pref.getBoolean(FIRST_START, true)
        if (firstStart) {
            val workshopDb = Room.databaseBuilder(applicationContext, WorkshopDatabase::class.java, "workshop_database").build()
            val workshopDao = workshopDb.workshopDao()
            lifecycleScope.launch {
                workshopDao.insertWorkshopList(Constants.getAllWorkshops())
                val pref2 = getSharedPreferences(WORKSHOP_ADDITION, MODE_PRIVATE)
                val prefEditor = pref2.edit()
                prefEditor.putBoolean(FIRST_START, false)
                prefEditor.apply()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (Build.VERSION.SDK_INT >= 33) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {
                finish()
            }
        } else {
            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    finish()
                }
            })
        }

        binding?.signUpBtn?.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding?.logInBtn?.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }

        binding?.skipBtn?.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}