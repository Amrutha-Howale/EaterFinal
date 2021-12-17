package com.example.food_ordering_app

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrdersPage : AppCompatActivity() {
    lateinit var session:SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders_page)
        val img=findViewById<ImageView>(R.id.images)
        val type=findViewById<TextView>(R.id.itemtype)
        val price=findViewById<TextView>(R.id.itemprice)

        val itemName=findViewById<TextView>(R.id.ItemName)
        val content=findViewById<TextView>(R.id.content)

        val numbers=findViewById<TextView>(R.id.orderedby2)
        val create=findViewById<Button>(R.id.Createorder)
        var text=intent.getIntExtra("ID",0)
        session= SessionManager(this)
        var token=session.fetchAuthToken()
        var apiClient=application as ApiClient
        var count:Int=0
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFFFF")))
        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setCustomView(R.layout.action_bar_layout_profile)
        val View: View? =supportActionBar?.getCustomView()
        View?.findViewById<TextView>(R.id.cen)!!.text="Orders "
        CoroutineScope(Dispatchers.IO).launch {
            val result=apiClient.apiService.GetDishes("Bearer "+token)
            var i=0
            var res2:FooditemData?=null
            if(result.isSuccessful) {
                while (i < result.body()?.dishes!!.size) {
                    if (result.body()?.dishes!![i].id == text) {
                        res2 = result.body()?.dishes!![i]
                        break
                    }
                    i += 1
                }
            }
              var res3=apiClient.apiService.GetUsers("Bearer "+token)
              if(res3.isSuccessful){
                  var j=0
                  var result2=res3.body()!!.users
                  while(j<result2!!.size){
                   var list=result2[j]!!.orderedDishes!!.split(",")

                          if (res2!!.name in list.toString())
                              count += 1
                      j+=1

                  }
              }
                else{
                    startActivity(intent)
                }

            withContext(Dispatchers.Main){
                Picasso.get().load(res2?.url).into(img);
                if(result.isSuccessful)
                Toast.makeText(this@OrdersPage,"success",Toast.LENGTH_LONG).show()
                if(count<0){
                    numbers.text="0"
                }
                else

                    numbers.text="${count}"
                type.text=res2?.type
                price.text="${res2?.price}"
                itemName.text=res2?.name
                content.text=res2?.contents
            }
        }
        create.setOnClickListener{
            var intent= Intent(this,ConfirmOrders::class.java)
            intent.putExtra("Id",text)
            startActivity(intent)
        }

    }
}