package com.iris.collapsingtoolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.collapsingtoolbar.R
import com.example.collapsingtoolbar.databinding.ActivityMainBinding
import com.example.collapsingtoolbar.databinding.ViewSearchBarBinding
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var searchBarBinding: ViewSearchBarBinding
    private lateinit var listAdapter: HomeGameListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
//        setSupportActionBar(_binding.toolbar)


        val appBarLayout = binding.appbarLayout
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { it, verticalOffset ->
            // 计算折叠比例
            val collapsedPercentage: Float = (Math.abs(verticalOffset) / appBarLayout.totalScrollRange.toFloat())

            // 检查折叠状态
            if (collapsedPercentage == 0f) {
                // 完全展开
                Toast.makeText(this, "完全展開", Toast.LENGTH_LONG).show()
            } else if (collapsedPercentage == 1f) {
                // 完全折叠
                Toast.makeText(this, "完全折叠", Toast.LENGTH_LONG).show()
            } else {
                // 部分折叠
                Toast.makeText(this, "部分折叠", Toast.LENGTH_LONG).show()
            }
        })

        searchBarBinding = binding.searchLayout
        searchBarBinding.searchLayout.setOnClickListener {
            Toast.makeText(this, "Search bar is clicked", Toast.LENGTH_LONG).show()
        }


        listAdapter = HomeGameListAdapter(getTestData).apply {
            onItemClickListener = adapterOnItemClickListener
            onCollectionClickListener = adapterOnCollectionClickListener
        }

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = listAdapter
        }
    }

    private val getTestData get() =
        listOf(
            ItemGame(
                id = 1,
                name = "Game 1",
                image = "game_1",
                price = "$19.99",
                description = "Description for Game 1",
                isFavorite = false
            ),
            ItemGame(
                id = 2,
                name = "Game 2",
                image = "game_2",
                price = "$24.99",
                description = "Description for Game 2",
                isFavorite = true
            ),
            ItemGame(
                id = 3,
                name = "Game 3",
                image = "game_3",
                price = "$14.99",
                description = "Description for Game 3",
                isFavorite = false
            ),
            ItemGame(
                id = 4,
                name = "Game 4",
                image = "game_4",
                price = "$29.99",
                description = "Description for Game 4",
                isFavorite = true
            ),
            ItemGame(
                id = 5,
                name = "Game 5",
                image = "game_5",
                price = "$9.99",
                description = "Description for Game 5",
                isFavorite = false
            ),
            ItemGame(
                id = 6,
                name = "Game 6",
                image = "game_6",
                price = "$39.99",
                description = "Description for Game 6",
                isFavorite = true
            ),
            ItemGame(
                id = 7,
                name = "Game 7",
                image = "game_7",
                price = "$49.99",
                description = "Description for Game 7",
                isFavorite = false
            ),
            ItemGame(
                id = 8,
                name = "Game 8",
                image = "game_8",
                price = "$54.99",
                description = "Description for Game 8",
                isFavorite = true
            ),
            ItemGame(
                id = 9,
                name = "Game 9",
                image = "game_9",
                price = "$19.99",
                description = "Description for Game 9",
                isFavorite = false
            ),
            ItemGame(
                id = 10,
                name = "Game 10",
                image = "game_10",
                price = "$64.99",
                description = "Description for Game 10",
                isFavorite = true
            )
        )

    private val adapterOnItemClickListener = object : HomeGameListAdapter.OnItemClickListener {
        override fun onItemClick(position: Int) {

        }
    }

    private val adapterOnCollectionClickListener = object : HomeGameListAdapter.OnCollectionClickListener {
        override fun onClick(position: Int) {

        }
    }
}