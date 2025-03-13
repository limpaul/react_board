package com.raonsecure.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response

class UserEnroll : AppCompatActivity() {
    private val userAddText:TextView by lazy { findViewById(R.id.userAddText) }
    private val username:EditText by lazy { findViewById(R.id.username) }
    private val useremail:EditText by lazy { findViewById(R.id.useremail) }
    private val userpassword:EditText by lazy { findViewById(R.id.userpassword) }
    private val userpasswordVerify:EditText by lazy { findViewById(R.id.userpasswordVerify) }
    private lateinit var restaurantName: String
    private lateinit var restaurantAddress: String

    private val userRoleChkBox:CheckBox by lazy { findViewById(R.id.userRoleChkBox) }
    private val roleRestaurantClientChkBox:CheckBox by lazy { findViewById(R.id.roleRestaurantClientChkBox) }

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_enroll)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            if(result.resultCode == RESULT_OK){
                val bundle = result.data?.extras

                restaurantName = bundle?.getString("restaurantName").toString()
                restaurantAddress = bundle?.getString("restaurantAddress").toString()
                Log.d("bwlim", "restaurantName: $restaurantName, restaurantAddress: $restaurantAddress")
            }
        }
        roleRestaurantClientChkBox.setOnClickListener {
            val intent:Intent = Intent(this, RestaurantEnrollment::class.java);
            resultLauncher.launch(intent)
        }
    }

    fun enrollUserInfoToServer(){
        // 이름(별명) | 이메일 | 비밀번호 | 비밀번호 재확인 | 일반사용자 | 판매용 계정 권한 유무 확인
        val result:Boolean = verifyDataText();
        if(result){
             // 서버로 데이터 전공
            val dataMap:MutableMap<String, Any> = HashMap()
            dataMap.put("username", username.text)
            dataMap.put("useremail", useremail.text)
            dataMap.put("userpassword", userpassword.text)
            if(roleRestaurantClientChkBox.isChecked){
                dataMap.put("restaurantName", restaurantName)
                dataMap.put("userpassword", restaurantAddress)
            }

            CoroutineScope(Dispatchers.IO).launch {
                val gson:Gson = Gson()
                var data:String = gson.toJson(dataMap)

                val client:OkHttpClient = OkHttpClient()
                val requestBody:RequestBody = data.toRequestBody(contentType = "application/json; charset=utf8".toMediaTypeOrNull())
                val request:Request = Request.Builder()
                    .url()
                    .post(requestBody)
                    .build()

                val response:Response = client.newCall(request).execute()
            }
        }
    }
    fun verifyDataText(): Boolean {
        if(username.text.toString().isEmpty()){
            Toast.makeText(this, "username is empty", Toast.LENGTH_LONG).show()
            return false
        }
        if(useremail.text.toString().isEmpty()){
            Toast.makeText(this, "useremail is empty", Toast.LENGTH_LONG).show()
            return false
        }
        if(userpassword.text.toString().isEmpty()){
            Toast.makeText(this, "userpassword is empty", Toast.LENGTH_LONG).show()
            return false
        }
        if(userpasswordVerify.text.toString().isEmpty()){
            Toast.makeText(this, "userpassword Verify is empty", Toast.LENGTH_LONG).show()
            return false
        }
        if(userpassword.text.equals(userpasswordVerify)){
            Toast.makeText(this, "password incorrect", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}