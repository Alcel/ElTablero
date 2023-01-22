package com.example.eltablero

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton

class TileView constructor(context:Context,x:Int,y:Int,topElementos:Int,index:Int,background:Int) : AppCompatButton(context) {
    val xPropia:Int =x
    val yPropia:Int=y
    val topElementosPropios:Int = topElementos
    var indexPropia:Int = index
    val backgroundPropia:Int = background

    fun getNewIndex():Int{
        indexPropia++
        if(indexPropia==topElementosPropios){
            indexPropia=0
        }
        return indexPropia
    }

}