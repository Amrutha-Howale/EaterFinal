package com.example.food_ordering_app

data class dish(var id: Int=0,
                var url:String="",
                var name:String="",
                var price: Int=0,
                val count: Int?=0,
                val orderid:Int?=0,
                val type : String="")
