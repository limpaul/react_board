package com.raonsecure.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    val appTitleTextView:TextView by lazy { findViewById(R.id.appTitle) }
    val userAddBtn:TextView by lazy { findViewById(R.id.userAddBtn) }
    var touchBtn:Boolean = false;
    private var rotationAngle = 0f // 30도씩 회전


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAppFontColorChange();

        userAddBtn.setOnClickListener { // 회원 가입 layout 이동
            val intent:Intent = Intent(this, UserEnroll::class.java);
            startActivity(intent);
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