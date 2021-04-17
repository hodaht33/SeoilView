package seoil.capstone.som.ui.main;

import android.content.Context;

import com.kakao.sdk.user.UserApiClient;
import com.nhn.android.naverlogin.OAuthLogin;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainPresenter implements MainContract.Presenter, Function1<Throwable, Unit> {

    private MainContract.View view;
    private Context mContext;

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        this.view = null;
    }

    @Override
    public void createInteractor() {

    }

    @Override
    public void releaseInteractor() {

    }

    @Override
    public void setContext(Context context) {
        this.mContext = context;
    }

    @Override
    public void releaseContext() {
        this.mContext = null;
    }

    @Override
    public void naverLogout() {
        OAuthLogin.getInstance().logout(mContext);
    }

    @Override
    public void kakaoLogout() {
        UserApiClient.getInstance().logout(this);
    }

    @Override
    public Unit invoke(Throwable throwable) {
        return null;
    }
}
