package com.example.eltablero

import android.content.Context
import androidx.core.view.TintableBackgroundView

class CeldaView(context:Context,x: Int, y: Int, index: Int, topElementos: Int,backgroundView: Int): androidx.appcompat.widget.AppCompatButton(context) {
    var x:Int =x
    var y:Int =y
    var index:Int =index
    var topElementos:Int=topElementos
     var backgroundView: Int =backgroundView



    fun getNewIndex(indice:Int):Int{
        var aux=indice+1
        if(aux==topElementos){
            aux=0
        }
        return aux
    }
}