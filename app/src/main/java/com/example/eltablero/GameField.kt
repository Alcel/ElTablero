package com.example.eltablero

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.os.Vibrator
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.LinearLayout
import android.widget.TextView
import com.example.eltablero.databinding.CeldaviewBinding
import kotlin.random.Random


class GameField : AppCompatActivity() {
        lateinit var colores:Array<String>
        lateinit var numeros:Array<String>
        var dibujos:Array<Int> = arrayOf( Color.BLACK,Color.GRAY,Color.CYAN,Color.GREEN,Color.BLUE)
        var topTileX:Int =3
        var topTileY:Int =3
        var height:Int =0
        var width:Int =0
        var topElement:Int =3
        var sound:Boolean =false
        var vibration:Boolean =false
        lateinit var ids:Array<IntArray>
        lateinit var values:Array<IntArray>
        var contador:Int =0
        lateinit var mp: MediaPlayer
        lateinit var vibrator: Vibrator
        lateinit var texto: TextView
        lateinit var miCrono:Chronometer


        lateinit var binding: CeldaviewBinding
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                binding=CeldaviewBinding.inflate(layoutInflater)

                val miTablero = binding.tablero


                vibrator= getSystemService(Context.VIBRATOR_SERVICE) as Vibrator //Puede que no funcione, revisar
                binding=CeldaviewBinding.inflate(layoutInflater)
                val bundle=intent.extras
                val dm:DisplayMetrics=resources.displayMetrics
                height=(dm.heightPixels-1300)/topTileX!!
                width=dm.heightPixels/topTileX!!



                if (bundle != null) {
                        topTileX=bundle.getInt("columnas")
                        topTileY=bundle.getInt("filas")
                        topElement=bundle.getInt("elemTop")
                        vibration=bundle.getBoolean("vibracion")
                        sound=bundle.getBoolean("sonido")
                        if(bundle.getString("modo").equals("Colores")){

                        }else{

                        }
                }
                values = Array(topTileX){ IntArray(topTileY) }//Array bidimensional
                ids=Array(topTileX){IntArray(topTileY)}
                var ident =0
                val miLin = binding.tablero
                for(i in 0..topTileY-1){
                        val l2:LinearLayout = LinearLayout(this)
                        l2.orientation=LinearLayout.HORIZONTAL
                        for (j in 0..topTileX-1){
                                println("j = "+j)
                                println("topX = "+topTileX)
                                val tramaToShow = miRandom()
                                println("["+j+"]"+"["+i+"]")
                                values[j][i] = tramaToShow
                                val tv:CeldaView = CeldaView(this,j,i,topElement,
                                        tramaToShow,dibujos[tramaToShow])
                                ident++
                                tv.id=ident
                                ids[j][i]=ident
                                tv.layoutParams= LinearLayout.LayoutParams(0,height,1.0f)
                                //Sin setOnClickListener
                                tv.setOnClickListener {

                                        if(i==0 && j==0){
                                                changeView(0,1)
                                                changeView(1,0)
                                                changeView(1,1)
                                        }
                                        else if(i==0 && j==topTileY-1){
                                                changeView(0,topTileY-2)
                                                changeView(1,topTileY-2)
                                                changeView(0,topTileY-1)
                                        }
                                        else if(i==topTileX-1&& j == topTileY-1){
                                                changeView(topTileX-2,topTileY-1)
                                                changeView(topTileX-2,topTileY-2)
                                                changeView(topTileX-1,topTileY-2)
                                        }
                                        else if(i==0){
                                                changeView(i,j-1)
                                                changeView(i,j+1)
                                                changeView(i+1,j)
                                        }
                                        else if(j==0){
                                                changeView(i-1,j)
                                                changeView(i+1,j)
                                                changeView(i,j+1)
                                        }
                                        else if(j==topTileX-1){
                                                changeView(i,j-1)
                                                changeView(i,j+1)
                                                changeView(i-1,j)
                                        }
                                        else if(j==topTileY-1){
                                                changeView(i-1,j)
                                                changeView(i+1,j)
                                                changeView(i,j-1)
                                        }
                                        else{
                                                changeView(i-1,j)
                                                changeView(i+1,j)
                                                changeView(i,j-1)
                                                changeView(i,j+1)
                                        }
                                        contador++
                                        binding.pulsaciones.setText(contador.toString())
                                        checkIfFinished()


                                }
                                l2.addView(tv)
                        }
                        miLin.addView(l2)
                }
                setContentView(binding.root)
                binding.cronometro.start()
                miTablero.removeAllViews()

        }
        fun miRandom():Int{
                var numAl:Int
                numAl= Random.nextInt(0,topElement);
                return numAl;
        }
        fun changeView(x:Int, y:Int){
                val tt:CeldaView= findViewById(ids[x][y])
                var newIndex = tt.getNewIndex()
                values[x][y] = newIndex
                tt.backgroundView=dibujos[newIndex]
                tt.invalidate()

        }
        fun checkIfFinished(){
                val valor = values[0][0]
                for(i in 0..topTileY-1){
                        for(j in 0..topTileX-1)
                                if (values[j][i] !=valor) return;
                }
        }

}