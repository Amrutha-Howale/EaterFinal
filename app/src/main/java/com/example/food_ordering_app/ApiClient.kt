package com.example.food_ordering_app

import android.app.Application
import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class ApiClient:Application() {
    public lateinit var apiService: ApiService

    override fun onCreate() {
        super.onCreate()
        apiService = initHttpApiService()
    }
    private fun initHttpApiService():ApiService{

        val retrofit = Retrofit.Builder()
            .baseUrl("https://android-kanini-course.cloud/")
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapper()))
            .build()
        return retrofit.create(ApiService::class.java)
    }
}