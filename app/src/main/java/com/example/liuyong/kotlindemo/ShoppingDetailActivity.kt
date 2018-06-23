package com.example.liuyong.kotlindemo

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.liuyong.kotlindemo.data.CartInfo
import com.example.liuyong.kotlindemo.database.CartDBHelper
import com.example.liuyong.kotlindemo.database.GoodsDBHelper
import com.example.liuyong.kotlindemo.util.Preference

import kotlinx.android.synthetic.main.toolbar_custom.*
import kotlinx.android.synthetic.main.activity_shopping_detail.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by ouyangshen on 2017/9/10.
 */
class ShoppingDetailActivity : AppCompatActivity() {
    private var mCount: Int by Preference(this, "count", 0)
    private var mGoodsId: Long = 0
    private lateinit var mGoodsHelper: GoodsDBHelper
    private lateinit var mCartHelper: CartDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_detail)
        setSupportActionBar(tl_head)
        tl_head.setNavigationOnClickListener { finish() }

        mGoodsHelper = GoodsDBHelper.getInstance(this)
        mCartHelper = CartDBHelper.getInstance(this)
        showDetail() //展示商品详情
        iv_cart.setOnClickListener { startActivity<ShoppingCartActivity>() }
        btn_add_cart.setOnClickListener {
            addToCart(mGoodsId) //添加购物车数据库
            toast("成功添加至购物车")
        }
    }

    private fun addToCart(goods_id: Long) {
        mCount++
        tv_count.text = mCount.toString()
        var info = mCartHelper.queryByGoodsId(goods_id)
        if (info.goods_id > 0) {
            //数据库已有商品记录，那么数量加一。否则新增一条记录。
            info.count++
            info.update_time = DateUtil.getFormatTime()
            mCartHelper.update(info)
        } else {
            info = CartInfo(count=1, goods_id=goods_id, update_time=DateUtil.getFormatTime())
            mCartHelper.insert(info)
        }
    }

    private fun showDetail() {
        tv_count.text = mCount.toString()
        mGoodsId = intent.getLongExtra("goods_id", 0L)
        if (mGoodsId > 0) {
            val info = mGoodsHelper.queryByRowid(mGoodsId)
            tv_title.text = info.name
            tv_goods_desc.text = info.desc
            tv_goods_price.text = info.price.toString()
            val pic = BitmapFactory.decodeFile(info.pic_path)
            iv_goods_pic.setImageBitmap(pic)
        }
    }

}
