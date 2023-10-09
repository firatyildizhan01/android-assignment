package com.arabam.android.assigment.presentation.toast

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner

class ToastStateObserver {
    fun observeForToastMessages(
        stateOwner: ToastStateOwner,
        lifecycleOwner: LifecycleOwner,
        view: View?
    ) {
        stateOwner.toastState.observe(lifecycleOwner) {
            if (it != null) {
                if (it.message != null) {
                    Toast.makeText(view?.context, it.message, Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(view?.context, it.messageRes!!, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}