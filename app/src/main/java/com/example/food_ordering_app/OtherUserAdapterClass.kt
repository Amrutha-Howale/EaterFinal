package com.example.food_ordering_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class OtherUserAdapterClass(var songs:MutableList<OtherUsersData>): RecyclerView.Adapter<OtherUserAdapterClass.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.otheruser,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text1.text=songs[position].email
        holder.text2.text=songs[position].orderedDishes
        holder.text3.text= songs[position].email?.get(0)?.uppercase().toString()

       // Picasso.get().load(songs[position].url).into(holder.image);
    }

    override fun getItemCount(): Int {
        return songs.size
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
      //  val image=itemView.findViewById<ImageView>(R.id.image)
        val text1=itemView.findViewById<TextView>(R.id.textView4)
        val text2=itemView.findViewById<TextView>(R.id.textView5)
        val text3=itemView.findViewById<TextView>(R.id.nameLetter)
    }
}