package com.example.eltablero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import com.example.eltablero.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var barra1:SeekBar
    lateinit var barra2:SeekBar
    lateinit var barra3:SeekBar
    lateinit var textProgress1:TextView
    lateinit var textProgress2:TextView
    lateinit  var textProgress3:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         barra1 = binding.seekBar1
         barra2 = binding.seekBar2
         barra3 = binding.seekBar3
         textProgress1 = binding.textProgress1
        textProgress2 = binding.textProgress2
         textProgress3 = binding.textProgress3


        barra1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                updateXTiles(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        barra2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                updateYTiles(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        barra3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                updateColores(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
    }
    fun startPlay(){

    }
    fun updateXTiles(progreso:Int){
        textProgress1.setText(progreso.toString())
    }
    fun updateYTiles(progreso:Int){
        textProgress2.setText(progreso.toString())
    }
    fun updateColores(progreso:Int){
        textProgress3.setText(progreso.toString())
    }

}