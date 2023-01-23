package com.example.eltablero

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        lateinit var dibujos:Array<Int>
        var topTileX:Int =0
        var topTileY:Int =0
        var height:Int =0
        var width:Int =0
        var topElement:Int =0
        var sound:Boolean =false
        var vibration:Boolean =false
        lateinit var ids:Array<IntArray>
        lateinit var values:Array<IntArray>
        var contador:Int =0
        lateinit var mp: MediaPlayer
        lateinit var vibrator: Vibrator
        lateinit var texto: TextView
        lateinit var x: IntArray
        lateinit var y: IntArray

        lateinit var binding: CeldaviewBinding
          override fun onCreate(savedInstanceState: Bundle?) {
                  super.onCreate(savedInstanceState)
                  val miTablero = binding.tablero
                  val miCrono:Chronometer=binding.cronometro
                  miCrono.start()
                  vibrator= getSystemService(Context.VIBRATOR_SERVICE) as Vibrator //Puede que no funcione, revisar
                  binding=CeldaviewBinding.inflate(layoutInflater)
                  val bundle=intent.extras
                  val dm:DisplayMetrics=resources.displayMetrics
                  height=(dm.heightPixels-180)/topTileX!!
                  width=dm.heightPixels/topTileX!!
                  x= IntArray(topTileX)
                  y= IntArray(topTileY)
                  values = arrayOf(x, y)//Array bidimensional
                  ids=arrayOf(x,y)
                  //Crear array bidimensional con dos arraylist 5.5.2
                  if (bundle != null) {
                          topTileX=bundle.getInt("columnas") //Si no funciona comprobar si
                          topTileY=bundle.getInt("filas") //lo que se pasa en el bundle esta bien
                          topElement=bundle.getInt("elemTop")
                          vibration=bundle.getBoolean("vibracion")
                          sound=bundle.getBoolean("sonido")
                          if(bundle.getString("modo").equals("Colores")){

                          }else{

                          }
                  }
                  var ident =0
                  val miLin = binding.tablero
                  for(i in 0..topTileY){
                          val l2:LinearLayout = LinearLayout(this)
                          l2.orientation=LinearLayout.HORIZONTAL
                          for (j in 0..topTileX){
                                  val tramaToShow = miRandom()
                                  values[j][i] = tramaToShow
                                  val tv:CeldaView = CeldaView(this,j,i,topElement,
                                  tramaToShow,dibujos[tramaToShow])
                                ident++
                                  tv.id=ident
                                  ids[j][i]=ident
                                  tv.layoutParams= LinearLayout.LayoutParams(0,height,1.0f)
                                  //Sin setOnClickListener
                                  tv.setOnClickListener {

                                  }
                                  l2.addView(tv)
                          }
                          miLin.addView(l2)
                  }
                  setContentView(binding.root)
                  miTablero.removeAllViews()

          }
        fun miRandom():Int{
                var numAl:Int
                numAl= Random.nextInt(0,topElement);
                return numAl;
        }
}