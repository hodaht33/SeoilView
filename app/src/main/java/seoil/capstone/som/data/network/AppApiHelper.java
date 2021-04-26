package seoil.capstone.som.data.network;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import org.json.JSONException;
import org.json.JSONObject;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import seoil.capstone.som.R;
import seoil.capstone.som.data.network.api.UserRestApi;
import seoil.capstone.som.data.network.model.IdDuplicateResponse;
import seoil.capstone.som.data.network.model.LoginRequest;
import seoil.capstone.som.data.network.model.LoginResponse;
import seoil.capstone.som.data.network.model.RegisterRequest;
import seoil.capstone.som.data.network.model.RegisterResponse;

public class AppApiHelper {

    public static final String SOM_BASE_URL = "https://leebera.name/api/";

    private static AppApiHelper mAppApiHelper;
    private UserRestApi mUserRestApi;

    public AppApiHelper() {

        mUserRestApi = new UserRestApi(
                new Retrofit
                .Builder()
                .baseUrl(SOM_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        );
    }

    public static AppApiHelper getInstance() {

        if (mAppApiHelper == null) {

            mAppApiHelper = new AppApiHelper();
        }

        return mAppApiHelper;
    }

    public void serverLogin(LoginRequest loginRequest, OnFinishApiListener<LoginResponse.SomRestLoginApi> onFinishApiListener) {

        mUserRestApi.login(loginRequest, onFinishApiListener);
    }

    public void kakaoLogin(Context context, OnFinishApiListener<LoginResponse.KakaoLoginApi> onFinishApiListener) {

        // 카카오 로그인 콜백 함수 정의
        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {

                // 로그인 정상 수행
                if (oAuthToken != null) {

                    Log.d("API", "oAuth is available");

                    // 카카오 로그인 사용자의 uid받아 서버에 보내 회원인지 확인
                    UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                        @Override
                        public Unit invoke(User user, Throwable throwable) {

                            // 카카오 유저가 정상적으로 있을 경우
                            if (user != null) {

                                onFinishApiListener.onSuccess(new LoginResponse.KakaoLoginApi(String.valueOf(user.getId())));
                            }

                            return null;
                        }
                    });
                }

                // 로그인 실패
                if (throwable != null) {

                    // 예외 처리
                    Log.d("API", "throwable: " + throwable);
                }

                return null;
            }
        };
        // 카카오 어플 존재하는지 확인
        if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(context)) {

            // 존재하므로 어플로 로그인
            UserApiClient.getInstance().loginWithKakaoTalk(context, callback);
        } else {

            // 존재하지 않아 웹으로 로그인
            UserApiClient.getInstance().loginWithKakaoAccount(context, callback);
        }
    }

    public void naverLogin(Context context, Resources res, OnFinishApiListener<LoginResponse.NaverLoginApi> onFinishApiListener) {

        OAuthLogin oAuthLogin = OAuthLogin.getInstance();
        oAuthLogin.init(
                context
                ,res.getString(R.string.naver_client_id)
                ,res.getString(R.string.naver_client_secret)
                ,res.getString(R.string.naver_client_name)
        );

        OAuthLoginHandler oAuthLoginHandler = new OAuthLoginHandler() {
            @Override
            public void run(boolean success) {
                if (success) {

                    Thread getDataThread = new Thread() {
                        @Override
                        public void run() {

                            String accessToken = oAuthLogin.getAccessToken(context);
                            String naverLoginData = oAuthLogin.requestApi(context, accessToken, "https://openapi.naver.com/v1/nid/me");

                            try {

                                JSONObject jsonResult = new JSONObject(naverLoginData).getJSONObject("response");
                                String gender = jsonResult.getString("gender");

                                // 성별이 알 수 없음 일 때
                                if (gender.equals("U")) {

                                    gender = "M";
                                }

                                onFinishApiListener.onSuccess(new LoginResponse.NaverLoginApi(
                                        jsonResult.getString("id"),
                                        jsonResult.getString("birthyear") + jsonResult.getString("birthday").replace("-", ""),
                                        gender,
                                        jsonResult.getString("email"),
                                        jsonResult.getString("mobile").replace("-", "")
                                ));
                            } catch (JSONException e) {

                                e.printStackTrace();
                            }

                        }
                    };

                    getDataThread.start();
                    try {

                        getDataThread.join();
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                } else {

                    String errorCode = oAuthLogin.getLastErrorCode(context).getCode();
                    String errorDesc = oAuthLogin.getLastErrorDesc(context);
                }
            };
        };

        // OAuth로그인 핸들러 넘겨 수행
        oAuthLogin.startOauthLoginActivity((Activity) context, oAuthLoginHandler);
    }

    public void checkIdDuplicate(String id, OnFinishApiListener<IdDuplicateResponse> onFinishApiListener) {

        mUserRestApi.checkIdDuplicate(id, onFinishApiListener);
    }

    public void customerRegister(RegisterRequest.Customer registerRequest, OnFinishApiListener<RegisterResponse> onFinishApiListener) {

        mUserRestApi.insertCustomer(registerRequest, onFinishApiListener);
    }
}
