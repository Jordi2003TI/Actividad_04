package com.example.actividad_04

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.actividad_04.MainActivity.Companion.IMC_KEY

class ResultIMC : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView

    private lateinit var btnReCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imc)
        val result: Double = intent.extras?.getDouble(IMC_KEY)?: -1.0
        initComponents();
        initListeners();
        initUI(result)
    }

    private fun initComponents(){
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnReCalculate = findViewById(R.id.btnReCalculate)

    }

    private fun initListeners(){
        btnReCalculate.setOnClickListener { onBackPressed() }
    }

    private fun initUI(result:  Double){
        tvIMC.text = result.toString()
        when(result){
            in 0.00..18.50 ->{ // Peso Bajo
                tvResult.text = getString(R.string.titleBajoPeso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.pesoBajo))
                tvDescription.text = getString(R.string.descriptiocionBajoPeso)
            }
            in 18.51..24.99 ->{ // Peso Mediano
                tvResult.text = getString(R.string.titleMedioPeso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.pesoMediano))
                tvDescription.text = getString(R.string.descriptiocionMedioPeso)
            }
            in 25.00..29.99 ->{ // SobrePeso
                tvResult.text = getString(R.string.titleSobrePeso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.pesoSobrePeso))
                tvDescription.text = getString(R.string.descriptiocionSobrePeso)
            }
            in 30.00..99.99 ->{ // Obesidad
                tvResult.text = getString(R.string.titleObesidad)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.pesoObesidad))
                tvDescription.text = getString(R.string.descriptiocionObesidad)
            }
            else ->{
                tvIMC.text = getString(R.string.error)
                tvResult.text =  getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }
}