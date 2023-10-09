package com.arabam.android.assigment.presentation.car

import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.arabam.android.assigment.R
import com.arabam.android.assigment.databinding.FragmentCarBinding
import com.arabam.android.assigment.presentation.base_fragment.BaseFragment
import com.arabam.android.assigment.presentation.bottom_sheet.ItemListDialogFragment
import com.arabam.android.assigment.presentation.navigation.NavigationStateObserver
import com.arabam.android.assigment.presentation.toast.ToastStateObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CarFragment : BaseFragment<FragmentCarBinding>(R.layout.fragment_car) {

    private val viewModel: CarViewModel by activityViewModels()
    private val carAdapter = CarAdapter()
    private val navigationStateObserver = NavigationStateObserver()
    private val toastStateObserver = ToastStateObserver()

    companion object {
        private const val MENU_ITEM_1 = 1
        private const val MENU_ITEM_2 = 2
        private const val MENU_ITEM_3 = 3
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCarBinding {
        return FragmentCarBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        setupRecyclerView()
        dataLoad()
        menuHost()
    }

    override fun setupObservers() {
        toastStateObserver.observeForToastMessages(viewModel, viewLifecycleOwner, binding.root)
        navigationStateObserver.observeForNavigationState(viewModel, viewLifecycleOwner, binding.root)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = carAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
        }
    }

    private fun showBottomFragment(listIdentifier: Int) {
        viewModel.showBottomType.value = listIdentifier
        val bottomSheetFragment = ItemListDialogFragment()
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }

    private fun menuHost() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.item_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_menu1 -> {
                        showBottomFragment(MENU_ITEM_1)
                        true
                    }
                    R.id.action_menu2 -> {
                        showBottomFragment(MENU_ITEM_2)
                        true
                    }
                    R.id.action_menu3 -> {
                        showBottomFragment(MENU_ITEM_3)
                        true
                    }
                    R.id.action_menu4 -> {
                        viewModel.getCars(sort = 0, sortDirection = 1, skip = 1, take = 100)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun dataLoad() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.cars.collectLatest { carState ->
            when (carState) {
                is CarState.Success -> {
                    val pagingData = carState.data
                    carAdapter.submitData(pagingData)
                }
                is CarState.Loading -> {
                }
                is CarState.Error -> {
                    error("Not Responding")
                }
            }
        }
    }
}
