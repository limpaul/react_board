package com.raonsecure.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private val appTitleTextView:TextView by lazy { findViewById(R.id.appTitle) }
    private val userAddBtn:TextView by lazy { findViewById(R.id.userAddBtn) }
    private val usernameEditText:EditText by lazy { findViewById(R.id.username) }
    private val userpasswordEditText:EditText by lazy { findViewById(R.id.userpassword) }
    private val loginBtn:Button by lazy { findViewById(R.id.loginBtn) }
    private var touchBtn:Boolean = false;
    private var rotationAngle = 0f // 30도씩 회전
    private val http = NetworkSetting()

    //test
    private val autoLoginBtn:Button by lazy { findViewById(R.id.autoLoginBtn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAppFontColorChange();

        userAddBtn.setOnClickListener { // 회원 가입 layout 이동
            val intent:Intent = Intent(this, UserEnroll::class.java);
            startActivity(intent);
        }

        loginBtn.setOnClickListener {
            val dataMap: MutableMap<String, Any>  = HashMap()
            val username = usernameEditText.text.toString()
            val userpassword = userpasswordEditText.text.toString()
            dataMap.put("username", username);
            dataMap.put("userpassword", userpassword)

            CoroutineScope(Dispatchers.IO).launch {
                http.commonSendPostToServer(urlPath = "/api/order/user/login", dataMap = dataMap)?.let {
                    response ->
                    Log.d("bwlim", response.toString())
                    val gson:Gson = Gson()
                    val resultMap = gson.fromJson(response, Map::class.java)

                    CoroutineScope(Dispatchers.Main).launch {
                        val builder = AlertDialog.Builder(this@MainActivity) // MainActivity의 context 사용
                        builder.setTitle("로그인 결과")
                            .setMessage(resultMap.get("message").toString())
                            .setPositiveButton("확인") { dialog, _ ->
                                dialog.dismiss()
                                if(resultMap.get("isSuccess") == true){
                                    val intent:Intent = Intent(this@MainActivity, RestaurantList::class.java)
                                    intent.putExtra("userinfo", response)
                                    startActivity(intent)
                                }
                            }
                            .show()


                    }
                }
            }
        }

        autoLoginBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val dataMap = HashMap<String, Any>()
                dataMap.put("username", "test")
                dataMap.put("userpassword", "1234")
                http.commonSendPostToServer(urlPath = "/api/order/user/login", dataMap = dataMap)?.let {
                        response ->
                    Log.d("bwlim", response.toString())
                    val gson:Gson = Gson()
                    val resultMap = gson.fromJson(response, Map::class.java)

                    CoroutineScope(Dispatchers.Main).launch {
                        if(resultMap.get("isSuccess") == true){
                            val intent:Intent = Intent(this@MainActivity, RestaurantList::class.java)
                            intent.putExtra("userinfo", response)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }

    fun setAppFontColorChange(){
        CoroutineScope(Dispatchers.IO).launch {
            while(true){
                delay(500L)
                rotationAngle += 30f // 30도씩 회전

                withContext(Dispatchers.Main){
                    if(touchBtn){
                        appTitleTextView.setTextColor(Color.RED)
                        touchBtn = false
                    }else{
                        appTitleTextView.setTextColor(Color.GRAY)
                        touchBtn = true
                    }
                    appTitleTextView.rotation = rotationAngle;
                }
            }
        }
    }

}