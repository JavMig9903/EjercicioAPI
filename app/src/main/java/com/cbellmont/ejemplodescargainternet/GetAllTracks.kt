package com.cbellmont.ejemplodescargainternet

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class GetAllTracks {
    companion object {
        suspend fun send(mainActivity : MainActivityInterface?,ejemplo :String) {
            val client = OkHttpClient()
            var url=""
            if (ejemplo.isEmpty()){
                url = "https://itunes.apple.com/search?term=radiohead"
            }else{
                url = "https://itunes.apple.com/search?term=$ejemplo"
            }

            val request = Request.Builder()
                .url(url)
                .build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    Log.e("GetAllTracks", call.toString())

                }

                override fun onResponse(call: Call, response: Response) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val bodyInString = response.body?.string()
                        bodyInString?.let {
                            Log.w("GetAllTracks", bodyInString)
                            val JsonObject = JSONObject(bodyInString)

                            val results = JsonObject.optJSONArray("results")
                            results?.let {
                                Log.w("GetAllTracks", results.toString())
                                val gson = Gson()

                                val itemType = object : TypeToken<List<iTunes>>() {}.type

                                val list = gson.fromJson<List<iTunes>>(results.toString(), itemType)

                                mainActivity?.onFilmsReceived(list)
                            }
                        }
                    }
                }
            })
        }
    }
}