package seoil.capstone.som.ui.login;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import seoil.capstone.som.data.network.api.UserApi;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Login;
import seoil.capstone.som.ui.main.MainActivity;
import seoil.capstone.som.ui.register.RegisterActivity;

public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";
    private LoginContract.View mView;
    private LoginContract.Interactor mInteractor;

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
    public void serverLogin(String id, String pwd, Context context, OnFinishApiListener onFinishApiListener) {

        OnFinishApiListener<Login.LoginRes> callback;

        if (onFinishApiListener == null) {

            callback = new OnFinishApiListener<Login.LoginRes>() {
                @Override
                public void onSuccess(Login.LoginRes loginResponse) {

                    int statusCode = loginResponse.getStatus();

                    // 초기엔 공용 데이터인 아이디와 구분 코드 담기 위한 Bundle 생성
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("code", loginResponse.getCode());

                    if (pwd.equals("naver")
                        || pwd.equals("kakao")) {

                        bundle.putString("platform", pwd);
                    } else {

                        bundle.putString("platform", "");
                    }

                    Intent intent = new Intent();

                    if (statusCode == UserApi.SUCCESS) {

                        // 로그인 성공했으므로 MainActivity로 이동하도록 설정
                        intent.setComponent(new ComponentName(context, MainActivity.class));
                        intent.putExtra("data", bundle);

                        mView.toMain(intent);
                    } else if (statusCode == UserApi.NEW_USER) {

                        // 카카오의 새로운 사용자일 때 RegisterActivity로 이동하도록 설정
                        intent.setComponent(new ComponentName(context, RegisterActivity.class));
                        bundle.putString("platform", "kakao");
                        intent.putExtra("data", bundle);

                        mView.toRegit(intent);
                    } else {

                        // 로그인 실패 에러 코드 전송
                        mView.loginFail(statusCode);
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                    // som rest api연결 실패 예외처리
                    mView.showToast("Login Fail : " + t);
                }
            };
        } else {

            // 네이버의 경우 Response를 따로 넘겨줘야하기 때문에 인자로 받은 listener를 넘김
            callback = onFinishApiListener;
        }

        mInteractor.serverLogin(new Login.LoginReq(id, pwd), callback);
    }

    @Override
    public void kakaoLogin(Context context) {

        OnFinishApiListener<Login.KakaoLoginRes> callback = new OnFinishApiListener<Login.KakaoLoginRes>() {
            @Override
            public void onSuccess(Login.KakaoLoginRes loginResponse) {

                // 카카오 아이디가 null이 아니면 성공한 것이므로 som rest api로 서버에 보내 사용자인지 검사
                String kakaoId = loginResponse.getId();
                if (kakaoId != null) {

                    serverLogin(kakaoId, "kakao", context, null);
                }
            }

            @Override
            public void onFailure(Throwable t) {

                // 로그인 실패 예외처리
                Log.d(TAG, "Kakao Login Error : " + t);
            }
        };

        mInteractor.kakaoLogin(context, callback);
    }

    @Override
    public void naverLogin(Context context, Resources resources) {

        OnFinishApiListener<Login.NaverLoginRes> callback = new OnFinishApiListener<Login.NaverLoginRes>() {
            @Override
            public void onSuccess(Login.NaverLoginRes naverLoginResponse) {

                // 네이버 아이디가 있으면 네이버 로그인 성공이므로 som rest api로 서버에 보내 사용자인지 검사
                String naverId = naverLoginResponse.getId();
                if (naverId != null) {

                    Log.d(TAG, "naverSuccess");
                    serverLogin(naverId, "naver", context, new OnFinishApiListener<Login.LoginRes>() {
                        @Override
                        public void onSuccess(Login.LoginRes serverLoginResponse) {

                            Log.d(TAG,"serverSuccess");
                            int statusCode = serverLoginResponse.getStatus();

                            Bundle bundle = new Bundle();
                            bundle.putString("id", naverId);

                            Intent intent = new Intent();


                            if (statusCode == UserApi.SUCCESS) {

                                intent.setComponent(new ComponentName(context, MainActivity.class));
                                bundle.putString("code", serverLoginResponse.getCode());
                                bundle.putString("platform", "naver");
                                intent.putExtra("data", bundle);

                                mView.toMain(intent);
                            } else if (statusCode == UserApi.NEW_USER) {

                                // 네이버에 담긴 정보를 같이 넘김
                                intent.setComponent(new ComponentName(context, RegisterActivity.class));
                                bundle.putString("birthdate", naverLoginResponse.getBirthdate());
                                bundle.putString("gender", naverLoginResponse.getGender());
                                bundle.putString("email", naverLoginResponse.getEmail());
                                bundle.putString("phoneNumber", naverLoginResponse.getPhoneNumber());
                                bundle.putString("platform", "naver");
                                intent.putExtra("data", bundle);

                                mView.toRegit(intent);
                            } else {

                                // 로그인 실패 에러 코드 전송
                                mView.loginFail(statusCode);
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {

                            // som rest api연결 실패 예외처리
                            mView.showToast("Fail : " + t);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Throwable t) {

                // 로그인 실패 예외처리
                Log.d(TAG, "Naver Login Error : " + t);
            }
        };

        mInteractor.naverLogin(context, resources, callback);
    }
}