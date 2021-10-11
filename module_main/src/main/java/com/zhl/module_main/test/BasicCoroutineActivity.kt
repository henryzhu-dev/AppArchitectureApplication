package com.zhl.module_main.test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zhl.module_main.databinding.ActCoroutineTestBinding
import kotlinx.coroutines.*

/**
 *    author : zhuhl
 *    date   : 2021/8/5
 *    desc   :
 *    refer  :
 */
class BasicCoroutineActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    lateinit var inflate: ActCoroutineTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflate = ActCoroutineTestBinding.inflate(layoutInflater)
        setContentView(inflate.root)

        inflate.mainScope.setOnClickListener {
            launchFromMainScope()
        }
    }

    private fun launchFromMainScope() {
        launch {
            val deferred = async(Dispatchers.IO) {
                delay(3000)
                "Get it"
            }
            inflate.mainScope.text = deferred.await()
            Toast.makeText(applicationContext, "MainScope", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}