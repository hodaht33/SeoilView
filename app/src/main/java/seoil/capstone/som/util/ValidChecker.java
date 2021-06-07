package seoil.capstone.som.util;

import android.util.Patterns;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;

// 회원가입 유효성 검사
public class ValidChecker {

    // 유효성 검사 코드
    public final int ID_VALID = 0;
    public final int ID_EMPTY = 1;
    public final int ID_SHORT = 2;
    public final int ID_LONG = 3;

    public final int PWD_VALID = 0;
    public final int PWD_EMPTY = 1;
    public final int PWD_CHECK_EMPTY = 2;
    public final int PWD_CHECK_NOT_EQUAL = 3;
    public final int PWD_INVALID = 4;

    public final int EMAIL_VALID = 0;
    public final int EMAIL_EMPTY = 1;
    public final int EMAIL_INVALID = 2;

    public final int PHONE_VALID = 0;
    public final int PHONE_EMPTY = 1;
    public final int PHONE_LENGTH_ERROR = 2;
    public final int PHONE_ERROR_OTHER_CHAR = 3;

    public final int BIRTH_VALID = 0;
    public final int BIRTH_EMPTY = 1;
    public final int BIRTH_ERROR_OTHER_CHAR = 2;
    public final int BIRTH_ERROR_LENGTH = 3;

    public final int GENDER_VALID = 0;
    public final int GENDER_INVALID = 1;

    public final int NEEDED_VALID = 0;
    public final int TERMS_OF_USE_NEEDED = 1;
    public final int PERSONAL_INFO_NEEDED = 2;

    // 아이디 유효성 검사
    public int idValid(String id) {

        if (id.isEmpty()) {

            return ID_EMPTY;
        } else if (id.length() < 8) {

            return ID_SHORT;
        } else if (id.length() > 20) {

            return ID_LONG;
        }

        return ID_VALID;
    }

    // 아이디 중복 검사
    public boolean checkIdValid(String id, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().checkIdDuplicate(id, onFinishApiListener);

        return true;
    }

    // 비밀번호 유효성 검사
    public int pwdValid(String pwdText, String pwdCheckText) {

        if (pwdText.isEmpty()) {

            return PWD_EMPTY;
        } else if (!pwdText.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{10,}$")) {

            // 영문자, 숫자, 특수문자가 하나라도 들어가있는 10자이상의 문자열이 아니면 유효하지 않음
            return PWD_INVALID;
        } else if (pwdCheckText.isEmpty()) {

            return PWD_CHECK_EMPTY;
        } else if (!pwdText.equals(pwdCheckText)) {

            return PWD_CHECK_NOT_EQUAL;
        }

        return PWD_VALID;
    }

    // 이메일 유효성 검사
    public int emailValid(String emailText) {

        if (emailText.isEmpty()) {

            return EMAIL_EMPTY;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {

            return EMAIL_INVALID;
        }

        return EMAIL_VALID;
    }

    // 핸드폰 번호 유효성 검사
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

    // 생년월일 유효성 검사
    public int birthdateValid(String birthdateText) {


        if (birthdateText.isEmpty()) {

            return BIRTH_EMPTY;
        } else if (!birthdateText.matches("^\\d*$")) {  // 숫자 0~9가 아닌 문자가 없을 수도 무한정 많을 수도 있음

            return BIRTH_ERROR_OTHER_CHAR;
        } else if (birthdateText.length() != 8) {

            return BIRTH_ERROR_LENGTH;
        }

        return BIRTH_VALID;
    }

    // 성별 유효성 검사
    public int genderValid(Boolean maleChkBoxBool, Boolean femaleChkBoxBool) {

        if (!(maleChkBoxBool || femaleChkBoxBool)) {

            return GENDER_INVALID;
        }

        return GENDER_VALID;
    }

    // 정보 동의 체크 검사
    public int neededCheck(Boolean termsOfUseChkBoxBool, Boolean personalInfoChBoxBool) {

        if (!termsOfUseChkBoxBool) {

            return TERMS_OF_USE_NEEDED;
        } else if (!personalInfoChBoxBool) {

            return PERSONAL_INFO_NEEDED;
        }

        return NEEDED_VALID;
    }
}
