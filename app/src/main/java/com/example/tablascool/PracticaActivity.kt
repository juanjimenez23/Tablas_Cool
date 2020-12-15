package com.example.tablascool

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_practica.*
import kotlin.random.Random

class PracticaActivity : AppCompatActivity() {
    var resultado = 0
    private fun crearDialogoOk(){
        val miDialogView = LayoutInflater.from(this).inflate(R.layout.dialogo_ok,null)
        val mBuilder = AlertDialog.Builder(this).setView(miDialogView)
            .setTitle("Muy Bien!!")
        val miDialogOk = mBuilder.create()
        miDialogOk.show()
        val miPlayer:MediaPlayer? = MediaPlayer.create(this,R.raw.aplauso)
        miPlayer?.start()

    }

    private fun crearDialogoError(){
        val miDialogView = LayoutInflater.from(this).inflate(R.layout.dialogo_error,null)
        val mBuilder = AlertDialog.Builder(this).setView(miDialogView)
            .setTitle("Muy Mal!!")
        val miDialogError = mBuilder.create()
        miDialogError.show()
        val miPlayer:MediaPlayer? = MediaPlayer.create(this,R.raw.mal)
        miPlayer?.start()
    }

    private fun generaOperacion() {
        val operando1 = Random.nextInt(from = 1, until = 10)
        val operando2 = Random.nextInt(from = 1, until = 10)

        resultado = operando1*operando2
        tvPregunta.text = "$operando1 x $operando2 = ?"
        txtRespuesta.text.clear()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practica)

        generaOperacion()

        btnComprobar.setOnClickListener {
            val resText = txtRespuesta.text.toString()
            if (!resText.equals("")) {
                val respuesta = resText.toInt()
                if (respuesta==resultado){
                Log.d("resultado", "Le ateniste")
                    crearDialogoOk()
            } else {
                Log.d("resuldado", "No le atinaste")
                    crearDialogoError()

            }
            generaOperacion()
        }


    }

    }
}

