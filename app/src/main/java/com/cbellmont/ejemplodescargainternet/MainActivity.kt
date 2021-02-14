package com.cbellmont.ejemplodescargainternet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        const val GRUPO_MUSICA= "VARIABLE2"
        const val SECOND_ACTIVITY_CODE = 9742
    }
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        CoroutineScope(Dispatchers.IO).launch{
                GetAllTracks.send(this@MainActivity,"radiohead")
        }

        binding.buttonSecondAct.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra(SecondActivity.VAR1,"hola")
            startActivityForResult(intent, SECOND_ACTIVITY_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SECOND_ACTIVITY_CODE && resultCode == RESULT_OK) {
            Log.e("MAINACTIVITY","El Result funciona")
            data?.let {
                var a = it.getStringExtra(GRUPO_MUSICA)
                a?.let {
                    CoroutineScope(Dispatchers.IO).launch{
                        GetAllTracks.send(this@MainActivity,a)
                    }
                }
            }
            return
        }
        Log.e("MAINACTIVITY","Algo ha fallado")

    }

    override suspend fun onFilmsReceived(listITunes : List<iTunes>) {
        withContext(Dispatchers.Main){
            binding.tvFilms.text = ""
            listITunes.forEach {
                binding.tvFilms.append(it.toString())
            }
        }

    }
}