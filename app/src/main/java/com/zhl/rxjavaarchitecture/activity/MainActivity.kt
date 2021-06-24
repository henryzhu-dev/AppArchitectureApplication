package com.zhl.rxjavaarchitecture.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhl.baselibrary.activity.BaseActivity
import com.zhl.rxjavaarchitecture.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvTest.text = "hello binding"
    }

    override fun getLayoutViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}