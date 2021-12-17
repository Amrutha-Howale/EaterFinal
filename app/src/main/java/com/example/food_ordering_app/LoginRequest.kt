package com.example.food_ordering_app

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    var email:String?="",
    var password:String?="",)
