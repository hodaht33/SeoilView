package seoil.capstone.som.data.network;

import android.annotation.SuppressLint;
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
import seoil.capstone.som.data.network.api.PointApi;
import seoil.capstone.som.data.network.api.UserApi;
import seoil.capstone.som.data.network.model.CurrentPoint;
import seoil.capstone.som.data.network.model.IdDuplicate;
import seoil.capstone.som.data.network.model.Login;
import seoil.capstone.som.data.network.model.Register;
import seoil.capstone.som.data.network.model.SavePoint;
import seoil.capstone.som.data.network.model.UsingPoint;

public class AppApiHelper {

    public static final String BASE_URL = "https://leebera.name/api/";

    private static AppApiHelper mAppApiHelper;
    private UserApi mUserApi;
    private PointApi mPointApi;

    public AppApiHelper() {

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mUserApi = new UserApi(retrofit);
        mPointApi = new PointApi(retrofit);
    }

    public static AppApiHelper getInstance() {

        if (mAppApiHelper == null) {

            mAppApiHelper = new AppApiHelper();
        }

        return mAppApiHelper;
    }

    public void serverLogin(Login.LoginReq loginReq, OnFinishApiListener<Login.LoginRes> onFinishApiListener) {

        mUserApi.login(loginReq, onFinishApiListener);
    }

    public void kakaoLogin(Context context, OnFinishApiListener<Login.KakaoLoginRes> onFinishApiListener) {

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

                                onFinishApiListener.onSuccess(new Login.KakaoLoginRes(String.valueOf(user.getId())));
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

    public void naverLogin(Context context, Resources res, OnFinishApiListener<Login.NaverLoginRes> onFinishApiListener) {

        OAuthLogin oAuthLogin = OAuthLogin.getInstance();
        oAuthLogin.init(
                context
                ,res.getString(R.string.naver_client_id)
                ,res.getString(R.string.naver_client_secret)
                ,res.getString(R.string.naver_client_name)
        );

        @SuppressLint("HandlerLeak") OAuthLoginHandler oAuthLoginHandler = new OAuthLoginHandler() {
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

                                onFinishApiListener.onSuccess(new Login.NaverLoginRes(
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

    public void checkIdDuplicate(String id, OnFinishApiListener<IdDuplicate> onFinishApiListener) {

        mUserApi.checkIdDuplicate(id, onFinishApiListener);
    }

    public void customerRegister(Register.Customer registerRequest, OnFinishApiListener<Register.RegisterRes> onFinishApiListener) {

        mUserApi.insertCustomer(registerRequest, onFinishApiListener);
    }

    public void getCurrentPoint(String id, OnFinishApiListener<CurrentPoint.GetRes> onFinishApiListener) {

        mPointApi.getPoint(id, onFinishApiListener);
    }

    public void insertCurrentPoint(CurrentPoint.InsertReq req, OnFinishApiListener<CurrentPoint.StatusRes> onFinishApiListener) {

        mPointApi.insertPointTuple(req, onFinishApiListener);
    }

    public void getUsingPoint(String id, OnFinishApiListener<UsingPoint.GetRes> onFinishApiListener) {

        mPointApi.getUsingPoint(id, onFinishApiListener);
    }

    public void insertUsingPoint(UsingPoint.InsertReq req, OnFinishApiListener<UsingPoint.StatusRes> onFinishApiListener) {

        mPointApi.insertUsingPoint(req, onFinishApiListener);
    }

    public void getSavePoint(String id, OnFinishApiListener<SavePoint.GetRes> onFinishApiListener) {

        mPointApi.getSavePoint(id, onFinishApiListener);
    }

    public void insertSavePoint(SavePoint.InsertReq req, OnFinishApiListener<SavePoint.StatusRes> onFinishApiListener) {

        mPointApi.insertSavePoint(req, onFinishApiListener);
    }
}
