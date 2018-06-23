package com.example.liuyong.kotlindemo

import com.example.liuyong.kotlindemo.data.LifeItem.Companion.default
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * Created by liuyong on 2018/6/8.
 */
object DateUtil {
    val nowDateTime: String
        get() = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())

    val nowDate: String
        get() = SimpleDateFormat("yyyy-MM-dd").format(Date())

    val nowTime: String
        get() = SimpleDateFormat("HH:mm:ss").format(Date())

    val nowTimeDetail: String
        get() = SimpleDateFormat("HH:mm:ss.SSS").format(Date())

    var number: Int = 1

    //根据指定格式返回日期时间字符串
    fun getFormatTime(format: String = ""): String {
        return if (format.isNotEmpty()) SimpleDateFormat(format).format(Date())
                        else SimpleDateFormat("yyyyMMddHHmmss").format(Date())
    }

    fun formatDate(long: Long): String {return ""}


}

//在类名后面声明主构造函数
open class Animal constructor(name: String){

    var name = name
    var sex = 0
    var age = 0
    var color: Int?
    var food: String? = null

    init {
        println(name)
        color = 0
    }

    constructor(name: String, sex: Int): this(name) {
        this.sex = sex
        println(name)
    }

    constructor(name: String, sex: Int, age: Int): this(name) {
        this.sex = sex
        this.age = age
        if (sex == 0) println("$name is 雄性") else println("$name is 雌性")
    }

    protected open fun getAnimalInfo(): String {
        return "name is $name, sex is $sex, age is $age, color is $color , food is $food"
    }

    companion object CommonAnimal{

        val MALE = 0
        val FEMALE = 1
        val UNKNOWN = -1

        var sex = 0

        fun judgeSex(sexName: String): Int {
            return when (sexName) {
                "公","雄" -> MALE
                "母","雌" -> FEMALE
                else -> UNKNOWN
            }
        }
    }
}

interface Behavior {

    fun run()

    fun fly()
}

class BehaviorRun(name:String): Behavior {

     var name = name

    override fun run() {
       println("$name can run")
    }

    override fun fly() {
        println("$name can not fly")
    }
}

class BehaviorFly(name:String): Behavior{

    var name = name

    override fun run() {
        println("$name can not run")
    }

    override fun fly() {
        println("$name can fly")
    }
}

class Bird(behavior: Behavior): Behavior by behavior {

}

class Tigger(behavior: Behavior): Behavior by behavior {

}

class Tree(var treeName: String) {

    //在类的内部在定义一个嵌套类
    inner class Flower(var flowerName: String) {

        fun getName(): String {
//            return "这是一朵$flowerName"

            //嵌套类不能直接访问外部类的成员
            return "这是${treeName}上的一朵$flowerName"
        }
    }

}

enum class Season {
    SPRING, SUMMER, AUTUMN, WINTER
}

enum class SeasonWithName(val season: String) {
    SPRING("春天"),
    SUMMER("夏天"),
    AUTUMN("秋天"),
    WINTER("冬天")
}

//使用sealed关键字声明密封类
sealed class SeasonSealed {
    //密封类内部的每个嵌套类都必须继承该类
    class Spring(var name: String): SeasonSealed()
    class Summer(var name: String): SeasonSealed()
    class Autumn(var name: String): SeasonSealed()
    class Winter(var name: String): SeasonSealed()
}

//必须有带输入参数的主构造函数
//输入参数由var或val关键字声明
//数据类不能是基类或者子类，也不能是抽象类、内部类、密封类
//使用data关键字声明数据类Plant
data class Plant(var name: String, var stem: String, var leaf: String,
                 var flower: String, var fruit: String, var seed: String) {

}

class River<T> (var name: String, var length: T) {

    fun getInfo(): String {
        var unit: String = when(length) {
            is String -> "米"
            is Number -> "m"
            else -> "meters"
        }
        return "${name}的长度是$length$unit"
    }

}

class Example {
    //属性p通过代理类Delegate来获取和赋值
    var p: String by Delegate()
//    operator fun Delegate.getValue(thisRef: Any?, property: KProperty<*>): String {
//        return "$thisRef, thank you for delegating '${property.name}' to me!"
//    }
//
//    operator fun Delegate.setValue(thisRef: Any?, property: KProperty<*>, value: String) {
//        println("$value has been assigned to '${property.name} in $thisRef.'")
//    }
}

class Delegate {

    /**
     * 接管属性值的获取行为
     *
     * thisRef : 属性所在的类
     * property : 属性的描述,这个参数的类型必须是 KProperty<*> , 或者是它的基类,
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    /**
     * 接管属性值的修改行为
     * value : 属性修改后的值
     */
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name} in $thisRef.'")
    }
}


