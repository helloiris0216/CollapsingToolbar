package com.iris.collapsingtoolbar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.collapsingtoolbar.databinding.ItemHomeBinding
import com.example.collapsingtoolbar.databinding.ItemHomeCardSliderBinding

class MultiItemViewsAdapter(private val items: List<MultiItemTypesDto>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ViewHolderCardSlider(private val binding: ItemHomeCardSliderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: CardSliderItem) {
            binding.viewPager.apply {
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                adapter = CardSliderAdapter(item.imageUrls)
            }

            binding.dotsIndicator.apply {
                attachTo(binding.viewPager)
            }
        }
    }

    inner class ViewHolderItems(private val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: Item) {
            binding.tvName.text = item.name
            binding.ivUrl.load(item.imageUrl)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MultiItemTypesDto.TYPE_CARD_SLIDER -> {
                val binding = ItemHomeCardSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolderCardSlider(binding)
            }
            MultiItemTypesDto.TYPE_ITEMS -> {
                val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolderItems(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is ViewHolderCardSlider -> {
                holder.setData(item as CardSliderItem)
            }
            is ViewHolderItems -> {
                holder.setData(item as Item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
