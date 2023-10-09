package com.arabam.android.assigment.domain.use_case

import androidx.paging.PagingData
import com.arabam.android.assigment.domain.model.ResultUI
import com.arabam.android.assigment.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarUseCase @Inject constructor(
    private val repository: CarRepository

) {
    operator fun invoke(categoryId : Int?, minDate : Int?, maxDate : Int?, minYear : Int?, maxYear : Int?, sort : Int?, sortDirection : Int ?, skip : Int? , take : Int?): Flow<PagingData<ResultUI>> {
        return repository.getCar(categoryId , minDate, maxDate , minYear , maxYear, sort , sortDirection , skip  , take )
    }
}
