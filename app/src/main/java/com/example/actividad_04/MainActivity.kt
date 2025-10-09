package com.example.actividad_04

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.RangeSlider

class MainActivity : AppCompatActivity() {

    lateinit var botonInici: Button

    private var isMaleSelected:Boolean= true;
    private var isFemaleSelected:Boolean= false;


    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView

// para el slider, ponemos de lo que queremos enlazar del mismo tipo de la id
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents();
        initListeners();
        initUI()

    }

    private fun initUI() {
        setGender()
    }

    // Usamos este modulo para que podamos encontrar el elemento mediante la id y poder
    // vinularlo
    private fun initComponents(){
        viewMale = findViewById(R.id.cvMale)
        viewFemale = findViewById(R.id.cvFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)

    }

    private fun initListeners(){
        viewMale.setOnClickListener {
            changeGnder()
            setGender()
        }
        viewFemale.setOnClickListener {
            changeGnder()
            setGender()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            val result = df.format(value)
            tvHeight.text = "$result cm"
        }
    }
    private fun changeGnder(){
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }
    private fun setGender(){

        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(selected: Boolean): Int{

        val colorReference = if(selected){
            R.color.colorSelecionado
        }else{
            R.color.colorPredeterminado
        }

        return ContextCompat.getColor(this, colorReference)
    }


}