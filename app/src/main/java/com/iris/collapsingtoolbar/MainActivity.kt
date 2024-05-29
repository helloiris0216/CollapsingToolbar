package com.iris.collapsingtoolbar

import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collapsingtoolbar.databinding.ActivityMainBinding
import com.example.collapsingtoolbar.databinding.ViewCollapsingAppbarBinding
import com.example.collapsingtoolbar.databinding.ViewSearchBarBinding
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var searchBarBinding: ViewSearchBarBinding
    private lateinit var listAdapter: HomeGameListAdapter
    private lateinit var viewAppBarLayout: ViewCollapsingAppbarBinding
    private lateinit var multipleAdapter: MultiItemViewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewAppBarLayout = binding.appbarLayout


        val appBarLayout = viewAppBarLayout.appbarContainer.apply {
            addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                // 計算折疊比例
                val collapsedPercentage: Float =
                    abs(verticalOffset) / appBarLayout.totalScrollRange.toFloat()

                // 獲取 WindowInsetsController
                val windowInsetsController = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    window.insetsController
                } else {
                    null
                }

                // 檢查折疊狀態
                when (collapsedPercentage) {
                    0f -> {
                        // 完全展開 > 淺色文字
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            windowInsetsController?.setSystemBarsAppearance(
                                0,
                                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                            )
                        }
                    }

                    1f -> {
                        // 完全折叠 > 深色文字
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            windowInsetsController?.setSystemBarsAppearance(
                                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                            )
                        }
                    }
                }
            }
        }

        searchBarBinding = binding.searchLayout.apply {
            viewLayoutSearch.setOnClickListener {
                Toast.makeText(this@MainActivity, "Search bar is clicked", Toast.LENGTH_LONG).show()
            }
        }

        // TODO: test general adapter
//        listAdapter = HomeGameListAdapter(getTestData).apply {
//            onItemClickListener = adapterOnItemClickListener
//            onCollectionClickListener = adapterOnCollectionClickListener
//        }
//
//        binding.recyclerView.apply {
//            layoutManager =
//                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
//            adapter = listAdapter
//        }

        // TODO: test multi item views
        multipleAdapter = MultiItemViewsAdapter(getMultiTypesData())

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = multipleAdapter
        }
    }

    private val getTestData
        get() =
            listOf(
                ItemGame(
                    id = 1,
                    name = "Game 1",
                    image = "game_1",
                    price = "19.99",
                    description = "Description for Game 1"
                ),
                ItemGame(
                    id = 2,
                    name = "Game 2",
                    image = "game_2",
                    price = "24.99",
                    description = "Description for Game 2"
                ),
                ItemGame(
                    id = 3,
                    name = "Game 3",
                    image = "game_3",
                    price = "14.99",
                    description = "Description for Game 3"
                ),
                ItemGame(
                    id = 4,
                    name = "Game 4",
                    image = "game_4",
                    price = "29.99",
                    description = "Description for Game 4"
                )
            )

    private val adapterOnItemClickListener = object : HomeGameListAdapter.OnItemClickListener {
        override fun onItemClick(position: Int) {

        }
    }

    private val adapterOnCollectionClickListener =
        object : HomeGameListAdapter.OnCollectionClickListener {
            override fun onClick(position: Int) {

            }
        }

    private fun getMultiTypesData(): List<MultiItemTypesDto> {
        val imageUrls = listOf(
            "https://images.unsplash.com/photo-1621551122354-e96737d64b70?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            "https://images.unsplash.com/photo-1621616875450-79f024a8c42c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            "https://images.unsplash.com/photo-1621687947404-e41b3d139088?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080"
        )

        val items = mutableListOf<MultiItemTypesDto>()
        items.add(
            CardSliderItem(
                imageUrls
            )
        )
        items.add(
            Item(
                "Item 1",
                "https://images.unsplash.com/photo-1621551122354-e96737d64b70?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            )
        )
        items.add(
            Item(
                "Item 2",
                "https://images.unsplash.com/photo-1621616875450-79f024a8c42c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            )
        )
        items.add(
            Item(
                "Item 3",
                "https://images.unsplash.com/photo-1621616875450-79f024a8c42c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080",
            )
        )
        items.add(
            Item(
                "Item 4",
                "https://imgur.com/Q5Lige1.jpg",
            )
        )
        items.add(
            CardSliderItem(
                imageUrls
            )
        )
        return items
    }
}