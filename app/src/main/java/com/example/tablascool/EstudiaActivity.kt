package com.example.tablascool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_estudia.*
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.*
import java.util.*

class EstudiaActivity : AppCompatActivity(), TextToSpeech.OnInitListener, AdapterView.OnItemClickListener {
    var tts: TextToSpeech? = null
    var listaElementos: MutableList<String>?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudia)

        listaElementos = mutableListOf<String>()

        tts = TextToSpeech(this,this)
        Log.i("lenguajes", Locale.getAvailableLocales().toString())

        ListaTablas.setOnItemClickListener(this)

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                listaElementos!!.clear()
                if (p1 < 1){
                    seekBar.setProgress(1)
                }
                if (p1 > 0){

                    for (i in 0..10) {
                        val texto = "$p1 x $i = ${p1 + i}"
                        listaElementos!!.add(texto)
                    }
                }
                val adaptador = ArrayAdapter(application,android.R.layout.simple_list_item_1,listaElementos!!)

                ListaTablas.adapter = adaptador
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

        })



    }

    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            val result = tts !!.setLanguage(Locale("es_MX"))
            if (result != TextToSpeech.LANG_NOT_SUPPORTED && result != TextToSpeech.LANG_MISSING_DATA){
                Toast.makeText(this,"Todo esta bien", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Lenguaje no soportado", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        if (tts!! != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //Toast.makeText(this, "hola",Toast.LENGTH_SHORT).show()
        val textoAleer = listaElementos!!.get(p2)
        tts!!.speak(textoAleer,TextToSpeech.QUEUE_FLUSH,null,null)
    }
}
