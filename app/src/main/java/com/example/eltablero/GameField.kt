package com.example.eltablero

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.LinearLayout
import android.widget.TextView
import com.example.eltablero.databinding.CeldaviewBinding
import kotlin.random.Random


class GameField : AppCompatActivity() {
        var colores:Array<Int> = arrayOf( Color.BLACK,Color.GRAY,Color.CYAN,Color.GREEN,Color.BLUE,Color.RED,Color.YELLOW)
        var numeros:Array<Int> = arrayOf(1,2,3,4,5,6)
        lateinit var dibujos:Array<Int>
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
        lateinit var modo:String


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
                        modo = bundle.getString("modo").toString()
                        if(modo.equals("Colores")){
                                dibujos=colores
                        }
                        else{
                                println(modo)
                                dibujos=numeros
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
                                val tramaToShow = miRandom()

                                values[j][i] = tramaToShow
                                var tv:CeldaView = CeldaView(this,j,i,tramaToShow,topElement,
                                        dibujos[tramaToShow])
                                if(modo.equals("Colores")){
                                        tv.setBackgroundColor(dibujos[tramaToShow])
                                }
                                else{
                                        println(modo)
                                        tv.text=dibujos[tramaToShow].toString()
                                }

                                ident++
                                tv.id=ident
                                ids[j][i]=ident



                                tv.layoutParams= LinearLayout.LayoutParams(0,height,1.0f)
                                if(sound){
                                mp=MediaPlayer.create(applicationContext,R.raw.lego)}

                                //Sin setOnClickListener
                                tv.setOnClickListener {
                                        if(vibration){
                                                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.EFFECT_TICK))
                                        }

                                        if(sound){
                                                mp.start()
                                        }


                                        changeView(j,i)

                                        if(j==0 && i==0){
                                                changeView(0,0)
                                                changeView(0,1)
                                                changeView(1,0)
                                                changeView(1,1)
                                        }
                                        else if(j==0 && i==topTileY-1){
                                                changeView(0,topTileY-2)
                                                changeView(1,topTileY-2)
                                                changeView(1,topTileY-1)
                                        }
                                        else if(j == topTileX-1 && i==0){
                                                changeView(topTileX-2,0)
                                                changeView(topTileX-2,1)
                                                changeView(topTileX-1,1)

                                        }
                                        else if(j==topTileX-1&& i == topTileY-1){
                                                changeView(topTileX-2,topTileY-1)
                                                changeView(topTileX-2,topTileY-2)
                                                changeView(topTileX-1,topTileY-2)
                                        }
                                        else if(i==0){

                                                changeView(j-1,i)
                                                changeView(j+1,i)
                                                changeView(j,i+1)
                                        }
                                        else if(j==0){

                                                changeView(j,i-1)
                                                changeView(j,i+1)
                                                changeView(j+1,i)
                                        }

                                        else if(j==topTileX-1){
                                                changeView(j,i-1)
                                                changeView(j,i+1)
                                                changeView(j-1,i)
                                        }
                                        else if(i==topTileY-1){
                                                changeView(j-1,i)
                                                changeView(j+1,i)
                                                changeView(j,i-1)
                                        }

                                        else{
                                                changeView(j-1,i)
                                                changeView(j+1,i)
                                                changeView(j,i-1)
                                                changeView(j,i+1)
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
                println(" "+x+" "+y)
                var newIndex = tt.getNewIndex(values[x][y])
                values[x][y] = newIndex
                println(newIndex)
                if(modo.equals("Colores")){
                        tt.setBackgroundColor(dibujos[newIndex])
                }
                else{
                       tt.text=dibujos[newIndex].toString()
                }

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