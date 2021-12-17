package com.example.food_ordering_app

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val APP_PREFERENCES = "mysettings"
        var mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        val button=findViewById<Button>(R.id.button)
        val email=findViewById<TextInputLayout>(R.id.userEmail)
        val password=findViewById<TextInputLayout>(R.id.userPassword)
        val errorMessage=findViewById<TextView>(R.id.errorView)
        //val id=findViewById<TextInputLayout>(R.id.confirmPassword)
        var users= mutableListOf<User>()


        val apiClient=application as ApiClient

        val intent = Intent(this, MainActivity2::class.java)
        var dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.loading_screen)
        dialog.setCanceledOnTouchOutside(false)






        button.setOnClickListener {
            dialog.show()
            val email = email.editText?.text.toString()
            val password = password.editText?.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    var result = apiClient.apiService.register(
                        User(
                            email = email,
                            password = password
                        )
                    )
                    if (result.isSuccessful) {
                      dialog.dismiss()
                        Toast.makeText(applicationContext, "Register OK", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    }

                }
            }

            }
        }

    }
