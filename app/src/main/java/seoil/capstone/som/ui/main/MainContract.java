package seoil.capstone.som.ui.main;

import android.content.Context;

import seoil.capstone.som.base.BaseContract;

public interface MainContract {

    interface View extends BaseContract.View {

    }

    interface Presenter extends BaseContract.Presenter<MainContract.View> {

        void setContext(Context context);
        void releaseContext();
        void naverLogout();
        void kakaoLogout();
    }
}
