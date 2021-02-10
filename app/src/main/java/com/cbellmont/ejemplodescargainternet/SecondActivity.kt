package com.cbellmont.ejemplodescargainternet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.second_activity_main.*


class SecondActivity : AppCompatActivity() {

    companion object {
        const val VAR1= "VARIABLE1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity_main)
        buttonVolver.setOnClickListener {
            if (textView1.text.toString().isEmpty()){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error")
                builder.setMessage("No has introducido nada")
                builder.setPositiveButton("Aceptar",null)
                val dialog:AlertDialog = builder.create()
                dialog.show()

            }else{
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra(MainActivity.VAR2,textView1.text.toString())
                startActivity(intent)
            }
        }
    }
}