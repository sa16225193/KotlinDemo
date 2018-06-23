package com.example.liuyong.kotlindemo.widget

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.liuyong.kotlindemo.R
import kotlinx.android.synthetic.main.activity_check_box.*

class CheckBoxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_box)
        ck_select.isChecked = false
        ck_select.setOnCheckedChangeListener { buttonView, isChecked ->
            tv_select.text = "您${ if (isChecked) "勾选" else "取消勾选"}了复选框"
        }
    }
}
