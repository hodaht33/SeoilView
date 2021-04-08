package seoil.capstone.som.ui.main;

import seoil.capstone.som.base.BaseContract;

public interface MainContract {

    interface View extends BaseContract.View {

    }

    interface Presenter extends BaseContract.Presenter<MainContract.View> {
        void naverLogout();
        void kakaoLogout();
    }
}
