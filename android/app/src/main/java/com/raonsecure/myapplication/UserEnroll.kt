package com.raonsecure.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UserEnroll : AppCompatActivity() {
    private val userAddText:TextView by lazy { findViewById(R.id.userAddText) }
    private val roleRestaurantClientChkBox:CheckBox by lazy { findViewById(R.id.roleRestaurantClientChkBox) }
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_enroll)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            if(result.resultCode == RESULT_OK){
                val bundle = result.data?.extras

                val restaurantName = bundle?.getString("restaurantName")
                val restaurantAddress = bundle?.getString("restaurantAddress")
                Log.d("bwlim", "restaurantName: $restaurantName, restaurantAddress: $restaurantAddress")
            }
        }
        roleRestaurantClientChkBox.setOnClickListener {
            val intent:Intent = Intent(this, RestaurantEnrollment::class.java);
            resultLauncher.launch(intent)
        }
    }
}