package seoil.capstone.som.ui.main;

import android.content.Context;
import android.content.res.Resources;

import com.kakao.sdk.user.UserApiClient;
import com.nhn.android.naverlogin.OAuthLogin;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainPresenter implements MainContract.Presenter, Function1<Throwable, Unit> {

    private MainContract.View view;
    private Context context;
    private Resources res;

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        this.view = null;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void releaseContext() {
        this.context = null;
    }

    @Override
    public void setResources(Resources res) {
        this.res = res;
    }

    @Override
    public void releaseResources() {
        this.res = null;
    }

    @Override
    public void naverLogout() {
        OAuthLogin.getInstance().logout(context);
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
