package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.model.Check;
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

    private User mUserData;

    public UserApi(Retrofit retrofit) {

        mUserData = retrofit.create(User.class);
    }

    public void login(Login.LoginReq req, OnFinishApiListener onFinishApiListener) {

        Call<Login.LoginRes> call = mUserData.getLoginData(req);
        call.enqueue(new Callback<Login.LoginRes>() {
            @Override
            public void onResponse(Call<Login.LoginRes> call, Response<Login.LoginRes> response) {

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Login.LoginRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void checkIdDuplicate(String id, OnFinishApiListener<Check.StatusRes> onFinishApiListener) {

        Call<Check.StatusRes> call = mUserData.checkIdDuplicate(id);
        call.enqueue(new Callback<Check.StatusRes>() {
            @Override
            public void onResponse(Call<Check.StatusRes> call, Response<Check.StatusRes> response) {

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Check.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertCustomer(Register.Customer registerRequest, OnFinishApiListener<Register.RegisterRes> onFinishApiListener) {

        Call<Register.RegisterRes> call = mUserData.insertCustomer(registerRequest);
        call.enqueue(new Callback<Register.RegisterRes>() {
            @Override
            public void onResponse(Call<Register.RegisterRes> call, Response<Register.RegisterRes> response) {

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

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Register.RegisterRes> call, Throwable t) {

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
