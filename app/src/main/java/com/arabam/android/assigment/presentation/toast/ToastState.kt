package com.arabam.android.assigment.presentation.toast

import android.widget.Toast

data class ToastState(
    val message: String? = null,
    val messageRes: Int? = null,
    val duration: Int = Toast.LENGTH_LONG
) {
    init {
        if (message == null && messageRes == null) {
            throw IllegalArgumentException("message and messageRes cannot be null at the same time.")
        }
    }
}