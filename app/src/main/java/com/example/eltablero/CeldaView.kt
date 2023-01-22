package com.example.eltablero

import android.content.Context
import androidx.core.view.TintableBackgroundView

class CeldaView {
    var x:Int =0
    var y:Int =0
    var index:Int =0
    var topElementos:Int=0
    lateinit var context: Context
    lateinit var backgroundView: TintableBackgroundView

    constructor(context:Context,x: Int, y: Int, index: Int, topElementos: Int,backgroundView: TintableBackgroundView) {
        this.x = x
        this.y = y
        this.index = index
        this.topElementos = topElementos
        this.context=context
        this.backgroundView=backgroundView
    }
    fun getNewIndex():Int{
        var indexAux=index+1
        if(indexAux==topElementos){
            indexAux=0
        }
        return indexAux
    }
}