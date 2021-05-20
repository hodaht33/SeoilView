package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.model.Auth;
import seoil.capstone.som.data.network.model.Login;
import seoil.capstone.som.data.network.model.Register;
import seoil.capstone.som.data.network.model.retrofit.User;
import seoil.capstone.som.data.network.OnFinishApiListener;

// "https://leebera.name/api/user"에서 사용자의 정보를 모두 받아오는 api
public class UserApi {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;

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

    public void insertCustomer(Register.Customer registerRequest, OnFinishApiListener<Register.RegisterRes> onFinishApiListener) {

        Call<Register.RegisterRes> call = mUserData.insertCustomer(registerRequest);
        call.enqueue(new Callback<Register.RegisterRes>() {
            @Override
            public void onResponse(Call<Register.RegisterRes> call, Response<Register.RegisterRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Register.RegisterRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertManager(Register.Manager registerRequest, OnFinishApiListener onFinishApiListener) {

        Call<Register.RegisterRes> call = mUserData.insertManager(registerRequest);
        call.enqueue(new Callback<Register.RegisterRes>() {
            @Override
            public void onResponse(Call<Register.RegisterRes> call, Response<Register.RegisterRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Register.RegisterRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

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

    public void update() {

        // TODO: Call<ChangePwdResponse> call = mUserData.updateUser(changePwdRequest.getId(), getPrevPwd(), getPwd());
        // TODO: call.enqueue(new Callback<ChangePwdResponse>() {});
    }

    public void delete() {

        // TODO: Call<DeleteResponse> call = mUserData.deleteUser(deleteRequest.getId());
        // TODO: call.enqueue(new Callback<DeleteResponse>() {});

    }
}
