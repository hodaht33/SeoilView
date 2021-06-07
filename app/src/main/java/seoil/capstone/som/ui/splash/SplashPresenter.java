package seoil.capstone.som.ui.splash;

import android.content.Context;
import android.util.Log;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.UserApi;
import seoil.capstone.som.data.network.model.Login;

public class SplashPresenter implements SplashContract.Presenter{

    private SplashContract.View mView;
    private SplashInteractor mInteractor;

    @Override
    public void setView(SplashContract.View view) {

        mView = view;
    }

    @Override
    public void releaseView() {

        mView = null;
    }

    @Override
    public void createInteractor() {

        mInteractor = new SplashInteractor();
    }

    @Override
    public void releaseInteractor() {

        mInteractor = null;
    }

    @Override
    public void login(String id, String pwd, Context context) {

        OnFinishApiListener<Login.LoginRes> onFinishApiListener = new OnFinishApiListener<Login.LoginRes>() {
            @Override
            public void onSuccess(Login.LoginRes loginRes) {

                if (loginRes.getStatus() == UserApi.SUCCESS) {

                    GlobalApplication app = (GlobalApplication) context.getApplicationContext();
                    app.setUserId(id);
                    app.setUserCode(loginRes.getCode());

                    mView.changeView(true);
                } else {

                    mView.changeView(false);
                }
            }
            @Override
            public void onFailure(Throwable t) {

                Log.d("SplashPresenter", t.toString());
            }
        };

        mInteractor.login(new Login.LoginReq(id, pwd), onFinishApiListener);
    }
}
