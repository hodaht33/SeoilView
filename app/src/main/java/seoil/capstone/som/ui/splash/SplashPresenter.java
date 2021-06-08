package seoil.capstone.som.ui.splash;

import android.content.Context;
import android.util.Log;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.UserApi;
import seoil.capstone.som.data.network.model.LoginDTO;

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

    // 로그인 요청
    @Override
    public void login(String id, String pwd, Context context) {

        OnFinishApiListener<LoginDTO.LoginRes> onFinishApiListener = new OnFinishApiListener<LoginDTO.LoginRes>() {
            @Override
            public void onSuccess(LoginDTO.LoginRes loginRes) {

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

        mInteractor.login(new LoginDTO.LoginReq(id, pwd), onFinishApiListener);
    }
}
