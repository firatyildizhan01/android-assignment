package com.arabam.android.assigment.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arabam.android.assigment.common.Constants
import com.arabam.android.assigment.data.dto.ResultCar
import com.arabam.android.assigment.data.paging.CarPagingSource
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl (private val apiService: ApiService): RemoteDataSource {

    override fun getCar(categoryId : Int?, minDate : Int?, maxDate : Int?, minYear : Int?, maxYear : Int?, sort : Int?, sortDirection : Int ?, skip : Int?, take : Int?): Flow<PagingData<ResultCar>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.NETWORK_PAGE_SIZE),
            pagingSourceFactory = { CarPagingSource(apiService,categoryId , minDate, maxDate , minYear , maxYear, sort , sortDirection , skip  , take ) }
        ).flow
    }

    override suspend fun getCarById(id: Int?): ResultCar {
        return apiService.getCarDetail(id)
    }
}
