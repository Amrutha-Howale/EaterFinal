package com.example.food_ordering_app

data class LoginResponse( var id:Int=0,
                           var token: String="",
                           var email:String="",
                           var memberSince: Long=0,
                           var password:String?=null,)
