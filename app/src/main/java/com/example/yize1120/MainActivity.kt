package com.example.yize1120

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.yize1120.ui.theme.Yize1120Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.logging.Logger
import kotlin.coroutines.CoroutineContext

class MainActivity : ComponentActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(CoroutinesViewModel::class.java) }
    private var textMain: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            println("当前的线程：${Thread.currentThread().name}")
        }

        initView()
        startObserver()
    }

    private fun initView() {
        val btnGetData = findViewById<Button>(R.id.btnGetData)
        btnGetData.setOnClickListener {
            viewModel.getArticles(0)
        }
        textMain = findViewById(R.id.tv_main)
    }

    private fun startObserver() {
        viewModel.articlesLiveData.observe(this, Observer { it ->
            Log.d("——————文章——————","${it.size}")
            it.run {
                if (this.size > 0){
                    val text = StringBuilder()
                    this.forEach {
                        text.append(it.title + "\n")
                    }
                    textMain?.text = text
                }
            }
        })

        viewModel.apiError.observe(this, Observer {

        })
    }


}
