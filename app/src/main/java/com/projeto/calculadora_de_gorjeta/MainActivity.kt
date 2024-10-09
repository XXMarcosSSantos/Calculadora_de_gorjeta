package com.projeto.calculadora_de_gorjeta

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.projeto.calculadora_de_gorjeta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var percentage: Int = 0

        binding.rbOptionOne.setOnCheckedChangeListener { _, isChecked ->
            println("marcos $isChecked")
            if (isChecked)
                percentage = 10
        }

        binding.rbOptionTwo.setOnCheckedChangeListener { _, isChecked ->
            println("marcos two $isChecked")
            if (isChecked)
                percentage = 15
        }

        binding.rbOptionThree.setOnCheckedChangeListener { _, isChecked ->
            println("marcos three$isChecked")
            if (isChecked)
                percentage = 20
        }

        binding.btnClean.setOnClickListener{
        println("marcos Option one: " + binding.tieTotal.text)
        println("marcos" + binding.tieNumPeople.text)
        }

        binding.btnDone.setOnClickListener {
            val totalTableTemp = binding.tieTotal.text
            val npeopleTemp = binding.tieNumPeople.text
                if (totalTableTemp?.isEmpty() == true || npeopleTemp?.isEmpty() == true){
                    Snackbar.make(
                        binding.tieTotal,
                        "preencha o campo vazio",
                        Snackbar.LENGTH_LONG
                    ).show()
                }else{
                    val totalTable: Float = totalTableTemp.toString().toFloat()
                    val nPeople: Int = npeopleTemp.toString().toInt()

                    val totalTemp  = totalTable / nPeople
                    val tips = totalTemp * percentage / 100
                    val totalWithChip = totalTemp + tips
                    binding.tvResult.text = "total with tips: $totalWithChip"
                }



        }
    }
}