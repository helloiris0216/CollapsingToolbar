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
                        windowInsetsController?.setSystemBarsAppearance(
                            0,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                        )
                    }

                    1f -> {
                        // 完全折叠 > 深色文字
                        windowInsetsController?.setSystemBarsAppearance(
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                        )
                    }
                }
            }
        }

        searchBarBinding = binding.searchLayout.apply {
            viewLayoutSearch.setOnClickListener {
                Toast.makeText(this@MainActivity, "Search bar is clicked", Toast.LENGTH_LONG).show()
            }
        }

        listAdapter = HomeGameListAdapter(getTestData).apply {
            onItemClickListener = adapterOnItemClickListener
            onCollectionClickListener = adapterOnCollectionClickListener
        }

       binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL , false)
            adapter = listAdapter
        }
    }

    private val getTestData get() =
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

    private val adapterOnCollectionClickListener = object : HomeGameListAdapter.OnCollectionClickListener {
        override fun onClick(position: Int) {

        }
    }
}