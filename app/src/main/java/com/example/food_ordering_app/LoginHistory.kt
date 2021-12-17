package com.example.food_ordering_app

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date

class LoginHistory : AppCompatActivity() {
    private lateinit var session: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_history)
        val string=intent.getStringExtra("string")
        val prof=findViewById<TextView>(R.id.mem)



        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFFFF")))
        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setCustomView(R.layout.action_bar_layout_profile)
        val View: View? =supportActionBar?.getCustomView()
        View?.findViewById<TextView>(R.id.cen)!!.text="Login History"

        val apiclient=application as ApiClient
        session=SessionManager(this)
        var intent= Intent(this,User_Login::class.java)

        var token=session.fetchAuthToken()
        val dat ="${session.fetch_member()}"

        val date= Date(dat.toLong())
        prof.text="Memeber Since : ${date.toGMTString().removeRange(12,date.toGMTString().length)}"

        val items:MutableList<LoginData> = mutableListOf<LoginData>()
        if(session.fetchAuthToken()!=null){
            CoroutineScope(Dispatchers.IO).launch{
                val result=apiclient.apiService.GetLoginhistory("Bearer "+token)
                var i=0
                if(result.isSuccessful){
                    while(i<result.body()?.loginEntries!!.size){
                        items.add(result.body()?.loginEntries!![i])
                        i+=1
                    }
                }
                else{
                    startActivity(intent)
                }
                withContext(Dispatchers.Main){
                    val recycle=findViewById<RecyclerView>(R.id.recycle2)
                    recycle.adapter=LoginAdatpterClass(items)
                    recycle.layoutManager= LinearLayoutManager(this@LoginHistory)
                }

            }
        }
        else
            startActivity(intent)
    }
}
