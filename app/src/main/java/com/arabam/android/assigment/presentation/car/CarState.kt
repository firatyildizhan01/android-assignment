package com.arabam.android.assigment.presentation.car

import androidx.paging.PagingData
import com.arabam.android.assigment.domain.model.ResultUI

sealed interface CarState {
    object Loading : CarState
    data class Success(val data: PagingData<ResultUI>) : CarState
    data class Error(val message: String?) : CarState
}
