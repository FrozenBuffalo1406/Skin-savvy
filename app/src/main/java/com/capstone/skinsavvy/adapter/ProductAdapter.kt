package com.capstone.skinsavvy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.capstone.skinsavvy.data.model.ListProductItem
import com.capstone.skinsavvy.databinding.ProductItemBinding

class ProductAdapter() : ListAdapter<ListProductItem, ProductAdapter.ProductViewHolder>(ProductDiffCallBack()) {

    class ProductViewHolder(binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    class ProductDiffCallBack : DiffUtil.ItemCallback<ListProductItem>() {
        override fun areItemsTheSame(oldItem: ListProductItem, newItem: ListProductItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ListProductItem,
            newItem: ListProductItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)

    }


}


