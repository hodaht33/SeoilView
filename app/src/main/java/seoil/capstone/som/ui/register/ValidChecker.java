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
import seoil.capstone.som.data.network.api.UserRestApi;
import seoil.capstone.som.data.network.model.IdDuplicateResponse;
import seoil.capstone.som.util.Utility;

public class ValidChecker {

    public static final String TAG = "ValidChecker";
    private Activity mActivity;

    public ValidChecker(Activity activity) {

        mActivity = activity;
    }

    public void releaseActivity() {

        mActivity = null;
    }

    private boolean isIdValid(TextInputEditText idEditText) {

        String id = idEditText.getText().toString();

        if (id.isEmpty()) {

            idEditText.setError("아이디를 입력해주세요.");
            idEditText.requestFocus();
            Utility.getInstance().renderKeyboard(mActivity);

            return false;
        } else if (id.length() < 3) {

            idEditText.setError("아이디가 너무 짧습니다. 3자 이상 입력해주세요.");
            idEditText.requestFocus();
            Utility.getInstance().renderKeyboard(mActivity);

            return false;
        } else if (id.length() > 20) {

            idEditText.setError("아이디가 너무 깁니다. 20자 이하로 입력해주세요.");
            idEditText.requestFocus();
            Utility.getInstance().renderKeyboard(mActivity);

            return false;
        }

        return true;
    }

    public void checkIdValid(TextInputEditText idEditText, OnFinishApiListener onFinishApiListener) {

        if (isIdValid(idEditText)) {

            AppApiHelper.getInstance().checkIdDuplicate(idEditText.getText().toString(), onFinishApiListener);
        }

        // TODO: Presenter와 Interactor로 나누면 해결될 문제 Presenter에서 onSuccess내에서 뷰를 바꿔주는 함수를 호출하면 되기 때문, 추후 리팩토링
        // TODO: 하지만 중복을 피하면서 모두 분리하면 클래스가 늘어나는데 괜찮은가?
    }

    public boolean isPwdValid(TextInputEditText pwdEditText, TextInputEditText pwdCheckEditText) {

        Log.d(TAG, "pwd");
        String pwd = pwdEditText.getText().toString();
        String pwdCheck = pwdCheckEditText.getText().toString();
        if (pwd.isEmpty()) {

            pwdEditText.setError("비밀번호를 입력해주세요.");
            pwdEditText.requestFocus();
            Utility.getInstance().renderKeyboard(mActivity);

            return false;
        } else if (pwdCheck.isEmpty()) {

            pwdCheckEditText.setError("비밀번호를 입력해주세요.");
            pwdCheckEditText.requestFocus();
            Utility.getInstance().renderKeyboard(mActivity);

            return false;
        }
        if (!pwd.equals(pwdCheck)) {

            pwdCheckEditText.setError("비밀번호가 서로 다릅니다.");
            pwdCheckEditText.requestFocus();
            Utility.getInstance().renderKeyboard(mActivity);

            return false;
        }

        return true;
    }

    public boolean isEmailValid(TextInputEditText emailEditText) {

        Log.d(TAG, "email");
        String email = emailEditText.getText().toString();

        if (email.isEmpty()) {

            emailEditText.setError("이메일을 입력해주세요.");
            emailEditText.requestFocus();
            Utility.getInstance().renderKeyboard(mActivity);

            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            emailEditText.setError("올바른 이메일을 입력해주세요.");
            emailEditText.requestFocus();
            Utility.getInstance().renderKeyboard(mActivity);

            return false;
        }

        return true;
    }

    public String phoneNumberToInternationalNumber(String phoneNumber) {

        // 유효성검사를 이미 했다고 치고 변환
        // TODO: SMS API에 따라 다른지 확인 후 변경
        Log.d(TAG, "phoneToInternal");
        return phoneNumber.replaceFirst("0", "+82");
    }

    public boolean isPhoneNumberValid(TextInputEditText phoneNumberEditText) {

        String phoneNumber = phoneNumberEditText.getText().toString();

        if (phoneNumber.isEmpty()) {

            phoneNumberEditText.setError("핸드폰 번호를 입력해주세요.");
            phoneNumberEditText.requestFocus();
            Utility.getInstance().renderKeyboard(mActivity);

            return false;
        } else if (phoneNumber.length() != 11) {

            phoneNumberEditText.setError("핸드폰 번호가 맞는지 확인해주세요.(010xxxxxxxx)");
            phoneNumberEditText.requestFocus();
            Utility.getInstance().renderKeyboard(mActivity);

            return false;
        } else if (!phoneNumber.matches("^\\d*$")) {    // 숫자가 아닌 문자가 하나라도 있으면

            phoneNumberEditText.setError("숫자만 입력해주세요. (ex : 19500101)");
            phoneNumberEditText.requestFocus();
            Utility.getInstance().renderKeyboard(mActivity);

            return false;
        }

        return true;
    }

    public boolean isBirthdateValid(TextInputEditText birthdateEditText) {

        Log.d(TAG, "birthdate");
        String birthdate = birthdateEditText.getText().toString();

        if (birthdate.isEmpty()) {

            birthdateEditText.setError("생년월일을 입력해주세요.");
            birthdateEditText.requestFocus();
            Utility.getInstance().renderKeyboard(mActivity);

            return false;
        } else if (!birthdate.matches("^\\d*$")) {  // 숫자 0~9가 아닌 문자가 없을 수도 무한정 많을 수도 있음

            birthdateEditText.setError("숫자만 입력해주세요. (ex : 19500101)");
            birthdateEditText.requestFocus();
            Utility.getInstance().renderKeyboard(mActivity);

            return false;
        } else if (birthdate.length() != 8) {

            birthdateEditText.setError("8자리를 입력해주세요. (ex : 19500101)");
            birthdateEditText.requestFocus();
            Utility.getInstance().renderKeyboard(mActivity);

            return false;
        }

        return true;
    }

    public boolean isGenderValid(CheckBox maleChkBox, CheckBox femaleChkBox, TextView errorEditText) {

        Log.d(TAG, "gender");
        if (!(maleChkBox.isChecked()
                || femaleChkBox.isChecked())) {

            errorEditText.setVisibility(View.VISIBLE);
            errorEditText.setText("성별을 선택해주세요.");

            return false;
        }

        return true;
    }

    public boolean isNeededCheck(CheckBox termsOfUseChkBox, CheckBox personalInfoChBox, TextView errorEditText) {

        Log.d(TAG, "needed");
        if (!termsOfUseChkBox.isChecked()) {

            errorEditText.setVisibility(View.VISIBLE);
            errorEditText.setText("이용약관 동의가 필요합니다.");

            return false;
        } else if (!personalInfoChBox.isChecked()) {

            errorEditText.setVisibility(View.VISIBLE);
            errorEditText.setText("개인정보 취급방침 동의가 필요합니다.");

            return false;
        }

        return true;
    }
}
