package com.example.food_ordering_app

import androidx.recyclerview.widget.RecyclerView
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*
import java.net.CacheRequest

interface ApiService {
   @POST(Constants.Register_URL)
     suspend fun Register(@Body request: RegisterUser)

    @POST(Constants.Register_URL)
    suspend fun register(@Body user: User): Response<RegisterUser>

    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest):Response<LoginResponse>
   @GET("/eaterapp/dishes")
   suspend fun GetDishes(@Header("Authorization") token: String):Response<FooditemsList>

   @GET("/eaterapp/users")
   suspend fun GetUsers(@Header("Authorization") token: String):Response<OtherUserList>

    @POST("/eaterapp/users/me/email")
    suspend fun change_email(@Header("Authorization") token: String,@Body updateRequest: UpdateRequest):Response<UpdateResponse>

    @POST("/eaterapp/users/me/orders")
    suspend fun PlaceOrders(@Header("Authorization") token: String,@Body request: Dishes):Response<Unit>

    @GET("/eaterapp/users/me/loginHistory")
    suspend fun GetLoginhistory(@Header("Authorization") token: String):Response<LoginDataList>

    @DELETE("/eaterapp/users/me")
    suspend fun  delete(@Header("Authorization") token: String)

    @GET("/eaterapp/users/me/orders")
    suspend fun fetchOrders(@Header("Authorization") token: String):Response<OrdersHistoryList>

    @DELETE("/eaterapp/users/me/orders/{orderId}")
    suspend fun DeleteOrders(@Header("Authorization") token: String,@Path("orderId")orderId:Int):Response<Unit>


}