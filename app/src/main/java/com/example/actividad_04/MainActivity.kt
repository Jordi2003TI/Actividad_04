package com.example.actividad_04

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var botonInici: Button
    private var currentWeight: Int = 60
    private var currentAge: Int = 18

    private var currentHeight:Int = 120

    private var isMaleSelected:Boolean= true;
    private var isFemaleSelected:Boolean= false;

    // para poder elegir entre los generos
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView

    // para poder sumar y restar a weight
    private lateinit var btnSubractWeigh: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeigh: TextView

    // para poder sumar la EDAD
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private  lateinit var tvAge: TextView

    //BOTON
    private lateinit var btnCalculate: Button


// para el slider, ponemos de lo que queremos enlazar del mismo tipo de la id
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider

    companion object{
        const val  IMC_KEY = "IMC_RESULT"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents();
        initListeners();
        initUI()

    }

    private fun initUI() {
        setGender()
        setWeight()
        setAge()
    }

    // Usamos este modulo para que podamos encontrar el elemento mediante la id y poder
    // vinularlo
    private fun initComponents(){
        viewMale = findViewById(R.id.cvMale)
        viewFemale = findViewById(R.id.cvFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnSubractWeigh = findViewById(R.id.btnSubractWeigh)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeigh = findViewById(R.id.tvWeigh)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)

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
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }
        btnSubractWeigh.setOnClickListener {
            currentWeight--
            setWeight()
        }
        btnPlusWeight.setOnClickListener {
            currentWeight++
            setWeight()
        }
        btnSubtractAge.setOnClickListener {
            currentAge--
            setAge()
        }
        btnPlusAge.setOnClickListener {
            currentAge++
            setAge()
        }
        btnCalculate.setOnClickListener {
            var result = calculateIMC()
            navigatetoResult(result)
        }
    }

    private fun navigatetoResult(result: Double){
        val intent = Intent(this, ResultIMC::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)

    }

    private fun calculateIMC(): Double{
        val df = DecimalFormat("#.##")
        val imc = currentWeight/(currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        return df.format(imc).toDouble()

    }

    private fun setAge(){
        tvAge.text = currentAge.toString()
    }

    private fun setWeight(){
        tvWeigh.text = currentWeight.toString()
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