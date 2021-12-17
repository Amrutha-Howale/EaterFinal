package com.example.food_ordering_app

data class OrdersHistoryList(
    val dishOrders:List<OrdersHistory>?= listOf<OrdersHistory>()
)
