package com.arabam.android.assigment.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.arabam.android.assigment.data.mapper.toResultUI
import com.arabam.android.assigment.domain.repository.CarRepository
import com.arabam.android.assigment.data.source.RemoteDataSource
import com.arabam.android.assigment.domain.model.ResultUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CarRepositoryImpl(private val remoteDataSource: RemoteDataSource) : CarRepository {

    override fun getCar(categoryId : Int?, minDate : Int?, maxDate : Int?, minYear : Int?, maxYear : Int?, sort : Int?, sortDirection : Int ?, skip : Int?, take : Int?): Flow<PagingData<ResultUI>> {
        return remoteDataSource.getCar(categoryId , minDate, maxDate , minYear , maxYear, sort , sortDirection , skip  , take ).map { responseData ->
            responseData.map { it.toResultUI() }
        }
    }

    override suspend fun getCarById(id: Int?): ResultUI {
        return remoteDataSource.getCarById(id).toResultUI()
    }
}
