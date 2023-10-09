package com.arabam.android.assigment.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arabam.android.assigment.data.dto.ResultCar
import com.arabam.android.assigment.data.source.ApiService

class CarPagingSource(private val apiService: ApiService, private val categoryId : Int?, private val minDate : Int?, private val maxDate : Int?, private val minYear : Int?, private val maxYear : Int?, private val sort : Int?, private val sortDirection : Int ?, private val skip : Int?, private val take : Int?) : PagingSource<Int, ResultCar>() {

    override fun getRefreshKey(state: PagingState<Int, ResultCar>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultCar> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getCarList(categoryId = categoryId, minDate = minDate, maxDate = maxDate, minYear = minYear, maxYear = maxYear , sort = sort, sortDirection = sortDirection , skip = skip , take = take)

            if (response.isSuccessful) {
                val responseBody = response.body()
                val nextKey = currentPage + 1

                if (responseBody != null) {
                    LoadResult.Page(
                        data = responseBody,
                        prevKey = if (currentPage > 1) currentPage - 1 else null,
                        nextKey = nextKey
                    )
                } else {
                    LoadResult.Error(Exception("Response body is null"))
                }
            } else {
                LoadResult.Error(Exception("Response is not successful"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }}
