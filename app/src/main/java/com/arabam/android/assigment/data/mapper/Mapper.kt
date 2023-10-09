package com.arabam.android.assigment.data.mapper

import com.arabam.android.assigment.data.dto.ResultCar
import com.arabam.android.assigment.domain.model.ResultUI

fun ResultCar.toResultUI() = ResultUI(
    id = id,
    photo = photo,
    price = price,
    location = location,
    properties = properties,
    title = title,
    category = category,
    priceFormatted = priceFormatted,
    dateFormatted = dateFormatted,
    modelName = modelName,
    photos = photos,
    userInfo = userInfo
)
