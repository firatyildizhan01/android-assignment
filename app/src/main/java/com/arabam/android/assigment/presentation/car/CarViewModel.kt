package com.arabam.android.assigment.presentation.car

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arabam.android.assigment.domain.use_case.CarUseCase
import com.arabam.android.assigment.presentation.navigation.NavigationState
import com.arabam.android.assigment.presentation.navigation.NavigationStateOwner
import com.arabam.android.assigment.presentation.toast.ToastState
import com.arabam.android.assigment.presentation.toast.ToastStateOwner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val useCase: CarUseCase

) : ViewModel(), NavigationStateOwner, ToastStateOwner {

    private val _cars = MutableStateFlow<CarState>(CarState.Loading)
    val cars = _cars.asStateFlow()

    val dismissDialogEvent = MutableLiveData<Boolean>()

    private val showShortType = MutableLiveData<Int>(null)

    private val showShortDirectionType = MutableLiveData<Int>(null)

    private val minYear = MutableLiveData<Int>(null)

    private val maxYear = MutableLiveData<Int>(null)

    private val categoryId = MutableLiveData<Int>(null)

    val showBottomType = MutableLiveData<Int>()

    override val navigationState = MutableLiveData<NavigationState?>(null)

    override val toastState = MutableLiveData<ToastState?>(null)

    init {
        getCars(sort = 0 , sortDirection = 1 , skip = 1 , take = 100)
    }

     fun getCars(categoryId : Int? = null, minDate : Int? = null, maxDate : Int? = null, minYear : Int? = null, maxYear : Int? = null, sort : Int? = null, sortDirection : Int ? = null, skip : Int? = null, take : Int? = null) = viewModelScope.launch {
        useCase(categoryId , minDate, maxDate , minYear , maxYear, sort , sortDirection , skip  , take).cachedIn(viewModelScope).collectLatest {
                _cars.emit(CarState.Success(it))
        }
    }

    fun setCarList(minDate : Int? = null, maxDate : Int? = null, skip : Int? = null, take : Int? = 100){
        getCars(categoryId.value , minDate, maxDate , minYear.value , maxYear.value, showShortType.value , showShortDirectionType.value , skip , take )
    }

    fun sortParameter(sort : Int){
        showShortType.value = sort
        dismissDialogEvent.value = true
    }

    fun sortDirectionParameter(sortDirection : Int){
        showShortDirectionType.value = sortDirection
        dismissDialogEvent.value = true
    }

    fun setMinYear(s: Editable){
        if (s.isNotEmpty()) {
            minYear.value = s.toString().toInt()
        }
    }

    fun setMaxYear(s: Editable){
        if (s.isNotEmpty()) {
            maxYear.value = s.toString().toInt()
        }
    }

    fun setCategoryId(s: Editable){
        if (s.isNotEmpty()) {
            categoryId.value = s.toString().toInt()
        }
    }

    fun calculateFilter(){
        dismissDialogEvent.value = true
        setCarList()
    }

}
