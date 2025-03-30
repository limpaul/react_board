package com.raonsecure.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantMenu : AppCompatActivity() {

    private val networkSetting:NetworkSetting by lazy { NetworkSetting() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_menu)

        getRestaurantListFromServer();
    }

    // 식당 정보를 서버로 부터 받아온다  /api/order/user/list/restaurant
    fun getRestaurantListFromServer(){
        CoroutineScope(Dispatchers.IO).launch {
            val result:String? = networkSetting.commonSendGetToServer(urlPath = "/api/order/user/list/restaurant")
            if(result!=null){
                Log.d("bwlim menu lists", result.toString())
            }
        }
    }



}