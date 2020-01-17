package com.example.formu_registro

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.formu_registro.CONSTANTES.Constantes.Companion.EMPTY
import com.example.formu_registro.CONSTANTES.Constantes.Companion.INTERLIN
import com.example.formu_registro.CONSTANTES.Constantes.Companion.SPACE
import kotlinx.android.synthetic.main.activity_registro.*
import java.text.SimpleDateFormat
import java.util.*


class Registro : AppCompatActivity() {

    private var cal = Calendar.getInstance()
    private lateinit var fecha : String
    @SuppressLint("SetTextI18n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        var sexo = getString(R.string.Mascu)
        //     val btn_registro : Button = findViewById(R.id.bt_registrar)

        et_Masculino.setOnClickListener {
            sexo = getString(R.string.Mascu)
        }

        et_Femenino.setOnClickListener {
            sexo = getString(R.string.Fem)
        }

// Date picker
        val dataSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set( Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = "MM-dd-yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                fecha = sdf.format(cal.time).toString()
                showPicker.text = fecha
            }
        }


        showPicker.setOnClickListener {
            DatePickerDialog(
                this,
                dataSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

// Spinner
 val adapter = ArrayAdapter.createFromResource(this, R.array.City_list,android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        mySpinner.adapter= adapter



// BOTON REGISTRAR


        et_registro.setOnClickListener {
            val nombre = et_name.text.toString()
            val correo = et_Email.text.toString()
            val password = et_Pass.text.toString()
            val comPassword = et_PassCom.text.toString()
            var pasatiempos = EMPTY
            val telefono = et_Num.text.toString().toInt()
            // Obtener valor del spinner
            val city = mySpinner.selectedItem.toString()



            if (cb_Trotar.isChecked) pasatiempos = pasatiempos + SPACE + getString(R.string.Trotar)
            if (cb_Nadar.isChecked) pasatiempos =
                pasatiempos + SPACE + getString(R.string.Nadar)
            if (cb_cine.isChecked) pasatiempos = pasatiempos + SPACE + getString(R.string.Cine)
            if (cb_Leer.isChecked) pasatiempos = pasatiempos + SPACE + getString(R.string.Leer)

            /*    if (rb_masculino.isChecked) sexo = "Masculino"
                else sexo = "Femenino"*/


            if (et_Masculino.isChecked()){
                sexo="Masculino"
            }else {
                sexo ="Femenino"
            }


            if (nombre.isEmpty() ||
                correo.isEmpty() ||
                et_Num.text.toString().isEmpty() ||
                password.isEmpty()            ||
                (password != comPassword)  ||
                        fecha.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    getString(R.string.msg_error_campos_vacios),
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                val telefono = et_Num.text.toString().toInt()
              //  et_result.text = getString(R.string.nombre) + SPACE + nombre +
                //        INTERLIN + getString(R.string.correo) + SPACE + correo +
               //         INTERLIN + getString(R.string.numero) + SPACE + telefono +
                 //       INTERLIN + getString(R.string.password) + SPACE + password +
                   //     INTERLIN + getString(R.string.sexo) + SPACE + sexo +
                     //   INTERLIN + getString(R.string.hobbie) + SPACE + pasatiempos +
                       // INTERLIN + getString(R.string.Fecha_Nacimiento) + SPACE + fecha

                et_result.text =  SPACE + nombre +
                        INTERLIN +  SPACE + correo +
                        INTERLIN +  SPACE + telefono +
                        INTERLIN +  SPACE + password +
                        INTERLIN +  SPACE + sexo +
                        INTERLIN +  SPACE + pasatiempos +
                        INTERLIN +  SPACE + city +
                        INTERLIN +  SPACE + fecha





            }
        }



    }



}
