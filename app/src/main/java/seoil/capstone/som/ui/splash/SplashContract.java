package seoil.capstone.som.ui.splash;

import android.content.Context;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.LoginDTO;

// 스플래시 MVP 인터페이스
public interface SplashContract {

    interface Interactor extends BaseContract.Interactor {

        void login(LoginDTO.LoginReq req, OnFinishApiListener onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void changeView(boolean isMain);
    }

    interface Presenter extends BaseContract.Presenter<SplashContract.View> {

        void login(String id, String pwd, Context context);
    }
}
