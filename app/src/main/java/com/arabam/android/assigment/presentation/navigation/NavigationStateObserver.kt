package com.arabam.android.assigment.presentation.navigation

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation

class NavigationStateObserver {
    fun observeForNavigationState(
        stateOwner: NavigationStateOwner,
        lifecycleOwner: LifecycleOwner,
        view: View?
    ) {
        stateOwner.navigationState.observe(lifecycleOwner) {
            if (it != null) {
                if (view != null) {
                    Navigation.findNavController(view).navigate(it.directions)
                }
                stateOwner.navigationCompleted()
            }
        }
    }
}