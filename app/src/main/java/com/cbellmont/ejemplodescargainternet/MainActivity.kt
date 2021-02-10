package com.cbellmont.ejemplodescargainternet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cbellmont.ejemplodescargainternet.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

interface MainActivityInterface {
    suspend fun onFilmsReceived(listITunes : List<iTunes>)
}

// IMPORTANT: Passing the activity to a the receiver is not a good practice, it may cause issues
// with the activity-s lifecycle. We are doing it just to keep the focus on the target of this example
class MainActivity : AppCompatActivity(), MainActivityInterface {
    companion object {
        const val VAR2= "VARIABLE2"
    }
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setContentView(R.layout.activity_main)

        var cambiar=""
        CoroutineScope(Dispatchers.IO).launch{
                GetAllTracks.send(this@MainActivity,cambiar)
        }
        buttonSecondAct.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra(SecondActivity.VAR1,"hola")
            startActivity(intent)
        }
        val vuelta= intent.getStringExtra(VAR2)
        vuelta.let {
            cambiar= vuelta.toString()
        }

    }

    override suspend fun onFilmsReceived(listITunes : List<iTunes>) {
        withContext(Dispatchers.Main){
            binding.tvFilms.text = ""
            listITunes.forEach {
                tvFilms.append(it.toString())
            }
        }

    }
}