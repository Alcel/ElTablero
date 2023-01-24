package com.example.eltablero

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Chronometer
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eltablero.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var barra1:SeekBar
    lateinit var barra2:SeekBar
    lateinit var barra3:SeekBar
    lateinit var textProgress1:TextView
    lateinit var textProgress2:TextView
    lateinit  var textProgress3:TextView
    var eleccion =""
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

        val grupoRadio = binding.grupo



        textProgress1.setText(" "+barra1.progress.toString())
        textProgress2.setText(" "+barra2.progress.toString())
        textProgress3.setText(" "+barra3.progress.toString())
        barra1.min=3
        barra1.max=barra1.max //Barra 1 tiene el mismo valor maximo que barra 2 y 3
        barra2.min=barra1.min
        barra3.min=barra1.min

        grupoRadio.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                eleccion = radio.text as String
            })

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
        binding.playButton.setOnClickListener {startPlay()  }
    }
    fun startPlay(){
        val inten: Intent=Intent(this,GameField::class.java)

        inten.putExtra("columnas",barra1.progress)
        inten.putExtra("filas",barra2.progress)
        inten.putExtra("elemTop",barra3.progress)
        inten.putExtra("vibracion",binding.vibrationCheckBox.isChecked)
        inten.putExtra("sonido",binding.soundCheckBox.isChecked)
        inten.putExtra("modo",eleccion)

        startActivity(inten)

    }
    fun updateXTiles(progreso:Int){
        textProgress1.setText(" "+progreso.toString())
    }
    fun updateYTiles(progreso:Int){
        textProgress2.setText(" "+progreso.toString())
    }
    fun updateColores(progreso:Int){
        textProgress3.setText(" "+progreso.toString())
    }
    override fun  onCreateOptionsMenu(menu: Menu):Boolean{
        val inflado:MenuInflater = menuInflater
        inflado.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
       when(menuItem.itemId){
           (R.id.item1) -> mostrarPlayer()
           (R.id.item2) -> mostrarHowTo()
           (R.id.item3) -> mostrarAbout()
       }
        return true
    }

    fun mostrarPlayer(){


    }

    fun mostrarHowTo(){
        val inten: Intent=Intent(this,HowTo::class.java)
        startActivity(inten)
    }

    fun mostrarAbout(){
        val inten: Intent=Intent(this,About::class.java)
        startActivity(inten)
    }

}