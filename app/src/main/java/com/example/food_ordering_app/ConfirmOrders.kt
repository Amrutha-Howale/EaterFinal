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
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConfirmOrders : AppCompatActivity() {
    lateinit var session:SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_orders)
        val img=findViewById<ImageView>(R.id.images)
        val qty=findViewById<TextView>(R.id.qty)
        val add=findViewById<Button>(R.id.add)
        val sub=findViewById<Button>(R.id.reduce)
        val pricce=findViewById<TextView>(R.id.itempricee)
        val create=findViewById<Button>(R.id.placeorder)
        val myorders=findViewById<Button>(R.id.check)
        var text=intent.getIntExtra("Id",0)
        var prices:Int?=0
        var count=0
        session= SessionManager(this)
        var token=session.fetchAuthToken()
        var apiClient=application as ApiClient

        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFFFF")))
        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setCustomView(R.layout.action_bar_layout_profile)
        val View: View? =supportActionBar?.getCustomView()
        View?.findViewById<TextView>(R.id.cen)!!.text="Place Order "


        CoroutineScope(Dispatchers.IO).launch {
            val result=apiClient.apiService.GetDishes("Bearer "+token)
            var i=0
            var res2:FooditemData?=null
            if(result.isSuccessful){
                while(i<result.body()?.dishes!!.size){
                    if(result.body()?.dishes!![i].id==text){
                        res2=result.body()?.dishes!![i]
                        break
                    }
                    i+=1
                }
            }
            else{

            }
            withContext(Dispatchers.Main){
                Picasso.get().load(res2?.url).into(img)
                prices=res2?.price?.toInt()
            }
        }
        add.setOnClickListener{
            count+=1
            qty.text="$count"
            val pp=prices!!.toInt()
            pricce.text="$ price ${pp*count}"
        }
        sub.setOnClickListener{
            if(count>0){
                count-=1
            }
            else
                count=0
            qty.text="$count"
            val pp=prices!!.toInt()
            pricce.text="$ price ${pp*count}"
        }
        create.setOnClickListener{
            if(count>0){
                CoroutineScope(Dispatchers.IO).launch {

                    var result=apiClient.apiService.PlaceOrders("Bearer "+token, Dishes(text,count))
                    withContext(Dispatchers.Main){

                        if(result!!.isSuccessful){
                          
                            Toast.makeText(this@ConfirmOrders,"Order successfull",Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
            else{
                Toast.makeText(this@ConfirmOrders,"Please select number of items",Toast.LENGTH_SHORT).show()
            }
        }
        myorders.setOnClickListener{
            var intent= Intent(this,MyOrders::class.java)
            startActivity(intent)
        }
    }
}