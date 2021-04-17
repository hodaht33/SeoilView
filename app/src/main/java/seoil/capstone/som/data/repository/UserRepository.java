package seoil.capstone.som.data.repository;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import seoil.capstone.som.base.BaseRepository;
import seoil.capstone.som.data.model.User;
import seoil.capstone.som.data.network.ApiClient;
import seoil.capstone.som.data.network.UserInterface;
import seoil.capstone.som.data.network.model.LoginRequest;
import seoil.capstone.som.data.network.model.LoginResponse;
import seoil.capstone.som.data.network.model.RegisterRequest;
import seoil.capstone.som.data.network.model.RegisterResponse;

// "https://leebera.name/user"에서 사용자의 정보를 모두 받아오는 repository
// singleton pattern
public class UserRepository implements BaseRepository {

    private static UserRepository instance;
    private UserInterface mUserData;

    public UserRepository() {

        mUserData = ApiClient.getRetrofit().create(UserInterface.class);
    }

    public static UserRepository getInstance() {

        if (instance == null) {

            instance = new UserRepository();
        }

        return instance;
    }

    public void get(OnFinishRepositoryListener onFinishRepositoryListener, LoginRequest loginRequest) {

        Call<LoginResponse> call = mUserData.getUser(loginRequest.getId(), loginRequest.getPwd());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                onFinishRepositoryListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                onFinishRepositoryListener.onFailure(t);
            }
        });
    }

    public void insert(OnFinishRepositoryListener onFinishRepositoryListener,  RegisterRequest registerRequest) {

        Call<RegisterResponse> call = mUserData.insertUser(registerRequest.getId(),
                registerRequest.getBirthdate(),
                registerRequest.getGender(),
                registerRequest.getEmail(),
                registerRequest.getPhoneNumber());
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                onFinishRepositoryListener.onSuccess(response.body().getState());
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                onFinishRepositoryListener.onFailure(t);
            }
        });

    }

    public void delete(Object key) {

        if (key instanceof User) {

            // TODO: Call<DeleteResponse> call = mUserData.deleteUser(deleteRequest.getId());
            // TODO: call.enqueue(new Callback<DeleteResponse>() {});
        }
    }

    // TODO: add parameter with ChangePwdRequest
    public void update() {

        // TODO: Call<ChangePwdResponse> call = mUserData.updateUser(changePwdRequest.getId(), getPrevPwd(), getPwd());
        // TODO: call.enqueue(new Callback<ChangePwdResponse>() {});
    }

    @Override
    public void save() {

    }
}
