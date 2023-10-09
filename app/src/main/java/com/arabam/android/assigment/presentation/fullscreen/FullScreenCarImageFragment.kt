package com.arabam.android.assigment.presentation.fullscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.arabam.android.assigment.R
import com.arabam.android.assigment.databinding.FragmentFullScreenCarImageBinding
import com.arabam.android.assigment.presentation.navigation.NavigationStateObserver
import com.arabam.android.assigment.presentation.toast.ToastStateObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullScreenCarImageFragment : Fragment(R.layout.fragment_full_screen_car_image) {

    private var binding: FragmentFullScreenCarImageBinding? = null
    private val viewModel: FullScreenCarImageViewModel by viewModels()

    private val navigationStateObserver = NavigationStateObserver()
    private val toastStateObserver = ToastStateObserver()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFullScreenCarImageBinding.inflate(layoutInflater)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner

        toastStateObserver.observeForToastMessages(viewModel, viewLifecycleOwner, binding?.root)

        navigationStateObserver.observeForNavigationState(
            viewModel,
            viewLifecycleOwner,
            binding?.root
        )

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
