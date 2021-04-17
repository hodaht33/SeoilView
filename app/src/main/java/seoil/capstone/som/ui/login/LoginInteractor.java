package seoil.capstone.som.ui.login;

import android.util.Log;

import seoil.capstone.som.data.model.User;
import seoil.capstone.som.data.network.model.LoginRequest;
import seoil.capstone.som.data.repository.OnFinishRepositoryListener;
import seoil.capstone.som.data.repository.UserRepository;

public class LoginInteractor implements LoginContract.Interactor {

    @Override
    public void login(String id, String pwd, OnFinishRepositoryListener onFinishRepositoryListener) {
        Log.d("Interactor", "request");
        UserRepository.getInstance().get(onFinishRepositoryListener, new LoginRequest(id, pwd));
    }
}
