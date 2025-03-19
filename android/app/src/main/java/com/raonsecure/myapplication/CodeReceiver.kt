package com.TouchEn.mVaccine.DEMO

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.secureland.smartmedic.core.Constants

class CodeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        /*-------------------------------- 안내 ------------------------------------------
		본 파일은 mVaccine 제품에 대한 연동의 이해를 돕기위해 작성 된 샘플코드 입니다. 
		백신 액티비티는 결과 값을 브로드캐스트로도 전달합니다.
		아래와 같이 브로드캐스트로 결과 값을 전달 받아 사용 하실 수 있습니다.		
        com.secureland.smartmedic.core.Constants.ROOTING_EXIT_APP - 루팅단말 [인텐트에 rootingexitapp-true로 백신 액티비티를 실행 했을 때]
        com.secureland.smartmedic.core.Constants.ROOTING_YES_OR_NO - 루팅단말 [인텐트에 rootingyesorno-true로 백신 액티비티를 실행 하고 사용자가 yes를 눌렀을 때]
        com.secureland.smartmedic.core.Constants.EMPTY_VIRUS - 악성코드, 루팅여부 모두 정상
        com.secureland.smartmedic.core.Constants.EXIST_VIRUS - 악성코드 탐지 됨
        com.secureland.smartmedic.core.Constants.EXIST_VIRUS_CASE1 - 악성코드 탐지 후 사용자가 해당 악성코드 앱을 삭제
        com.secureland.smartmedic.core.Constants.EXIST_VIRUS_CASE2 - 악성코드 탐지 후 사용자가 해당 악성코드 앱을 미삭제
        com.secureland.smartmedic.core.Constants.V_DB_FAIL - DB파일 무결성검증 실패
        com.secureland.smartmedic.core.Constants.POST_NOTI_NO - Android13 이상 노티피케이션 표시 권한이 없음
        com.secureland.smartmedic.core.Constants.END_INIT - 검사 과정 중 초기화 완료
        com.secureland.smartmedic.core.Constants.INIT_FAIL - 초기화 실패
		----------------------------------------------------------------------------------*/
        
        // "com.TouchEn.mVaccine.b2b2c.FIRE" 수신 된 Intent 처리
        val i = intent.getIntExtra("result", 0)
        Log.d("CodeReceiver", "result = $i")
        
        when (i) {
            Constants.EMPTY_VIRUS ->
                Log.d(
                    "CodeReceiver",
                    "com.secureland.smartmedic.core.Constants.EMPTY_VIRUS"
                )
            Constants.EXIST_VIRUS_CASE1 ->
                Log.d(
                    "CodeReceiver",
                    "com.secureland.smartmedic.core.Constants.EXIST_VIRUS_CASE1"
                )
            Constants.EXIST_VIRUS_CASE2 ->
                Log.d(
                    "CodeReceiver",
                    "com.secureland.smartmedic.core.Constants.EXIST_VIRUS_CASE2"
                )
            Constants.ROOTING_EXIT_APP ->                 // 루팅 탐지 되었을 경우 ( mini 모드,  bg_rooting 옵션 true )
                Log.d(
                    "CodeReceiver",
                    "com.secureland.smartmedic.core.Constants.ROOTING_EXIT_APP"
                )
            Constants.POST_NOTI_NO ->
                Log.d(
                    "CodeReceiver",
                    "com.secureland.smartmedic.core.Constants.POST_NOTI_NO"
                )
            Constants.INIT_FAIL ->		// 초기화 실패
                Log.d(
                    "CodeReceiver",
                    "com.secureland.smartmedic.core.Constants.INIT_FAIL"
                )
        }
    }
}