package seoil.capstone.som.ui.register.manager;

import android.content.Context;
import android.util.Log;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.UserApi;
import seoil.capstone.som.data.network.model.Check;
import seoil.capstone.som.ui.register.ValidChecker;

public class ManagerRegisterPresenter extends ValidChecker implements ManagerRegisterContract.Presenter {

    private ManagerRegisterContract.View mView;
    private ManagerRegisterInteractor mInteractor;

    public final int CORPORATE_NUMBER_NOT_NUM = 1;
    public final int CORPORATE_NUMBER_LENGTH_ERROR = 2;
    public final int CORPORATE_NUMBER_NOT_VALID = 3;
    public final int CORPORATE_NUMBER_VALID = 0;
    public final int MARKET_NAME_NOT_VALID = 1;
    public final int MARKET_NAME_VALID = 0;
    public final int MARKET_CATEGORY_NOT_VALID = 1;
    public final int MARKET_CATEGORY_VALID = 0;
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
    public void register(Context context, String platform, String id, String pwd, String birthdate, String gender, String email, String phoneNumber, boolean marketingAgreement) {

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

        OnFinishApiListener<Check.StatusRes> onFinishApiListener = new OnFinishApiListener<Check.StatusRes>() {
            @Override
            public void onSuccess(Check.StatusRes statusRes) {

                if (statusRes.getStatus() == UserApi.SUCCESS) {

                    mIsValid = true;
                    mView.hideProgress();
                    Log.d("test","true");
                } else {

                    mIsValid = false;
                    mView.hideProgress();
                    Log.d("test", "false");
                }
            }

            @Override
            public void onFailure(Throwable t) {

                mIsValid = false;
                mView.hideProgress();
                Log.d("test", t.toString());
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

    public int marketCategoryValid(String category) {

        if(category.isEmpty()) {

            return MARKET_CATEGORY_NOT_VALID;
        }

        return MARKET_CATEGORY_VALID;
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
