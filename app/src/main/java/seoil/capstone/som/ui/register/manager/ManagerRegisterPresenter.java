package seoil.capstone.som.ui.register.manager;

import android.content.Context;

import seoil.capstone.som.ui.register.ValidChecker;

public class ManagerRegisterPresenter extends ValidChecker implements ManagerRegisterContract.Presenter {

    private ManagerRegisterContract.View mView;
    private ManagerRegisterInteractor mInteractor;

    public final int CORPORATE_NUMBER_NOT_NUM = 1;
    public final int CORPORATE_NUMBER_LENGTH_ERROR = 2;
    public final int CORPORATE_NUMBER_NOT_VALID = 3;
    public final int CORPORATE_NUMBER_VALID = 0;

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

    public int checkCorporateNumber(String businessNumber) {

        final int[] LOGIC_NUM = {1, 3, 7, 1, 3, 7, 1, 3, 5, 1};

        if (!isNumeric(businessNumber)) {

            return CORPORATE_NUMBER_NOT_NUM;
        } else if (businessNumber.length() != 10) {

            return CORPORATE_NUMBER_LENGTH_ERROR;
        }

        int sum = 0;

        for(int i = 0; i < 9; i ++) {

            sum += (LOGIC_NUM[i] * Character.getNumericValue(businessNumber.charAt(i)));
        }

        if (10 - ((sum + Math.floor(LOGIC_NUM[8] * Character.getNumericValue(businessNumber.charAt(8))) / 10 )) % 10
                == Character.getNumericValue(businessNumber.charAt(9))) {

            return CORPORATE_NUMBER_VALID;
        }
        return CORPORATE_NUMBER_NOT_VALID;
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
}
