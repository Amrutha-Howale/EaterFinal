package com.example.food_ordering_app

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import java.sql.Date
import java.text.SimpleDateFormat
import kotlin.time.days

class userProfile : AppCompatActivity() {
    lateinit var session:SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        val updateemail=findViewById<Button>(R.id.changeEmail)
        val prof=findViewById<TextView>(R.id.member)
        val emailId= findViewById<TextView>(R.id.userEmailDis)
        val dele=findViewById<Button>(R.id.deleteUser)
        val reviewHistory=findViewById<Button>(R.id.HistoryReview)
        val logout=findViewById<Button>(R.id.logOut)
        session= SessionManager(this)

        val sdf= SimpleDateFormat("dd/mm/yyyy")


        val dat ="${session.fetch_member()}"

       val date= Date(dat.toLong())
        prof.text="Memeber Since : ${date.toGMTString().removeRange(12,date.toGMTString().length)}"


        emailId.text="${session.FetchUserID()}"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFFFF")))
        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setCustomView(R.layout.action_bar_layout_profile)
        val View: View? =supportActionBar?.getCustomView()
        View?.findViewById<TextView>(R.id.cen)!!.text="Profile"
        updateemail.setOnClickListener {
            val intent=Intent(this,update_email::class.java)
            startActivity(intent)
        }
        reviewHistory.setOnClickListener{
            val intent1=Intent(this,LoginHistory::class.java)
            startActivity(intent1)
        }
        dele.setOnClickListener {
            val intent2=Intent(this,DeleteAccount::class.java)
            startActivity(intent2)
        }
        logout.setOnClickListener {
            val intent3=Intent(this,User_Login::class.java)
            startActivity(intent3)
        }
    }
}