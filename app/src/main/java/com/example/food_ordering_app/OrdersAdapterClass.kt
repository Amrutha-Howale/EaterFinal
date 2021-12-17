package com.example.food_ordering_app

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class OrdersAdapterClass(var items:MutableList<dish>, var context: Context, var application: Application):
    RecyclerView.Adapter<OrdersAdapterClass.ViewHolder>() {
    lateinit var sessionManager: SessionManager
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersAdapterClass.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.order,parent,false)
        return ViewHolder(view)

    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(items[position]!!.url).into(holder.img)
        holder.names.text=items[position].name
        holder.type.text=items[position].type
        holder.price.text="$ Price ${items[position].count!!.toInt()*items[position]!!.price}"
        holder.countt.text="items ${items[position].count}"
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var img = itemView.findViewById<ImageView>(R.id.imgg)
        var names=itemView.findViewById<TextView>(R.id.nametext)
        var price=itemView.findViewById<TextView>(R.id.pricetext)
        var type=itemView.findViewById<TextView>(R.id.typetext)
        var countt=itemView.findViewById<TextView>(R.id.itemtext)
        var btn = itemView.findViewById<Button>(R.id.cancel)

        init {
            btn.setOnClickListener {
                var pos = adapterPosition
                var sessionManager = SessionManager(context)
                var Api = application as ApiClient
                var token = sessionManager.fetchAuthToken()
                var intent = Intent(context,DeleteOrders::class.java)
                intent.putExtra("orderid", items[pos].orderid)
                ContextCompat.startActivity(context, intent, Bundle())

            }
        }
    }
}
