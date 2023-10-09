package com.arabam.android.assigment.presentation.bottom_sheet

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.arabam.android.assigment.databinding.FragmentItemListDialogListDialogBinding

import com.arabam.android.assigment.presentation.car.CarViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemListDialogFragment : BottomSheetDialogFragment() {

    private var binding: FragmentItemListDialogListDialogBinding? = null
    private val viewModel : CarViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemListDialogListDialogBinding.inflate(layoutInflater)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dismissDialogEvent.observe(
            viewLifecycleOwner,
        ) { shouldDismiss ->
            if (shouldDismiss) {
                viewModel.setCarList()
                dismiss()
                viewModel.dismissDialogEvent.value = false
            }
        }

        viewModel.showBottomType.observe(
            viewLifecycleOwner,
        ) { bottomType ->
            when (bottomType) {
                1 -> {
                    binding?.apply {
                        bottomPrice.visibility = View.VISIBLE
                        bottomYear.visibility = View.VISIBLE
                        bottomDate.visibility = View.VISIBLE

                    }
                }
                2 -> {
                    binding?.apply {
                        bottomAscending.visibility = View.VISIBLE
                        bottomDescending.visibility = View.VISIBLE
                    }
                }
                3 -> {
                    binding?.apply {
                        bottomMindate.visibility = View.VISIBLE
                        bottomMaxDate.visibility = View.VISIBLE
                        bottomCategoryId.visibility = View.VISIBLE
                        button.visibility = View.VISIBLE
                    }            }
                else -> {
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}