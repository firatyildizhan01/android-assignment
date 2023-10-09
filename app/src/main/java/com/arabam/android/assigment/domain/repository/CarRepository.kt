package com.arabam.android.assigment.domain.repository

import androidx.paging.PagingData
import com.arabam.android.assigment.domain.model.ResultUI
import kotlinx.coroutines.flow.Flow

interface CarRepository {

    fun getCar(categoryId : Int?, minDate : Int?, maxDate : Int?, minYear : Int?, maxYear : Int?, sort : Int?, sortDirection : Int ?, skip : Int?, take : Int?): Flow<PagingData<ResultUI>>

    suspend fun getCarById(id: Int?): ResultUI
}
