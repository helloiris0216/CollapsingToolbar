package com.iris.collapsingtoolbar

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collapsingtoolbar.databinding.ActivityMainBinding
import com.example.collapsingtoolbar.databinding.ViewSearchBarBinding
import com.google.android.material.appbar.AppBarLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var searchBarBinding: ViewSearchBarBinding
    private lateinit var listAdapter: HomeGameListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            }
        })

        searchBarBinding = binding.searchLayout
        searchBarBinding.viewLayoutSearch.setOnClickListener {
            Toast.makeText(this, "Search bar is clicked", Toast.LENGTH_LONG).show()
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