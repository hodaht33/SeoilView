package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.db.model.User;
import seoil.capstone.som.data.network.uri.UserUri;
import seoil.capstone.som.data.network.model.LoginRequest;
import seoil.capstone.som.data.network.model.LoginResponse;
import seoil.capstone.som.data.network.model.RegisterRequest;
import seoil.capstone.som.data.network.model.RegisterResponse;
import seoil.capstone.som.data.network.OnFinishApiListener;

// "https://leebera.name/user"에서 사용자의 정보를 모두 받아오는 api
public class UserRestApi {

    private UserUri mUserData;

    public UserRestApi(Retrofit retrofit) {

        mUserData = retrofit.create(UserUri.class);
    }

    public void get(LoginRequest loginRequest, OnFinishApiListener onFinishApiListener) {

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

    public void insertCustomer(RegisterRequest.Customer registerRequest, OnFinishApiListener<RegisterResponse> onFinishApiListener) {

        Call<RegisterResponse> call = mUserData.insertCustomer(registerRequest.getId(),
                registerRequest.getPwd(),
                registerRequest.getBirthdate(),
                registerRequest.getGender(),
                registerRequest.getEmail(),
                registerRequest.getPhoneNumber());
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

        Call<RegisterResponse> call = mUserData.insertManager(registerRequest.getId(),
                registerRequest.getPwd(),
                registerRequest.getBirthdate(),
                registerRequest.getGender(),
                registerRequest.getEmail(),
                registerRequest.getPhoneNumber(),
                registerRequest.getCorporateNumber(),
                registerRequest.getStoreName(),
                registerRequest.getStoreAddr(),
                registerRequest.getStoreCategory());
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

    public void delete(Object key) {

        if (key instanceof User) {

            // TODO: Call<DeleteResponse> call = mUserData.deleteUser(deleteRequest.getId());
            // TODO: call.enqueue(new Callback<DeleteResponse>() {});
        }
    }
}
