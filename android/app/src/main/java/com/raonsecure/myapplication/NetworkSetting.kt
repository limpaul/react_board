package com.raonsecure.myapplication

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NetworkSetting {
    public val serverIp:String by lazy { "10.47.17.169" }
    public val serverPort:Int by lazy { 8080 }

    fun commonSendGetToServer(url:String, port:Int, dataMap:MutableMap<String, Any>){
        CoroutineScope(Dispatchers.IO).launch {
            
        }
    }
    fun commonSendPostToServer(url:String, port:Int, dataMap:MutableMap<String, Any>){
        CoroutineScope(Dispatchers.IO).launch {

        }
    }
}