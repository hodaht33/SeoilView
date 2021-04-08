package seoil.capstone.som;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class GlobalApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 카카오 어플 네이티브 앱 키
        KakaoSdk.init(this, "807b94e191e23dfd5f19907be7eec2ae");
    }
}
