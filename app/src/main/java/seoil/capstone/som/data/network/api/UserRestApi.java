package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.model.IdDuplicateResponse;
import seoil.capstone.som.data.network.model.retrofit.User;
import seoil.capstone.som.data.network.model.LoginRequest;
import seoil.capstone.som.data.network.model.LoginResponse;
import seoil.capstone.som.data.network.model.RegisterRequest;
import seoil.capstone.som.data.network.model.RegisterResponse;
import seoil.capstone.som.data.network.OnFinishApiListener;

// "https://leebera.name/api/user"에서 사용자의 정보를 모두 받아오는 api
public class UserRestApi {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int LOGIN_FAIL_ID = 2;
    public static final int LOGIN_FAIL_PWD = 3;
    public static final int NEW_USER = 4;   // 카카오나 네이버로 로그인 시 새로운 회원이면 이에 맞는 처리 수행
    public static final int ID_DUPLICATE = 5;

    private User mUserData;

    public UserRestApi(Retrofit retrofit) {

        mUserData = retrofit.create(User.class);
    }

    public void login(LoginRequest loginRequest, OnFinishApiListener onFinishApiListener) {

        Call<LoginResponse.SomRestLoginApi> call = mUserData.getLoginData(loginRequest.getId(), loginRequest.getPwd());
        call.enqueue(new Callback<LoginResponse.SomRestLoginApi>() {
            @Override
            public void onResponse(Call<LoginResponse.SomRestLoginApi> call, Response<LoginResponse.SomRestLoginApi> response) {

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse.SomRestLoginApi> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void checkIdDuplicate(String id, OnFinishApiListener<IdDuplicateResponse> onFinishApiListener) {

        Call<IdDuplicateResponse> call = mUserData.checkIdDuplicate(id);
        call.enqueue(new Callback<IdDuplicateResponse>() {
            @Override
            public void onResponse(Call<IdDuplicateResponse> call, Response<IdDuplicateResponse> response) {

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<IdDuplicateResponse> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertCustomer(RegisterRequest.Customer registerRequest, OnFinishApiListener<RegisterResponse> onFinishApiListener) {

        Call<RegisterResponse> call = mUserData.insertCustomer(registerRequest);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertManager(RegisterRequest.Manager registerRequest, OnFinishApiListener onFinishApiListener) {

        Call<RegisterResponse> call = mUserData.insertManager(registerRequest);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // customer와 manager의 request를 따로 두어 코드 중복 회피
    // Object가 아닌 String으로 변경(String타입인 id가 키)
    // TODO: add parameter with UpdateRequest
    public void update() {

        // TODO: Call<ChangePwdResponse> call = mUserData.updateUser(changePwdRequest.getId(), getPrevPwd(), getPwd());
        // TODO: call.enqueue(new Callback<ChangePwdResponse>() {});
    }

    public void delete() {

        // TODO: Call<DeleteResponse> call = mUserData.deleteUser(deleteRequest.getId());
        // TODO: call.enqueue(new Callback<DeleteResponse>() {});

    }
}
