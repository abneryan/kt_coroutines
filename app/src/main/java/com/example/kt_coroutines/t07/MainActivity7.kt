package com.example.kt_coroutines.t07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.kt_coroutines.R
import com.example.kt_coroutines.databinding.ActivityMain2Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
    阻塞与挂起
        1，挂起--非阻塞   一号人物，被拎出去，睡觉20s ,不会影响后面的2，3，4 人物，更加不会影响阻塞Android主线程
        2，阻塞          一号人物，直接在原地睡觉，影响阻塞2，3，4 人物，会影响阻塞 Android主线程，所以Android
            Button 白了，多次点击会ANR 未响应
 */
class MainActivity7 : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main2)

        mainBinding.btLogin.setOnClickListener {
            //阻塞演示
            /*Thread.sleep(20000)
            Toast.makeText(this@MainActivity7,"一号人物",Toast.LENGTH_LONG).show()
            Toast.makeText(this@MainActivity7,"二号人物",Toast.LENGTH_LONG).show()
            Toast.makeText(this@MainActivity7,"三号人物",Toast.LENGTH_LONG).show()
            Toast.makeText(this@MainActivity7,"四号人物",Toast.LENGTH_LONG).show()*/
            GlobalScope.launch(Dispatchers.Main){
                delay(20000)
                runOnUiThread {
                    Toast.makeText(this@MainActivity7,"一号人物",Toast.LENGTH_LONG).show()
                }
            }
            Toast.makeText(this@MainActivity7,"二号人物",Toast.LENGTH_LONG).show()
            Toast.makeText(this@MainActivity7,"三号人物",Toast.LENGTH_LONG).show()
            Toast.makeText(this@MainActivity7,"四号人物",Toast.LENGTH_LONG).show()
        }
    }
}