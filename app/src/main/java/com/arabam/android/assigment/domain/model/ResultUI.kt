package com.arabam.android.assigment.domain.model

import com.arabam.android.assigment.data.dto.Category
import com.arabam.android.assigment.data.dto.Location
import com.arabam.android.assigment.data.dto.Property
import com.arabam.android.assigment.data.dto.UserInfo

data class ResultUI(
    val id: Int?,
    val photo: String?,
    val price: Int?,
    val location: Location?,
    val category: Category?,
    val priceFormatted: String?,
    val dateFormatted: String?,
    val modelName: String?,
    val properties: List<Property?>?,
    val photos: List<String?>?,
    val title: String?,
    val userInfo: UserInfo?,
)