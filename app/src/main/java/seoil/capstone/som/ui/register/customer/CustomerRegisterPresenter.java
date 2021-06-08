package seoil.capstone.som.ui.register.customer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.UserApi;
import seoil.capstone.som.data.network.model.AuthDTO;
import seoil.capstone.som.data.network.model.UserDTO;
import seoil.capstone.som.ui.login.LoginActivity;
import seoil.capstone.som.ui.main.MainActivity;
import seoil.capstone.som.util.ValidChecker;

public class CustomerRegisterPresenter extends ValidChecker implements CustomerRegisterContract.Presenter {

    public static final String TAG = "CRegitPresenter";
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

        OnFinishApiListener<UserDTO.StatusRes> onFinishApiListener = new OnFinishApiListener<UserDTO.StatusRes>() {
            @Override
            public void onSuccess(UserDTO.StatusRes registerResponse) {

                if (registerResponse.getStatus() == UserApi.SUCCESS) {

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

                Log.d(TAG, "register Error : " + t);
            }
        };

        mInteractor.register(new UserDTO.Customer(id, pwd, birthdate, gender, email, phoneNumber, marketingAgreement), onFinishApiListener);
    }

    @Override
    public void sendSms(String phoneNumber) {

        OnFinishApiListener<AuthDTO.StatusRes> onFinishApiListener = new OnFinishApiListener<AuthDTO.StatusRes>() {
            @Override
            public void onSuccess(AuthDTO.StatusRes statusRes) {

                if (statusRes.getStatus() == UserApi.SUCCESS) {

                    mView.showDialog("인증번호가 발송되었습니다.");
                }
            }

            @Override
            public void onFailure(Throwable t) {

                Log.d(TAG, "sms Error : " + t);
            }
        };

        mInteractor.sendSms(new AuthDTO.Req(phoneNumber, null), onFinishApiListener);
    }

    @Override
    public void sendAuthCode(String phoneNumber, String authCode) {

        OnFinishApiListener<AuthDTO.StatusRes> onFinishApiListener = new OnFinishApiListener<AuthDTO.StatusRes>() {
            @Override
            public void onSuccess(AuthDTO.StatusRes statusRes) {

                mView.changePhoneAuthButton(statusRes.getStatus());
            }

            @Override
            public void onFailure(Throwable t) {

                Log.d(TAG, "phone auth Error : " + t);
            }
        };

        mInteractor.sendAuthCode(new AuthDTO.Req(phoneNumber, authCode), onFinishApiListener);
    }
}
