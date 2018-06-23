package com.example.liuyong.kotlindemo.widget

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.liuyong.kotlindemo.R
import com.example.liuyong.kotlindemo.data.User

import kotlinx.android.synthetic.main.activity_spinner_dialog.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/3.
 */
class SpinnerDialogActivity : AppCompatActivity() {
    private val satellites = listOf("水星", "金星", "地球", "火星", "木星", "土星")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner_dialog)

        //1. 使用Spinner
        initSpinner()
        //2. 使用selector
        initSelector()

        var userNull: User? = null
        var userOne = User(2, "Tom")
        var userTwo = User(2, "Jack")
        toast("userNull == userOne ? ${userNull == userOne}")
        toast("userOne == userTwo ? ${userOne == userTwo}")
        Log.d("AAA", "userNull == userOne ? ${userNull == userOne}")
        Log.d("AAA", "userOne == userTwo ? ${userOne == userTwo}")
    }

    private fun initSelector() {
        sp_dialog.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = satellites[0]
        tv_spinner.setOnClickListener {
            selector("请选择行星", satellites) { i ->
                tv_spinner.text = satellites[i]
                toast("你选择的行星是${tv_spinner.text}")
            }
        }
    }

    private fun initSpinner() {
        val starAdapter = ArrayAdapter(this, R.layout.item_select, satellites)
        starAdapter.setDropDownViewResource(R.layout.item_dropdown)
        val sp = findViewById<View>(R.id.sp_dialog) as Spinner
        sp.prompt = "请选择行星"
        sp.adapter = starAdapter
        sp.setSelection(0)
        sp.onItemSelectedListener = MySelectedListener()
    }

    internal inner class MySelectedListener: AdapterView.OnItemSelectedListener {

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            toast("你选择的行星是${satellites[position]}")
        }

    }
}
