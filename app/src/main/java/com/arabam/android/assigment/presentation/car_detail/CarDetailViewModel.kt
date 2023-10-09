package com.arabam.android.assigment.presentation.car_detail

import android.widget.Toast
import androidx.lifecycle.*
import com.arabam.android.assigment.common.Resource
import com.arabam.android.assigment.domain.model.ResultUI
import com.arabam.android.assigment.domain.use_case.CarByIdUseCase
import com.arabam.android.assigment.presentation.navigation.NavigationState
import com.arabam.android.assigment.presentation.navigation.NavigationStateOwner
import com.arabam.android.assigment.presentation.toast.ToastState
import com.arabam.android.assigment.presentation.toast.ToastStateOwner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarDetailViewModel @Inject constructor(private val useCase: CarByIdUseCase, savedStateHandle: SavedStateHandle
) : ViewModel(), NavigationStateOwner, ToastStateOwner {

    private val _carDetail: MutableStateFlow<ResultUI?> = MutableStateFlow(null)
    val carDetail = _carDetail.asStateFlow()

    private val _progress: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val progress = _progress.asStateFlow()

    private val argument: Int? = savedStateHandle["id"]

    override val toastState = MutableLiveData<ToastState?>(null)

    override val navigationState = MutableLiveData<NavigationState?>(null)

    init {
        if (argument != null) {
            getCarById(argument)
        }
    }
    fun getCarById(carId : Int) = viewModelScope.launch {

        when (val result = useCase(carId)) {

            is Resource.Loading -> {
                _progress.value = false
            }
            is Resource.Success -> {
                _carDetail.value = result.data
                _progress.value = true
            }
            is Resource.Error -> {
                toastState.value = ToastState(
                    message = "Not Responding",
                    duration = Toast.LENGTH_LONG
                )
            }
        }
    }

    fun navigateToFullScreenFragment() {
        navigate(CarDetailFragmentDirections.actionCarDetailFragmentToFullScreenCarImageFragment(
            carDetail.value?.photos?.elementAtOrNull(0).toString()
        ))

    }
}
