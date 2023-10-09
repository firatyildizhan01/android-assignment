package com.arabam.android.assigment.data.dto

data class ResultCar(
    val category: Category?,
    val date: String?,
    val dateFormatted: String?,
    val id: Int?,
    val location: Location?,
    val modelName: String?,
    val photo: String?,
    val photos: List<String?>?,
    val price: Int?,
    val priceFormatted: String?,
    val properties: List<Property?>?,
    val title: String?,
    val userInfo: UserInfo?,
)
