package com.example.food_ordering_app

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.sql.Date
import java.text.SimpleDateFormat


class LoginAdatpterClass(var songs:MutableList<LoginData>): RecyclerView.Adapter<LoginAdatpterClass.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.logintimestamp,parent,false)
        return ViewHolder(view)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date= Date(songs[position].loginTimestamp!!*1L)
        holder.text1.text="Logged in: ${date.toGMTString().removeRange(12,date.toGMTString().length)}"


       // holder.text2.text=songs[position].orderedDishes

        // Picasso.get().load(songs[position].url).into(holder.val sdf=SimpleDateFormat("dd/MM/yyyy")
        //        val date= Date(songs[position].loginTimestamp!!*1000L)
        //        holder.text1.text="${sdf.format(date)}"image);
    }

    override fun getItemCount(): Int {
        return songs.size
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //  val image=itemView.findViewById<ImageView>(R.id.image)
        val text1=itemView.findViewById<TextView>(R.id.LogintimeStamp)
        //val text2=itemView.findViewById<TextView>(R.id.textView5)
    }
}
