package com.raonsecure.myapplication

import android.content.Intent
import android.net.Network
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserEnroll : AppCompatActivity() {
    private val userAddText:TextView by lazy { findViewById(R.id.userAddText) }
    private val userAddSendBtn:Button by lazy { findViewById(R.id.userAddSendBtn) }
    private val username:EditText by lazy { findViewById(R.id.username) }
    private val useremail:EditText by lazy { findViewById(R.id.useremail) }
    private val userpassword:EditText by lazy { findViewById(R.id.userpassword) }
    private val userpasswordVerify:EditText by lazy { findViewById(R.id.userpasswordVerify) }
    private lateinit var restaurantName: String
    private lateinit var restaurantAddress: String
    private lateinit var restaurantDescription: String

    private val userRoleChkBox:CheckBox by lazy { findViewById(R.id.userRoleChkBox) }
    private val roleRestaurantClientChkBox:CheckBox by lazy { findViewById(R.id.roleRestaurantClientChkBox) }

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val http = NetworkSetting()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_enroll)



        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            if(result.resultCode == RESULT_OK){
                val bundle = result.data?.extras

                restaurantName = bundle?.getString("restaurantName").toString()
                restaurantAddress = bundle?.getString("restaurantAddress").toString()
                restaurantDescription = bundle?.getString("restaurantDescription").toString()
                Log.d("bwlim", "restaurantName: $restaurantName, restaurantAddress: $restaurantAddress")
            }
        }
        roleRestaurantClientChkBox.setOnClickListener {
            val intent:Intent = Intent(this, RestaurantEnrollment::class.java);
            resultLauncher.launch(intent)
        }
        userAddSendBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                enrollUserInfoToServer()
            }
        }
    }

    suspend fun enrollUserInfoToServer(){
        // 이름(별명) | 이메일 | 비밀번호 | 비밀번호 재확인 | 일반사용자 | 판매용 계정 권한 유무 확인
        val result:Boolean = verifyDataText();
        if(result){
             // 서버로 데이터 전공
            val dataMap:MutableMap<String, Any> = HashMap()
            dataMap.put("username", username.text.toString())
            dataMap.put("useremail", useremail.text.toString())
            dataMap.put("userpassword", userpassword.text.toString())

            if(roleRestaurantClientChkBox.isChecked){
                dataMap.put("restaurantName", restaurantName)
                dataMap.put("restaurantAddress", restaurantAddress)
                dataMap.put("restaurantAddress", restaurantDescription)
            }
            Log.d("bwlim", dataMap.toString())
            http.commonSendPostToServer(urlPath = "/api/order/user/enroll/account",dataMap = dataMap)?.let { response ->

                Log.d("bwlim", response)
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