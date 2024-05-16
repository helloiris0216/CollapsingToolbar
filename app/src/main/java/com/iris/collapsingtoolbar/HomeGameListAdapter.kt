package com.iris.collapsingtoolbar

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.collapsingtoolbar.R
import com.google.android.material.card.MaterialCardView

class HomeGameListAdapter(
    private var itemList: List<ItemGame> = emptyList(),
    private val emptyText: String = ""
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val TAG = "HomeGameListAdapter"
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_EMPTY = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_game, parent, false)
                ViewHolder(view)
            }

            VIEW_TYPE_EMPTY -> {
                val emptyView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_empty, parent, false)
                EmptyViewHolder(emptyView)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val item = itemList[position]
                item?.let {
                    holder.bind(it, position)
                }
            }
            is EmptyViewHolder -> {
                // Set the text for the empty view
                holder.emptyText.text = emptyText
            }
        }
    }

    override fun getItemCount(): Int {
        return if (itemList.isEmpty()) 1 else itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemList.isEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_ITEM
    }

    fun setData(list: List<ItemGame>) {
        itemList = list
        notifyDataSetChanged()
    }

    fun getItemSize(): Int = itemList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemImage: ImageView = itemView.findViewById(R.id.item_game_image)
        private val itemName: TextView = itemView.findViewById(R.id.item_game_tv)
        private val itemLayout: MaterialCardView = itemView.findViewById(R.id.list_game_item_layout)
        private val itemFavorite: ImageButton = itemView.findViewById(R.id.item_game_favorite)

        init {
            itemLayout.apply {
                setOnClickListener{onItemClickListener?.onItemClick(adapterPosition)}
            }
            itemFavorite.apply {
                setOnClickListener{
                    onCollectionClickListener?.onClick(adapterPosition)
                }
            }
        }

        @SuppressLint("DiscouragedApi")
        fun bind(item: ItemGame, position: Int) {
            item.apply {
                itemName.text = name
                itemImage.apply {
                    try {
                        val drawableResourceId = resources.getIdentifier(item.image, "drawable", itemView.context.packageName)
                        drawableResourceId?.also {
                            ContextCompat.getDrawable(itemView.context, drawableResourceId)?.also {
                                itemImage.setImageDrawable(it)
                            }
                        }
                    } catch (ex: Exception) {
                        Log.d(TAG, "bind > ${ex.message}")
                    }
                }

                // 用是否收藏判斷要顯示哪個 icon
                itemFavorite.apply {
                    val favoriteImage = if (isFavorite) {
                        ResourcesCompat.getDrawable(itemView.resources, R.drawable.heart_collect_selected, null)
                    } else {
                        ResourcesCompat.getDrawable(itemView.resources, R.drawable.heart_collect, null)
                    }
                    setImageDrawable(favoriteImage)
                }
            }
        }
    }

    inner class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val emptyText: TextView =
            itemView.findViewById(R.id.empty_text)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnCollectionClickListener {
        fun onClick(position: Int)
    }

    var onItemClickListener: OnItemClickListener? = null
    var onCollectionClickListener: OnCollectionClickListener? = null
}
