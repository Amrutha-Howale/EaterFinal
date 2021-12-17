package com.example.food_ordering_app

data class OrdersHistory(val userId : Int?=0,
                         val dishId : Int?=0,
                         val orderId: Int?=0,
                         val count: Int?=0,
                         val dateTimestamp: Long?=0)
