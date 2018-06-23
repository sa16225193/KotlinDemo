package com.example.liuyong.kotlindemo.data

/**
 * Created by liuyong on 2018/6/13.
 */
data class User(var id: Int, var name: String) {
    override fun equals(other: Any?): Boolean = other is User && other.id == this.id
}