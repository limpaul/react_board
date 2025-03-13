package com.raonsecure.myapplication

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject


class NetworkSetting {
    public val serverIp:String by lazy { "http://172.30.1.9" }
    public val serverPort:Int by lazy { 8080 }

    suspend fun commonSendGetToServer(url:String=serverIp, port:Int=serverPort, dataMap:Map<String, Any>): String? {
            val request = Request.Builder()
                .url(url)
                .get()
                .build()
            val client = OkHttpClient()

            val response:Response = client.newCall(request).execute()
            return response.body?.string()
    }
    suspend fun commonSendPostToServer(urlPath:String, port:Int=serverPort, dataMap:Map<String, Any>): String?{
        val client = OkHttpClient()
        val gson:Gson = Gson()
        val jsonBody:String = JSONObject(dataMap).toString()
        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()

        Log.d("bwlim", jsonBody)
        val requestBody:RequestBody = jsonBody.toRequestBody(contentType = mediaType)
        val request = Request.Builder()
            .url("$serverIp:$port$urlPath")
            .post(requestBody)
            .build()
        val response:Response = client.newCall(request).execute()

        return response.body?.string()
    }
}