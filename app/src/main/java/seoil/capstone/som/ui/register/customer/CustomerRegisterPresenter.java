package seoil.capstone.som.ui.register.customer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.UserRestApi;
import seoil.capstone.som.data.network.model.RegisterResponse;
import seoil.capstone.som.ui.login.LoginActivity;
import seoil.capstone.som.ui.main.MainActivity;

public class CustomerRegisterPresenter implements CustomerRegisterContract.Presenter {

    private CustomerRegisterContract.View mView;
    private CustomerRegisterContract.Interactor mInteractor;

    @Override
    public void setView(CustomerRegisterContract.View view) {
        this.mView = view;
    }

    @Override
    public void releaseView() {
        this.mView = null;
    }

    @Override
    public void createInteractor() {
        mInteractor = new CustomerRegisterInteractor();
    }

    @Override
    public void releaseInteractor() {
        this.mInteractor = null;
    }

    @Override
    public void register(Context context, String platform, String id, String pwd, String birthdate, String gender, String email, String phoneNumber, boolean marketingAgreement) {

        OnFinishApiListener<RegisterResponse> onFinishApiListener = new OnFinishApiListener<RegisterResponse>() {
            @Override
            public void onSuccess(RegisterResponse registerResponse) {

                if (registerResponse.getStatus() == UserRestApi.SUCCESS) {

                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                    if (platform.equals("naver")
                    && platform.equals("kakao")) {

                        intent.setComponent(new ComponentName(context, MainActivity.class));
                        intent.putExtra("id", id);

                        mView.finishRegister(intent);
                    } else {

                        intent.setComponent(new ComponentName(context, LoginActivity.class));

                        mView.finishRegister(intent);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

                Log.d("CRegitPresenter", "Error : " + t);
            }
        };

//        Log.d("CusPresenter", id);
//        Log.d("CusPresenter", pwd);
//        Log.d("CusPresenter", birthdate);
//        Log.d("CusPresenter", gender);
//        Log.d("CusPresenter", email);
//        Log.d("CusPresenter", phoneNumber);
//        Log.d("CusPresenter", String.valueOf(marketingAgreement));

        mInteractor.register(id, pwd, birthdate, gender, email, phoneNumber, marketingAgreement, onFinishApiListener);
    }
}
