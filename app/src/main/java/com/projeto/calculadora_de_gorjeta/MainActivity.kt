package com.projeto.calculadora_de_gorjeta

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

        val adapter =  ArrayAdapter.createFromResource(
            this,
            R.array.num_people,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerNumberOfPeople.adapter = adapter

        var numofpeopleselected  =  0

        binding.spinnerNumberOfPeople.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                numofpeopleselected = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.btnDone.setOnClickListener {
            val totalTableTemp = binding.tieTotal.text
                if (totalTableTemp?.isEmpty() == true ){
                    Snackbar.make(
                        binding.tieTotal,
                        "preencha o campo vazio",
                        Snackbar.LENGTH_LONG
                    ).show()
                }else{
                    val totalTable: Float = totalTableTemp.toString().toFloat()
                    val nPeople: Int = numofpeopleselected

                    val totalTemp  = totalTable / nPeople
                    val tips = totalTemp * percentage / 100
                    val totalWithChip = totalTemp + tips

                    val intent = Intent(this,SummaryActivity::class.java)
                    intent.apply {
                        putExtra("totalTable", totalTable)
                        putExtra("numPeople", numofpeopleselected)
                        putExtra("percentage", percentage)
                        putExtra("totalAmount", totalWithChip)
                    }

                    startActivity(intent)
                }

                binding.btnClean.setOnClickListener {
                    binding.tieTotal.setText("")
                    binding.rbOptionThree.isChecked = false
                    binding.rbOptionTwo.isChecked = false
                    binding.rbOptionOne.isChecked = false
                }
        }
    }
}