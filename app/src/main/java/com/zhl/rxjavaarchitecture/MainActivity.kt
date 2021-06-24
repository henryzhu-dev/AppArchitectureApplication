package com.zhl.rxjavaarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhl.rxjavaarchitecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvTest.text = "hello binding"
    }
}