package com.raonsecure.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RestaurantEnrollment : AppCompatActivity() {
    private val enrollRestaurantBtn:Button by lazy { findViewById(R.id.enrollRestaurantBtn) }
    private val restaurantNameEditText:EditText by lazy { findViewById(R.id.restaurantName) }
    private val restaurantAddressEditText:EditText by lazy { findViewById(R.id.restaurantAddress) }
    private val restaurantDescriptionEditText:EditText by lazy { findViewById(R.id.restaurantDescription) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_restaurant_enrollment)

        enrollRestaurantBtn.setOnClickListener {
            val bundle:Bundle = Bundle()
            val resultIntent = Intent()

            bundle.putString("restaurantName", restaurantNameEditText.text.toString())
            bundle.putString("restaurantAddress", restaurantAddressEditText.text.toString())
            bundle.putString("restaurantDescription", restaurantDescriptionEditText.text.toString())
            resultIntent.putExtras(bundle)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }


}