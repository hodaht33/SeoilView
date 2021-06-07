package seoil.capstone.som.ui.register.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.UserApi;
import seoil.capstone.som.data.network.model.Auth;
import seoil.capstone.som.data.network.model.UserData;
import seoil.capstone.som.ui.login.LoginActivity;
import seoil.capstone.som.ui.main.MainActivity;
import seoil.capstone.som.util.ValidChecker;

public class ManagerRegisterPresenter extends ValidChecker implements ManagerRegisterContract.Presenter {

    public static final String TAG = "MRegitPresenter";
    private ManagerRegisterContract.View mView;
    private ManagerRegisterInteractor mInteractor;

    public final int CORPORATE_NUMBER_NOT_NUM = 1;
    public final int CORPORATE_NUMBER_LENGTH_ERROR = 2;
    public final int CORPORATE_NUMBER_NOT_VALID = 3;
    public final int CORPORATE_NUMBER_VALID = 0;
    public final int MARKET_NAME_NOT_VALID = 1;
    public final int MARKET_NAME_VALID = 0;
    public final int MARKET_ADDRESS_NOT_VALID = 1;
    public final int MARKET_ADDRESS_VALID = 0;
    public final int POSTAL_CODE_NOT_VALID = 1;
    public final int POSTAL_CODE_VALID = 0;
    public final int DETAILED_ADDRESS_NOT_VALID = 1;
    public final int DETAILED_ADDRESS_VALID = 0;
    private boolean mIsValid;

    @Override
    public void setView(ManagerRegisterContract.View view) {
        mView = view;
    }

    @Override
    public void releaseView() {
        mView = null;
    }

    @Override
    public void createInteractor() {
        mInteractor = new ManagerRegisterInteractor();
    }

    @Override
    public void releaseInteractor() {
        mInteractor = null;
    }

    @Override
    public void register(Context context, String platform, String id, String pwd, String birthdate, String gender, String email, String phoneNumber,
                         String shopCode, String shopName, String shopPostCode, String shopAddress, String shopCategory, boolean marketingAgreement) {

        OnFinishApiListener<UserData.StatusRes> onFinishApiListener = new OnFinishApiListener<UserData.StatusRes>() {
            @Override
            public void onSuccess(UserData.StatusRes registerResponse) {

                if (registerResponse.getStatus() == UserApi.SUCCESS) {

                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                    if (platform.equals("naver")
                            || platform.equals("kakao")) {

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

                Log.d("MRegitPresenter", "Error : " + t);
            }
        };

        mInteractor.register(new UserData.Manager(id, pwd, birthdate, gender, email, phoneNumber, marketingAgreement, shopCode, shopName, shopPostCode, shopAddress, shopCategory), onFinishApiListener);
    }

    @Override
    public void sendSms(String phoneNumber) {

        OnFinishApiListener<Auth.StatusRes> onFinishApiListener = new OnFinishApiListener<Auth.StatusRes>() {
            @Override
            public void onSuccess(Auth.StatusRes statusRes) {

                if (statusRes.getStatus() == UserApi.SUCCESS) {

                    mView.showDialog("인증번호가 발송되었습니다.");
                }
            }

            @Override
            public void onFailure(Throwable t) {

                Log.d(TAG, "sms Error : " + t);
            }
        };

        mInteractor.sendSms(new Auth.Req(phoneNumber, null), onFinishApiListener);
    }

    @Override
    public void sendAuthCode(String phoneNumber, String authCode) {

        OnFinishApiListener<Auth.StatusRes> onFinishApiListener = new OnFinishApiListener<Auth.StatusRes>() {
            @Override
            public void onSuccess(Auth.StatusRes statusRes) {

                mView.changePhoneAuthButton(statusRes.getStatus());
            }

            @Override
            public void onFailure(Throwable t) {

                Log.d(TAG, "phone auth Error : " + t);
            }
        };

        mInteractor.sendAuthCode(new Auth.Req(phoneNumber, authCode), onFinishApiListener);
    }

    public int checkCorporateNumber(String corporateNumber) {

        final int[] LOGIC_NUM = {1, 3, 7, 1, 3, 7, 1, 3, 5};

        if (!isNumeric(corporateNumber)) {

            return CORPORATE_NUMBER_NOT_NUM;
        } else if (corporateNumber.length() != 10) {

            return CORPORATE_NUMBER_LENGTH_ERROR;
        }

        int sum = 0;
        int checkSum = 0;

        for(int i = 0; i < LOGIC_NUM.length; i++) {

            if(corporateNumber.charAt(i) == '0') {

                continue;
            }
            sum = sum + (LOGIC_NUM[i] * Integer.parseInt(String.valueOf(corporateNumber.charAt(i))));
        }
        sum = sum + ((LOGIC_NUM[8] * Integer.parseInt(String.valueOf(corporateNumber.charAt(8)))) / 10);
        checkSum = 10 - (sum % 10);

        if (checkSum == Integer.parseInt(String.valueOf(corporateNumber.charAt(9)))) {

            return CORPORATE_NUMBER_VALID;
        }
        return CORPORATE_NUMBER_NOT_VALID;
    }

    public void checkCorporateNumberValid(String corporateNumber) {

        OnFinishApiListener<Auth.StatusRes> onFinishApiListener = new OnFinishApiListener<Auth.StatusRes>() {
            @Override
            public void onSuccess(Auth.StatusRes statusRes) {

                if (statusRes.getStatus() == UserApi.SUCCESS) {

                    mIsValid = true;
                    mView.hideProgress();
                } else {

                    mIsValid = false;
                    mView.hideProgress();
                }
            }

            @Override
            public void onFailure(Throwable t) {

                mIsValid = false;
                mView.hideProgress();
            }
        };

        AppApiHelper.getInstance().checkRegistrationNumber(corporateNumber, onFinishApiListener);
    }

    public boolean isNumeric(String str) {
        if(str == null) {

            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {

                return false;
            }
        }

        return true;
    }

    public int marketNameValid(String name) {

        if (name.isEmpty()) {

            return MARKET_NAME_NOT_VALID;
        }

        return MARKET_NAME_VALID;
    }

    public int addressValid(String address) {

        if(address.isEmpty()) {

            return MARKET_ADDRESS_NOT_VALID;
        }

        return MARKET_ADDRESS_VALID;
    }

    public int postalCodeValid(String postalCode) {

        if(postalCode.isEmpty()) {

            return POSTAL_CODE_NOT_VALID;
        }

        return POSTAL_CODE_VALID;
    }

    public int detailedAddressValid(String detailedAddress) {

        if(detailedAddress.isEmpty()) {

            return DETAILED_ADDRESS_NOT_VALID;
        }

        return DETAILED_ADDRESS_VALID;
    }

    public boolean getIsValid() {

        return mIsValid;
    }
}
