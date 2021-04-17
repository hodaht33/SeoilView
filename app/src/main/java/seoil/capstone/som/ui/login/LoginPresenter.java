package seoil.capstone.som.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;

import com.google.android.material.textfield.TextInputEditText;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import org.json.JSONException;
import org.json.JSONObject;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import seoil.capstone.som.R;
import seoil.capstone.som.data.network.model.LoginResponse;
import seoil.capstone.som.data.repository.OnFinishRepositoryListener;
import seoil.capstone.som.ui.main.MainActivity;

public class LoginPresenter implements LoginContract.Presenter, Function2<OAuthToken, Throwable, Unit>, OnFinishRepositoryListener<LoginResponse> {

    private static final String TAG = "LoginPresenter";
    private LoginContract.View mView;
    private LoginContract.Interactor mInteractor;
    private Context mContext;
    private Resources res;

    @Override
    public void setView(LoginContract.View view) {
        this.mView = view;
    }

    @Override
    public void releaseView() {
        this.mView = null;
    }

    @Override
    public void createInteractor() {
        this.mInteractor = new LoginInteractor();
    }

    @Override
    public void releaseInteractor() {
        this.mInteractor = null;
    }

    @Override
    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void releaseContext() {
        this.mContext = null;
    }

    @Override
    public void setResources(Resources res) {
        this.res = res;
    }

    @Override
    public void releaseResources() {
        this.res = null;
    }

    @Override
    public void login(String id, String pwd) {

        mInteractor.login(id, pwd, this);
    }

    @Override
    public void kakaoLogin() {

        // 카카오톡 앱이 설치되어 있는지 검사하여
        // if : 있으면 카카오 어플로 리다이렉트하여 로그인
        // else : 없으면 카카오 웹으로 리다이렉트하여 로그인
        if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(mContext)) {
            UserApiClient.getInstance().loginWithKakaoTalk(mContext, this);
        } else {
            UserApiClient.getInstance().loginWithKakaoAccount(mContext, this);
        }
    }

    @Override
    public void setKakaoLoginData() {
        Intent intent = new Intent(mContext, MainActivity.class);
        boolean isNewUser = false;

        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {

                if (user != null) {

                    // TODO: user.getId()를 통해 db에서 검색하여 없으면 isNewUser는 true로 만들고 회원가입으로 이동
                    intent.putExtra("id", user.getId());
                    //intent.putExtra("userCode", mUser.USER_CUSTOMER);
//                    intent.putExtra("email", jsonResult.getJSONObject("response").getString("email"));
//                    intent.putExtra("gender", jsonResult.getJSONObject("response").getString("gender"));
//                    intent.putExtra("birthday", jsonResult.getJSONObject("response").getString("birthday"));
//                    intent.putExtra("birthyear", jsonResult.getJSONObject("response").getString("birthyear"));
//                    intent.putExtra("phoneNumber", jsonResult.getJSONObject("response").getString("mobile"));
                }

                return null;
            }
        });

        // TODO: db를 통해 id가 db에 있는지 검사 후 없으면 아래 메서드 호출
        if (isNewUser) {
            mView.toRegit(intent);
        } else {
            mView.toMain(intent);
        }
    }

    @Override
    public void naverLogin() {
        OAuthLogin oAuthLogin = OAuthLogin.getInstance();
        oAuthLogin.init(
                mContext
                ,res.getString(R.string.naver_client_id)
                ,res.getString(R.string.naver_client_secret)
                ,res.getString(R.string.naver_client_name)
        );

        OAuthLoginHandler oAuthLoginHandler = new OAuthLoginHandler() {
            @Override
            public void run(boolean success) {
                if (success) {

                    try {
                        setNaverLoginData(oAuthLogin);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    String errorCode = oAuthLogin.getLastErrorCode(mContext).getCode();
                    String errorDesc = oAuthLogin.getLastErrorDesc(mContext);
                }
            };
        };

        oAuthLogin.startOauthLoginActivity((Activity) mContext, oAuthLoginHandler);
    }

    @Override
    public void setNaverLoginData(OAuthLogin oAuthLogin) throws InterruptedException {
        Intent intent = new Intent(mContext, MainActivity.class);

        Thread getDataThread = new Thread() {
            @Override
            public void run() {
                String accessToken = oAuthLogin.getAccessToken(mContext);

                String naverLoginData = oAuthLogin.requestApi(mContext, accessToken, "https://openapi.naver.com/v1/nid/me");
                
                try {
                    JSONObject jsonResult = new JSONObject(naverLoginData);

                    // TODO: user.getId()를 통해 db에서 검색하여 없으면 newUser값을 true 넣음, 있으면 인텐트에 값을 넣지 않고 넘김
                    // 네이버는 아래 정보 모두 가지고 올 수 있으므로 가입시에만 넣어감
                    intent.putExtra("id", jsonResult.getJSONObject("response").getString("id"));
                    intent.putExtra("userCode", seoil.capstone.som.data.model.User.USER_MANAGER);
                    intent.putExtra("email", jsonResult.getJSONObject("response").getString("email"));
                    intent.putExtra("gender", jsonResult.getJSONObject("response").getString("gender"));
                    intent.putExtra("birthday", jsonResult.getJSONObject("response").getString("birthday"));
                    intent.putExtra("birthyear", jsonResult.getJSONObject("response").getString("birthyear"));
                    intent.putExtra("phoneNumber", jsonResult.getJSONObject("response").getString("mobile"));
                    intent.putExtra("newUser", "false");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        getDataThread.start();
        getDataThread.join();

//        Log.d(TAG,intent.getStringExtra("id").toString());
//        Log.d(TAG,intent.getStringExtra("email").toString());
//        Log.d(TAG,intent.getStringExtra("gender").toString());
//        Log.d(TAG,intent.getStringExtra("birthday").toString());
//        Log.d(TAG,intent.getStringExtra("birthyear").toString());
//        Log.d(TAG,intent.getStringExtra("phoneNumber").toString());

        if (intent.getStringExtra("newUser").equals("true")) {
            mView.toRegit(intent);
        } else {
            mView.toMain(intent);
        }
    }

    // Function2<OAuthToken, Throwable, Unit> 필수 정의 인터페이스 메서드
    // 카카오 로그인 여부 확인 콜백 메서드
    @Override
    public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
        Log.d(TAG, "loginCallback");
        if (oAuthToken != null) {
            Log.d(TAG, "oAuth is available");

            // 로그인 되었을 때 처리할 일들
            setKakaoLoginData();
        }

        if (throwable != null) {
            // 예외 처리
            Log.d(TAG, "throwable: " + throwable);
        }

        return null;
    }

    @Override
    public void onSuccess(LoginResponse loginResponse) {

        int statusCode = loginResponse.getStatus();

        // 로그인 성공 시 아이디와 사용자 구분 코드를 가지고 메인 화면으로 이동
        if (statusCode == LoginResponse.SUCCESS) {

            Intent intent = new Intent(mContext, MainActivity.class);
            intent.putExtra("id", loginResponse.getId());
            intent.putExtra("code", loginResponse.getCode());

            mView.toMain(intent);
        } else {

            // 로그인 실패 에러 코드 전송
            mView.loginFail(statusCode);
        }
    }

    @Override
    public void onFailure(Throwable t) {

        mView.showToast("Login Fail : " + t);
    }
}