package com.arabam.android.assigment.data.source

import androidx.paging.PagingData
import com.arabam.android.assigment.data.dto.ResultCar
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getCar(categoryId : Int?, minDate : Int?, maxDate : Int?, minYear : Int?, maxYear : Int?, sort : Int?, sortDirection : Int ?, skip : Int?, take : Int?): Flow<PagingData<ResultCar>>

    suspend fun getCarById(id: Int?): ResultCar
}
