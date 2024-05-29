package com.iris.collapsingtoolbar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.collapsingtoolbar.databinding.ItemCardSliderBinding
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel

class CardSliderAdapter(private val images: List<String>) :
    RecyclerView.Adapter<CardSliderAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(private val binding: ItemCardSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(imageUrl: String) {
            binding.imageView.apply {
                val shapeAppearanceModel = ShapeAppearanceModel.builder()
                    .setAllCorners(CornerFamily.ROUNDED, 8f)
                    .build()
                this.shapeAppearanceModel = shapeAppearanceModel

                load(imageUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = ItemCardSliderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        images.getOrNull(position)?.apply {
            holder.setData(this)
        }
    }

    override fun getItemCount(): Int = images.size
}