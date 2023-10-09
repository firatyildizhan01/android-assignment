package com.arabam.android.assigment.data.source

import com.arabam.android.assigment.data.dto.ResultCar
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query

interface ApiService {

    @GET("listing")
    suspend fun getCarList(
        @Query("categoryId") categoryId: Int? = null,
        @Query("minDate") minDate: Int? = null,
        @Query("maxDate") maxDate: Int? = null,
        @Query("minYear") minYear: Int? = null,
        @Query("maxYear") maxYear: Int? = null,
        @Query("sort") sort: Int? = null,
        @Query("sortDirection") sortDirection: Int? = null,
        @Query("skip") skip: Int? = null,
        @Query("take") take: Int? = null
    ) : Response<List<ResultCar>>


    @GET("detail")
    suspend fun getCarDetail(
        @Query("id") id: Int?
    ): ResultCar
}
