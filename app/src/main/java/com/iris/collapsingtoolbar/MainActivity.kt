package com.iris.collapsingtoolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.collapsingtoolbar.R
import com.example.collapsingtoolbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _binding = ActivityMainBinding.inflate(layoutInflater)
//        setSupportActionBar(_binding.toolbar)
    }
}