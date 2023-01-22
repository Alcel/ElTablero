package com.example.eltablero

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.util.DisplayMetrics
import android.widget.TextView
import com.example.eltablero.databinding.CeldaviewBinding


class GameField : AppCompatActivity() {
        lateinit var colores:Array<String>
        lateinit var numeros:Array<String>
        lateinit var dibujos:Array<Int>
        var topTileX:Int =0
        var topTileY:Int =0
        var maxH:Int =0
        var maxV:Int =0
        var topElement:Int =0
        var sound:Boolean =false
        var vibration:Boolean =false
        lateinit var ids:Array<Int>
        lateinit var values:Array<Int>
        var contador:Int =0
        lateinit var mp: MediaPlayer
        lateinit var vibrator: Vibrator
        lateinit var texto: TextView

        lateinit var binding: CeldaviewBinding
          override fun onCreate(savedInstanceState: Bundle?) {
                  super.onCreate(savedInstanceState)
                  val miTablero = binding.tablero
                  vibrator= getSystemService(Context.VIBRATOR_SERVICE) as Vibrator //Puede que no funcione, revisar
                  binding=CeldaviewBinding.inflate(layoutInflater)
                  val bundle=intent.extras
                  val dm:DisplayMetrics=resources.displayMetrics
                  maxH=(dm.heightPixels-180)/topTileX!!
                  maxV=dm.heightPixels/topTileX!!
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
                  setContentView(binding.root)
                  miTablero.removeAllViews()

          }
}