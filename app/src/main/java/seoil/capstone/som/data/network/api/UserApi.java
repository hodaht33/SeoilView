package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Path;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.model.Auth;
import seoil.capstone.som.data.network.model.Login;
import seoil.capstone.som.data.network.model.UserData;
import seoil.capstone.som.data.network.model.retrofit.User;
import seoil.capstone.som.data.network.OnFinishApiListener;

// 사용자 api
public class UserApi {

    // 사용자 응답 코드
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;
    public static final int EROR_NONE_DATA = 3;

    public static final int LOGIN_FAIL_ID = 3;
    public static final int LOGIN_FAIL_PWD = 4;
    public static final int NEW_USER = 5;   // 카카오나 네이버로 로그인 시 새로운 회원이면 이에 맞는 처리 수행

    public static final int ID_DUPLICATE = 3;

    public static final int CLOSED_BUSINESS = 3;
    public static final int ERROR_CRAWLING = 4;

    // 유효하지 않은 인증번호 입력
    public static final int ERROR_INVALID_AUTH = 4;

    private User mUserData;

    public UserApi(Retrofit retrofit) {

        mUserData = retrofit.create(User.class);
    }

    // 로그인 요청
    public void login(Login.LoginReq req, OnFinishApiListener onFinishApiListener) {

        Call<Login.LoginRes> call = mUserData.getLoginData(req);
        call.enqueue(new Callback<Login.LoginRes>() {
            @Override
            public void onResponse(Call<Login.LoginRes> call, Response<Login.LoginRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Login.LoginRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 인증번호 sms전송 요청
    public void sendSms(Auth.Req req, OnFinishApiListener<Auth.StatusRes> onFinishApiListener) {

        Call<Auth.StatusRes> call = mUserData.sendSms(req);
        call.enqueue(new Callback<Auth.StatusRes>() {
            @Override
            public void onResponse(Call<Auth.StatusRes> call, Response<Auth.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Auth.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 인증번호 확인 요청
    public void sendAuthCode(Auth.Req req, OnFinishApiListener<Auth.StatusRes> onFinishApiListener) {

        Call<Auth.StatusRes> call = mUserData.sendAuthCode(req);
        call.enqueue(new Callback<Auth.StatusRes>() {
            @Override
            public void onResponse(Call<Auth.StatusRes> call, Response<Auth.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Auth.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 인증 확인 후 해당 번호로 가입된 아이디 정보 요청(아이디 찾기)
    public void sendAuthForFindId(Auth.Req req, OnFinishApiListener<UserData.FindIdRes> onFinishApiListener) {

        Call<UserData.FindIdRes> call = mUserData.sendAuthForFindId(req);
        call.enqueue(new Callback<UserData.FindIdRes>() {
            @Override
            public void onResponse(Call<UserData.FindIdRes> call, Response<UserData.FindIdRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserData.FindIdRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 사용자 핸드폰 번호 요청
    public void getUserPhoneNumber(String userId, OnFinishApiListener<UserData.GetUserInfoRes> onFinishApiListener) {

        Call<UserData.GetUserInfoRes> call = mUserData.getUserPhoneNumber(userId);
        call.enqueue(new Callback<UserData.GetUserInfoRes>() {
            @Override
            public void onResponse(Call<UserData.GetUserInfoRes> call, Response<UserData.GetUserInfoRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserData.GetUserInfoRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 아이디 중복 확인 요청
    public void checkIdDuplicate(String id, OnFinishApiListener<Auth.StatusRes> onFinishApiListener) {

        Call<Auth.StatusRes> call = mUserData.checkIdDuplicate(id);
        call.enqueue(new Callback<Auth.StatusRes>() {
            @Override
            public void onResponse(Call<Auth.StatusRes> call, Response<Auth.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Auth.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 손님 회원가입 요청
    public void insertCustomer(UserData.Customer registerRequest, OnFinishApiListener<UserData.StatusRes> onFinishApiListener) {

        Call<UserData.StatusRes> call = mUserData.insertCustomer(registerRequest);
        call.enqueue(new Callback<UserData.StatusRes>() {
            @Override
            public void onResponse(Call<UserData.StatusRes> call, Response<UserData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 점주 회원가입 요청
    public void insertManager(UserData.Manager registerRequest, OnFinishApiListener onFinishApiListener) {

        Call<UserData.StatusRes> call = mUserData.insertManager(registerRequest);
        call.enqueue(new Callback<UserData.StatusRes>() {
            @Override
            public void onResponse(Call<UserData.StatusRes> call, Response<UserData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 비밀번호 수정(비밀번호 찾기) 요청
    public void updatePassword(String userId, UserData.ChangePasswordReq req, OnFinishApiListener<UserData.StatusRes> onFinishApiListener) {

        Call<UserData.StatusRes> call = mUserData.updatePassword(userId, req);
        call.enqueue(new Callback<UserData.StatusRes>() {
            @Override
            public void onResponse(Call<UserData.StatusRes> call, Response<UserData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 사업자 등록 번호 확인 요청
    public void checkRegistrationNumber(String number, OnFinishApiListener onFinishApiListener) {

        Call<Auth.StatusRes> call = mUserData.checkRegistrationNumber(number);
        call.enqueue(new Callback<Auth.StatusRes>() {
            @Override
            public void onResponse(Call<Auth.StatusRes> call, Response<Auth.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Auth.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 사용자 삭제 요청
    public void deleteUser(String id, OnFinishApiListener onFinishApiListener) {

        Call<UserData.StatusRes> call = mUserData.deleteUser(id);
        call.enqueue(new Callback<UserData.StatusRes>() {
            @Override
            public void onResponse(Call<UserData.StatusRes> call, Response<UserData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
