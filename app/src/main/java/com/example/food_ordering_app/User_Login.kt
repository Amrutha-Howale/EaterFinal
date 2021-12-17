package com.example.food_ordering_app

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class User_Login : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        val email = findViewById<TextInputLayout>(R.id.userEmail)
        val password = findViewById<TextInputLayout>(R.id.userPassword)
        val button = findViewById<Button>(R.id.button)
        val regbutton = findViewById<Button>(R.id.button2)

        val errorMessage = findViewById<TextView>(R.id.errorView)
        val registernav = findViewById<Button>(R.id.button2)

        val inputemailname = MutableLiveData<String>()

        val inputPassword = MutableLiveData<String>()

        val profile=findViewById<TextView>(R.id.DisplayUserEmail)
        val intent = Intent(this, MainActivity2::class.java)
        val intent1 = Intent(this, MainActivity::class.java)



        //apiClient = ApiClient()
        val apiClient=application as ApiClient
        var dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.loading_screen)
        dialog.setCanceledOnTouchOutside(false)

       sessionManager = SessionManager(this,)

        button.setOnClickListener {
            dialog.show()
        CoroutineScope(Dispatchers.IO).launch {

           var result=apiClient.apiService.login(LoginRequest(email.editText?.text?.toString(),password.editText?.text?.toString()))

            withContext(Dispatchers.Main) {
                if(result.isSuccessful){
                    dialog.dismiss()
                    sessionManager.saveAuthToken(result.body()?.token)
                    sessionManager.saveUserID(result.body()?.email)
                    sessionManager.save_member_since(result.body()!!.memberSince)
                    intent.putExtra("string",result.body()?.token)
                    startActivity(intent)

                    Toast.makeText(this@User_Login,"Login successfull",Toast.LENGTH_LONG).show()
                }
                else
                    Toast.makeText(this@User_Login,"Invalid Credential",Toast.LENGTH_LONG).show()
               }
        }
    }

        regbutton.setOnClickListener {
            startActivity(intent1)
        }
    }


    }

