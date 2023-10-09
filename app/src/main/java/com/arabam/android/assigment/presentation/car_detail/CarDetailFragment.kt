package com.arabam.android.assigment.presentation.car_detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.arabam.android.assigment.R
import com.arabam.android.assigment.databinding.FragmentCarDetailBinding
import com.arabam.android.assigment.presentation.base_fragment.BaseFragment
import com.arabam.android.assigment.presentation.navigation.NavigationStateObserver
import com.arabam.android.assigment.presentation.toast.ToastStateObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CarDetailFragment : BaseFragment<FragmentCarDetailBinding>(R.layout.fragment_car_detail) {

    private val viewModel: CarDetailViewModel by viewModels()
    private val navigationStateObserver = NavigationStateObserver()
    private val toastStateObserver = ToastStateObserver()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCarDetailBinding {
        return FragmentCarDetailBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        clickForNumber()
    }

    private fun clickForNumber(){
        binding.phoneNumberLayout.setOnClickListener {
            lifecycleScope.launch {
                viewModel.carDetail.collect { carDetail ->
                    if (carDetail != null) {
                        val dialIntent = Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse("tel:${carDetail.userInfo?.phoneFormatted}")
                        )
                        try {
                            startActivity(dialIntent)
                        } catch (_: ActivityNotFoundException) {
                        }
                    }
                }
            }
        }
    }
    override fun setupObservers() {
        toastStateObserver.observeForToastMessages(viewModel, viewLifecycleOwner, binding.root)
        navigationStateObserver.observeForNavigationState(viewModel, viewLifecycleOwner, binding.root)
    }
}

