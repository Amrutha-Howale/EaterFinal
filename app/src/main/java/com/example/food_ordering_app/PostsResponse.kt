package com.example.food_ordering_app

import com.google.gson.annotations.SerializedName

data class PostsResponse( @SerializedName("status_code")
                          var status: Int,

                          @SerializedName("message")
                          var message: String,

                          @SerializedName("posts")
                          var posts: List<LoginRequest>)
