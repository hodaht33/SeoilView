package seoil.capstone.som;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.firebase.FirebaseApp;
import com.kakao.sdk.common.KakaoSdk;

// 어플리케이션 전역 정보 관리
public class GlobalApplication extends Application {

    private String loginPlatform;   // 사용자 로그인 플랫폼(null : 일반, naver, kakao)
    private String userId;          // 사용자 아이디
    private String userCode;        // 사용자 분류 코드(C : 손님, M : 점주)

    @Override
    public void onCreate() {
        super.onCreate();

        // 카카오 어플 네이티브 앱 키 초기화
        KakaoSdk.init(this, "807b94e191e23dfd5f19907be7eec2ae");

        // Firebase 어플 초기화
        FirebaseApp.initializeApp(this);
    }

    // 로그아웃 시 사용자 정보 삭제
    public void logout() {

        userId = null;
        userCode = null;
    }

    // 사용자 정보 getter setter
    public String getLoginPlatform() {
        return loginPlatform;
    }

    public void setLoginPlatform(String loginPlatform) {
        this.loginPlatform = loginPlatform;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}