package com.example.mvvm_clean_architecture_paging3_hilt.data.source

import retrofit2.http.GET
import com.example.mvvm_clean_architecture_paging3_hilt.data.dto.Result
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
    ) : Response<List<Result>>


    @GET("detail")
    suspend fun getCarDetail(
        @Query("id") id: Int?
    ): Result
}
