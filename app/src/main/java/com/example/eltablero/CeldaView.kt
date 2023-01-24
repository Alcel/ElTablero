package com.example.eltablero

import android.content.Context
import androidx.core.view.TintableBackgroundView

class CeldaView(context:Context,x: Int, y: Int, index: Int, topElementos: Int,backgroundView: Int): androidx.appcompat.widget.AppCompatButton(context) {
    var x:Int =0
    var y:Int =0
    var index:Int =0
    var topElementos:Int=0
     var backgroundView: Int =0


    fun getNewIndex():Int{
        var indexAux=index+1
        if(indexAux==topElementos){
            indexAux=0
        }
        return indexAux
    }
}