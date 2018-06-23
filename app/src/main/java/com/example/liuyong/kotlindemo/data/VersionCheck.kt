package com.example.liuyong.kotlindemo.data

data class VersionCheck(var app_name: String="", var package_name: String="",
                        var version_code: Int=0, var version_name: String="",
                        var need_update: Boolean=false, var download_url: String="",
                        var local_path: String="") {
}
