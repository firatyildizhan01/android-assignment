package com.arabam.android.assigment.presentation.car

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assigment.common.loadImage
import com.arabam.android.assigment.databinding.ListItemBinding
import com.arabam.android.assigment.domain.model.ResultUI

class CarAdapter(
) : PagingDataAdapter<ResultUI, CarAdapter.MyViewHolder>(diffCallback) {
    inner class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ResultUI>() {

            override fun areItemsTheSame(oldItem: ResultUI, newItem: ResultUI): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ResultUI, newItem: ResultUI): Boolean {
                return oldItem == newItem
            }
        }
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.binding.apply {
                tvLocation.text = currentItem.location?.cityName ?: ""
                tvYear.text = currentItem.properties?.elementAtOrNull(2)?.value
                tvTitle.text = currentItem.title
                tvPrice.text = currentItem.price.toString()
                imageCars.loadImage(currentItem.photo?.replace("{0}", "800x600"))
                root.setOnClickListener {
                    it.findNavController().navigate(
                        CarFragmentDirections.actionCarFragmentToCarDetailFragment(
                            currentItem.id ?: 1
                        )
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
