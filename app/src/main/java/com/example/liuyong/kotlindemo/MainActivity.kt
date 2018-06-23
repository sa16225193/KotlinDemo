package com.example.liuyong.kotlindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    inline fun <T> appendString(tag: String, vararg otherInfo: T?): String {
        var str: String = "$tag: "
        for (item in otherInfo) {
            str = "$str${item.toString()}, "
        }
        return str
    }

    fun <T> function(arg: T) {

    }

    tailrec fun findFixPoint(x: Double = 1.0):Double = if (x == Math.cos(x)) x else findFixPoint(Math.cos(x))


}
