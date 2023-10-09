package com.arabam.android.assigment.presentation.toast

import androidx.lifecycle.MutableLiveData
import com.arabam.android.assigment.presentation.toast.ToastState

interface ToastStateOwner {
    val toastState: MutableLiveData<ToastState?>

    fun ToastState.show() {
        toastState.value = this
        toastState.value = null
    }
}