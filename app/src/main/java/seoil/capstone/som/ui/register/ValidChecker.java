package seoil.capstone.som.ui.register;

import android.app.Activity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.util.Utility;

public class ValidChecker {

    private Activity mActivity;
    public final int ID_EMPTY = 1;
    public final int ID_SHORT = 2;
    public final int ID_LONG = 3;
    public final int ID_VALID = 0;
    public final int PWD_EMPTY = 1;
    public final int PWD_CHECK_EMPTY = 2;
    public final int PWD_CHECK_NOT_EQUAL = 3;
    public final int PWD_VALID = 0;
    public final int EMAIL_EMPTY = 1;
    public final int EMAIL_NOT_VALID = 2;
    public final int EMAIL_VALID = 0;
    public final int PHONE_EMPTY = 1;
    public final int PHONE_LENGTH_ERROR = 2;
    public final int PHONE_ERROR_OTHER_CHAR = 3;
    public final int PHONE_VALID = 0;
    public final int BIRTH_EMPTY = 1;
    public final int BIRTH_ERROR_OTHER_CHAR = 2;
    public final int BIRTH_ERROR_LENTGTH = 3;
    public final int BIRTH_VALID = 0;
    public final int GENDER_NOT_VALID = 1;
    public final int GENDER_VALID = 0;
    public final int TERMS_OF_USE_NEEDED = 1;
    public final int PERSONAL_INFO_NEEDED = 2;
    public final int NEEDED_VALID = 0;


    public void releaseActivity() {

        mActivity = null;
    }

    public int idValid(String id) {

        if (id.isEmpty()) {

            return ID_EMPTY;
        } else if (id.length() < 3) {

            return ID_SHORT;
        } else if (id.length() > 20) {

            return ID_LONG;
        }

        return ID_VALID;
    }

    public boolean checkIdValid(String id, OnFinishApiListener onFinishApiListener) {

            AppApiHelper.getInstance().checkIdDuplicate(id, onFinishApiListener);

            return true;
        // TODO: Presenter와 Interactor로 나누면 해결될 문제 Presenter에서 onSuccess내에서 뷰를 바꿔주는 함수를 호출하면 되기 때문, 추후 리팩토링
        // TODO: 하지만 중복을 피하면서 모두 분리하면 클래스가 늘어나는데 괜찮은가?
    }

    public int pwdValid(String pwdText, String pwdCheckText) {

        if (pwdText.isEmpty()) {

            return PWD_EMPTY;
        } else if (pwdCheckText.isEmpty()) {

            return PWD_CHECK_EMPTY;
        }
        if (!pwdText.equals(pwdCheckText)) {

            return PWD_CHECK_NOT_EQUAL;
        }

        return PWD_VALID;
    }

    public int emailValid(String emailText) {

        if (emailText.isEmpty()) {

            return EMAIL_EMPTY;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {

            return EMAIL_NOT_VALID;
        }

        return EMAIL_VALID;
    }

//    public String phoneNumberToInternationalNumber(String phoneNumber) {
//
//        // 유효성검사를 이미 했다고 치고 변환
//        // TODO: SMS API에 따라 다른지 확인 후 변경
//        return phoneNumber.replaceFirst("0", "+82");
//    }

    public int phoneNumberValid(String phoneNumberText) {

        if (phoneNumberText.isEmpty()) {

            return PHONE_EMPTY;
        } else if (phoneNumberText.length() != 11) {

            return PHONE_LENGTH_ERROR;
        } else if (!phoneNumberText.matches("^\\d*$")) {    // 숫자가 아닌 문자가 하나라도 있으면

            return PHONE_ERROR_OTHER_CHAR;
        }

        return PHONE_VALID;
    }

    public int birthdateValid(String birthdateText) {


        if (birthdateText.isEmpty()) {

            return BIRTH_EMPTY;
        } else if (!birthdateText.matches("^\\d*$")) {  // 숫자 0~9가 아닌 문자가 없을 수도 무한정 많을 수도 있음

            return BIRTH_ERROR_OTHER_CHAR;
        } else if (birthdateText.length() != 8) {

            return BIRTH_ERROR_LENTGTH;
        }

        return BIRTH_VALID;
    }

    public int genderValid(Boolean maleChkBoxBool, Boolean femaleChkBoxBool) {

        if (!(maleChkBoxBool || femaleChkBoxBool)) {

            return GENDER_NOT_VALID;
        }

        return GENDER_VALID;
    }

    public int neededCheck(Boolean termsOfUseChkBoxBool, Boolean personalInfoChBoxBool) {

        if (!termsOfUseChkBoxBool) {

            return TERMS_OF_USE_NEEDED;
        } else if (!personalInfoChBoxBool) {

            return PERSONAL_INFO_NEEDED;
        }

        return NEEDED_VALID;
    }

}
