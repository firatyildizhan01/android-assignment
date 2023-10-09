package com.arabam.android.assigment.presentation.fullscreen

import androidx.lifecycle.*
import com.arabam.android.assigment.presentation.navigation.NavigationState
import com.arabam.android.assigment.presentation.navigation.NavigationStateOwner
import com.arabam.android.assigment.presentation.toast.ToastState
import com.arabam.android.assigment.presentation.toast.ToastStateOwner
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FullScreenCarImageViewModel @Inject constructor(savedStateHandle: SavedStateHandle
) : ViewModel(), NavigationStateOwner, ToastStateOwner {

     val argument: String? = savedStateHandle["imageUrl"]

    override val toastState = MutableLiveData<ToastState?>(null)

    override val navigationState = MutableLiveData<NavigationState?>(null)
}
