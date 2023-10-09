package com.arabam.android.assigment.presentation.navigation

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.arabam.android.assigment.presentation.navigation.NavigationState

interface NavigationStateOwner {
    val navigationState: MutableLiveData<NavigationState?>

    fun navigate(directions: NavDirections) {
        navigationState.value = NavigationState(directions)
    }

    fun navigationCompleted() {
        navigationState.value = null
    }
}