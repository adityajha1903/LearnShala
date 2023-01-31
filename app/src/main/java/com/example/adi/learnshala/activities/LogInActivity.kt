package com.example.adi.learnshala.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import com.example.adi.learnshala.Constants
import com.example.adi.learnshala.R
import com.example.adi.learnshala.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    private var binding: ActivityLogInBinding? = null

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = FirebaseAuth.getInstance()

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

        binding?.backBtnIB?.setOnClickListener { finish() }

        binding?.signUpBtn?.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

        binding?.getStartedBtn?.setOnClickListener {
            binding?.progressBar?.visibility = View.VISIBLE
            val name: String = binding?.eTName?.text.toString()
            val email: String = binding?.eTEmail?.text.toString()
            val password: String = binding?.eTPassword?.text.toString()

            if (name.isEmpty()) {
                binding?.progressBar?.visibility = View.GONE
                Toast.makeText(this, "Enter the name", Toast.LENGTH_SHORT).show()
            } else if (email.isEmpty()) {
                binding?.progressBar?.visibility = View.GONE
                Toast.makeText(this, "Enter the email id", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                binding?.progressBar?.visibility = View.GONE
                Toast.makeText(this, "Enter the password", Toast.LENGTH_SHORT).show()
            } else {
                auth?.signInWithEmailAndPassword(email, password)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val i = Intent(this, MainActivity2::class.java)
                            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            saveUserName(name)
                            startActivity(i)
                            finish()
                        } else {
                            Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                        binding?.progressBar?.visibility = View.GONE
                    }

            }
        }
    }

    private fun saveUserName(name: String) {
        val pref = getSharedPreferences(Constants.WORKSHOP_ADDITION, MODE_PRIVATE)
        val prefEditor = pref.edit()
        prefEditor.putString(Constants.USER_NAME, name)
        prefEditor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}