package com.raonsecure.myapplication

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.TouchEn.mVaccine.DEMO.CodeReceiver
import com.TouchEn.mVaccine.b2b2c.activity.BackgroundScanActivity
import com.TouchEn.mVaccine.b2b2c.activity.ScanActivity
import com.TouchEn.mVaccine.b2b2c.activity.UiScanActivity
import com.TouchEn.mVaccine.b2b2c.data.ScanOption
import com.TouchEn.mVaccine.b2b2c.receiver.ScanReceiver
import com.TouchEn.mVaccine.b2b2c.util.BackgroundScanner
import com.TouchEn.mVaccine.b2b2c.util.CommonUtil
import com.TouchEn.mVaccine.b2b2c.util.Global
import com.google.gson.Gson
import com.secureland.smartmedic.ScanResultListener
import com.secureland.smartmedic.core.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : Activity() {
    private val appTitleTextView:TextView by lazy { findViewById(R.id.appTitle) }
    private val userAddBtn:TextView by lazy { findViewById(R.id.userAddBtn) }
    private val usernameEditText:EditText by lazy { findViewById(R.id.username) }
    private val userpasswordEditText:EditText by lazy { findViewById(R.id.userpassword) }
    private val loginBtn:Button by lazy { findViewById(R.id.loginBtn) }
    private var touchBtn:Boolean = false;
    private var rotationAngle = 0f // 30도씩 회전
    private val http = NetworkSetting()

    private val vaccineStartBtn:Button by lazy { findViewById(R.id.vaccineStartBtn) }
    private val vaccineStopBtn:Button by lazy { findViewById(R.id.vaccineStopBtn) }

    //test
    private val autoLoginBtn:Button by lazy { findViewById(R.id.autoLoginBtn) }

    // vaccine
    /*-------------------------- mVaccine RequestCode 정의 -----------------------------------
    mVaccine 제품관련 RequsetCode를 사전에 정의해서 사용합니다.
    'API연동가이드 RequstCode정의' 참조하여 목적에 맞게 추가 선언하여 사용바랍니다.
    ---------------------------------------------------------------------------------------*/
        companion object {
            // mVaccine 제품 RequestCode
            const val REQUEST_CODE = 777

            // UI 간소화 모드 검사진행 Notification
            const val MESSAGE_ID = 12345

            // UI 간소화 모드 검사결과 Notification
            const val MESSAGE_ID1 = 123456
        }
    var scanReceiver: ScanReceiver? = null
    var codeReceiver: BroadcastReceiver? = null
    var rootingDelayTime = 0 //루팅 옵션
    var rootingInternalCheck = 0 //루팅 옵션
    var backgroundScanType = 1 // mini모드 동작 옵션 (0: Activity | 1: Service | 2: Thread)
    //val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAppFontColorChange();

        // vaccine
        /*------------------------ mVaccine 사이트ID, 라이선스key 설정 ------------------------------
사이트 인증을 위해 지급받은 사이트 ID, KEY 값을 정확하게 입력해야 합니다.(라온시큐어 담당SE제공)
ID, KEY 값이 맞지 않을 경우 mVaccine 구동이 정상적으로 되지 않습니다.
-------------------------------------------------------------------------------------*/
        //TODO 사이트아이디,라이센스키 설정
        Constants.site_id = ""
        Constants.license_key = ""

        /*----------------- 디버깅 모드 설정 -------------------*/
        Constants.debug = true // 디버깅 필요 시 true 설정
        Global.debug = true // 디버깅 필요 시 true 설정

        /*----------------- 실시간검사 동적 등록 방법 -------------------*/
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED")
        intentFilter.addAction("android.intent.action.PACKAGE_INSTALL")
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED")
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED")
        intentFilter.addDataScheme("package")
        scanReceiver = ScanReceiver()
        scanReceiver?.setScanOption(
            ScanOption.Builder()
                .setScanPhishing(true)
                .setShowBadge(false)
                .setNotifyClearable(true)
                .setUseDualEngine(true)
                .build()
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(scanReceiver, intentFilter, RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(scanReceiver, intentFilter)
        }

        /*----------------- CodeReceiver 동적 등록 방법 -------------------*/
        val intentFilter2 = IntentFilter()
        intentFilter2.addAction("$packageName.mVaccine.FIRE")
        codeReceiver = CodeReceiver()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(codeReceiver, intentFilter2, RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(codeReceiver, intentFilter2)
        }

        /*----------------- 악성코드, 보이스 피싱 분류 API 등록 -------------------*/
        // mini 모드 전용
        BackgroundScanActivity.SetScanListener { arrayList ->
            toastResult(arrayList)
        }
        // full모드 전용
        ScanActivity.SetScanListener { arrayList ->
            toastResult(arrayList)
        }
        // UIFull모드 전용
        UiScanActivity.SetScanListener { arrayList ->
            toastResult(arrayList)
        }

        userAddBtn.setOnClickListener { // 회원 가입 layout 이동
            val intent:Intent = Intent(this, UserEnroll::class.java);
            startActivity(intent);
        }
        /*----------------- mVaccine 검사 호출 ---------------------
        아래 검사옵션 설정에서 설정한 함수를 택일하여 호출합니다.
        mini : 검사진행 중 UI가 없는 간소화 모드 입니다.
        full : 검사진행 중 UI가 보여지는 모드 입니다.
        -------------------------------------------------------*/
        vaccineStartBtn.setOnClickListener {
            /*------------------ Android13 이상 검사 후 노티알람 권한이 없는 경우 동작 샘플 ------------------
            본 샘플은 mVaccine에서 제공하는 영역이 아닌 앱에 따라 상이하게 구현되는 부분이므로 예시는 구현의 참고용으로만 사용해주시길 바랍니다.
            Android13 이상 모든 앱은 사용자에게 노티 알람 권한을 요청해야합니다.
            악성앱 검사 전에 CommonUtil.checkNotiPermission() 를 이용하여 노티 알림 권한을 확인하고 사용자에게 권한을 요청하는 샘플입니다.
            ( 샘플에 대한 정상적인 구동(알림 권한 요청 알림창 제공 등)을 위해서는 targetSdk33 이상,
            AndroidManifest에서 android.permission.POST_NOTIFICATIONS를 허용해야 합니다. )
            -------------------------------------------------------------------------------------*/
            if (!CommonUtil.checkNotiPermission(this.applicationContext)) {
                if (applicationContext.applicationInfo.targetSdkVersion >= 33) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf("android.permission.POST_NOTIFICATIONS"),
                        12233
                    )
                }else{
                    Toast.makeText(this, "targetSdk under 33", Toast.LENGTH_LONG)
                }
                //알림 권한이 있는 경우에만 검사 진행하고 싶은 경우, 주석 해제
                //break;
            }
            // 백신 액티비티에서 악성코드,루팅 탐지
            when (backgroundScanType) {
                0 -> mini()
                1 -> mini_thread()
            }
        }
        vaccineStopBtn.setOnClickListener {
            Toast.makeText(this, "백신을 종료합니다", Toast.LENGTH_LONG)
            unregisterReceiver(scanReceiver)
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

    private fun toastResult(arrayList: java.util.ArrayList<*>) {
        var info: Map<String, String> = HashMap()
        var pkgName: String? = ""
        var malType: String? = ""
        for (i in arrayList.indices) {
            info = arrayList[i] as Map<String, String>
            pkgName = info["packageName"]
            malType = info["malware_type"]
            Toast.makeText(
                applicationContext,
                "PackageName : $pkgName\n Type : $malType",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun mini() {

        val i = Intent(this, BackgroundScanActivity::class.java)

        //옵션 설정
        i.putExtra("useBlackAppCheck", true) // 루팅 검사를 실시하면 루팅 우회 앱 설치 여부까지 검사
        i.putExtra("showBlackAppName", true)
        i.putExtra("scan_rooting", true)
        i.putExtra("scan_package", true)
        i.putExtra("useDualEngine", false)
        i.putExtra("backgroundScan", false) // mini 전용
        i.putExtra("rootingexitapp", false)
        i.putExtra("rootingyesorno", false)
        i.putExtra("rootingyes", false)
        i.putExtra("rooting_delay_time", rootingDelayTime)
        i.putExtra("show_update", false)
        i.putExtra("show_license", true)
        i.putExtra("show_notify", true) // mini 전용
        i.putExtra("show_result", true) // mini 전용
        i.putExtra("notifyClearable", true) // mini 전용
        i.putExtra("notifyAutoClear", true) // mini 전용
        i.putExtra("show_toast", true)
        i.putExtra("show_warning", true)
        i.putExtra("show_scan_ui", true) // mini 전용
        i.putExtra("show_badge", true) // mini 전용
        i.putExtra("bg_rooting", false) // mini 전용
        i.putExtra("show_about", false) // mini 전용
        i.putExtra("rooting_internal_check", rootingInternalCheck)
        i.putExtra("scan_phishing", false)

        //Intent를 보내고 결과값을 얻어옴
        this.startActivityForResult(i, REQUEST_CODE)
    }


    fun mini_thread() {

        val bundle = Bundle()

        //옵션 설정
        bundle.putBoolean("useBlackAppCheck", true) // 루팅 검사를 실시하면 루팅 우회 앱 설치 여부까지 검사
        bundle.putBoolean("showBlackAppName", true)
        bundle.putBoolean("scan_rooting", true)
        bundle.putBoolean("scan_package", true)
        bundle.putBoolean("useDualEngine", false)
        bundle.putBoolean("backgroundScan", true) // mini 전용
        bundle.putBoolean("rootingexitapp", true)
        bundle.putBoolean("rootingyesorno", false)
        bundle.putBoolean("rootingyes", false)
        bundle.putInt("rooting_delay_time", rootingDelayTime)
        bundle.putBoolean("show_update", false)
        bundle.putBoolean("show_license", true)
        bundle.putBoolean("show_notify", true) // mini 전용
        bundle.putBoolean("show_result", true) // mini 전용
        bundle.putBoolean("notifyClearable", true) // mini 전용
        bundle.putBoolean("notifyAutoClear", true) // mini 전용
        bundle.putBoolean("show_toast", true)
        bundle.putBoolean("show_warning", true)
        bundle.putBoolean("show_scan_ui", true) // mini 전용
        bundle.putBoolean("show_badge", true) // mini 전용
        bundle.putBoolean("bg_rooting", false) // mini 전용
        bundle.putBoolean("show_about", false) // mini 전용
        bundle.putInt("rooting_internal_check", rootingInternalCheck)
        bundle.putBoolean("scan_phishing", false)

        val backgroundScanner = BackgroundScanner(applicationContext, bundle)

        // 악성코드, 보이스 피싱 분류 API 등록
        BackgroundScanner.SetScanListener(object : ScanResultListener {
            override fun onScanComplete(list: ArrayList<MutableMap<String, String>>) {
                toastResult(list)
            }
        })

        // 검사 시작
        backgroundScanner.startScan()
    }


}