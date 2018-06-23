package com.example.liuyong.kotlindemo.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.*

object FileUtil {

    /**
     * 保存文本
     */
    fun writeText(path: String, txt: String) {
        File(path).writeText(txt)
    }

    /**
     * 读取文本
     */
    fun readText(path: String): String = File(path).readText()

    /**
     * 保存图片
     */
    fun saveImage(path: String, bitmap: Bitmap) {
        try {
            val file = File(path)
            //outputStream获取文件的输出流对象
            //writer获取文件的Writer对象
            //printWriter获取文件的PrintWriter对象
            val fos: OutputStream = file.outputStream()
            //压缩格式为JPEG图像，压缩质量为80%
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos)
            fos.flush()
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun openImage(path: String): Bitmap {
        //方式一：利用字节数组读取
        //readBytes读取字节数组形式的文件内容
        val bytes = File(path).readBytes()
        //decodeByteArray从字节数组中解析图片
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

        //方式二：利用输入流读取
//        val fileInputStream = File(path).inputStream()
//        val bitmap = BitmapFactory.decodeStream(fileInputStream)

        //方式三：直接从文件路径读取
//        val bitmap = BitmapFactory.decodeFile(path)
        return bitmap
    }

}
