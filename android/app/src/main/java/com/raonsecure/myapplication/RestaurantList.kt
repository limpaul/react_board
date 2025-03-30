package com.raonsecure.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class RestaurantList : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var openDrawerButton: Button
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var restaurantListLayout: View // 메뉴 리스트


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)


        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result ->
            if(result.resultCode == RESULT_OK){
                val bundle = result.data?.extras
            }
        }

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        openDrawerButton = findViewById(R.id.open_drawer_button)
        restaurantListLayout = findViewById(R.id.restaurantListLayout)

        openDrawerButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            val intent:Intent

            when (menuItem.itemId) {
                R.id.nav_item1 -> Toast.makeText(this, "1번 클릭", Toast.LENGTH_SHORT).show()
                R.id.nav_addRestaurantDrawerTab -> {
                    intent = Intent(this, RestaurantEnrollment::class.java);
                    resultLauncher.launch(intent)
                }
                R.id.nav_item3 -> Toast.makeText(this, "3번 클릭", Toast.LENGTH_SHORT).show()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // <include> 태그는 레이아웃을 재사용하기 위한 것으로, 클래스 간 연결을 해주지 않습니다

    }
}
