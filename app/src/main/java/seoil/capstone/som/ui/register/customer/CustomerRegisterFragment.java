package seoil.capstone.som.ui.register.customer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import seoil.capstone.som.R;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.UserRestApi;
import seoil.capstone.som.data.network.model.IdDuplicate;
import seoil.capstone.som.ui.register.ValidChecker;

// TODO: 제대로된 MVP으로 만들어져 있지 않음, 추후 리팩토링 필요(presenter내에서 valid검사, id중복확인 요청은 interactor를 통해 수행)
public class CustomerRegisterFragment extends Fragment implements CustomerRegisterContract.View, View.OnClickListener, OnFinishApiListener<IdDuplicate.statusRes> {

    private CustomerRegisterPresenter mPresenter;
    private ValidChecker mValidChecker;
    private TextInputEditText mEditTextId;
    private TextInputEditText mEditTextPwd;
    private TextInputEditText mEditTextCheckPwd;
    private TextInputEditText mEditTextEmail;
    private TextInputEditText mEditTextPhoneNumber;
    private TextInputEditText mEditTextAuthCode;
    private TextInputEditText mEditTextBirthdate;
    private TextInputLayout mTextLayoutId;
    private TextInputLayout mTextLayoutPwd;
    private TextInputLayout mTextLayoutCheckPwd;
    private TextInputLayout mTextLayoutEmail;
    private TextInputLayout mTextLayoutPhoneNumber;
    private TextInputLayout mTextLayoutAuthCode;
    private TextInputLayout mTextLayoutBirthdate;
    private CheckBox mChkBoxFemale;
    private CheckBox mChkBoxMale;
    private CheckBox mChkBoxTermsOfUse;
    private CheckBox mChkBoxPersonalInfo;
    private CheckBox mChkBoxMarketingInfo;
    private Button mBtnCheckIdDuplication;
    private Button mBtnSendAuthCode;
    private Button mBtnCheckAuthCode;
    private Button mBtnFinish;
    private TextView mTextViewError;
    private Bundle mBundleData;
    private boolean mIsIdValid;
    private boolean mIsValidPhoneNumber;

    public CustomerRegisterFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new CustomerRegisterPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();

        mValidChecker = new ValidChecker(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_register, container, false);

        initView(view);
        initListener();

        // TODO: 두개 다 테스트용으로 true로 선언, 제대로 된 기능 구현 후 false로 만들고 테스트
        mIsIdValid = false;
        mIsValidPhoneNumber = true;

        // TODO: bundle을 Presenter로 넘겨 naver와 kakao 또는 그 외를 판단하여 뷰를 바꾸는 메서드 호출하는 구조로 변경
        mBundleData = getArguments();
        String platform = mBundleData.getString("platform");

        if (platform.equals("naver")) {

            // 이미 있는 정보들을 입력받지 않기 위해 없앰
            mTextLayoutId.setVisibility(View.GONE);
            mTextLayoutPwd.setVisibility(View.GONE);
            mTextLayoutCheckPwd.setVisibility(View.GONE);
            mTextLayoutEmail.setVisibility(View.GONE);
            mTextLayoutPhoneNumber.setVisibility(View.GONE);
            mTextLayoutAuthCode.setVisibility(View.GONE);
            mTextLayoutBirthdate.setVisibility(View.GONE);
            mChkBoxFemale.setVisibility(View.GONE);
            mChkBoxMale.setVisibility(View.GONE);
            mBtnCheckIdDuplication.setVisibility(View.GONE);
            mBtnSendAuthCode.setVisibility(View.GONE);
            mBtnCheckAuthCode.setVisibility(View.GONE);
        } else if (platform.equals("kakao")) {

            mTextLayoutId.setVisibility(View.GONE);
            mTextLayoutPwd.setVisibility(View.GONE);
            mTextLayoutCheckPwd.setVisibility(View.GONE);
            mBtnCheckIdDuplication.setVisibility(View.GONE);
        }

        // 인증번호 관련 뷰는 없애뒀다가 인증번호 전송 버튼 눌렀을 때 나오도록 설정
        mTextLayoutAuthCode.setVisibility(View.GONE);
        mBtnCheckAuthCode.setVisibility(View.GONE);

        // 에러 관련 텍스트 뷰는 에러 출력 시에만 VISIBLE로 사용
        mTextViewError.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onDestroy() {

        mBundleData = null;
        mValidChecker.releaseActivity();
        mValidChecker = null;
        mPresenter.releaseInteractor();
        mPresenter.releaseView();
        mPresenter = null;

        super.onDestroy();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        if (viewId == R.id.btnCRegitCheckIdDuplication) {

            mValidChecker.checkIdValid(mEditTextId, this);
        } else if (viewId == R.id.btnCRegitSendAuthorizationCode) {

            mTextLayoutAuthCode.setVisibility(View.VISIBLE);
            mBtnCheckAuthCode.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.btnCRegitCheckAuthorizationCode) {

            // TODO: Presenter에 인증번호를 보내 확인, Presenter에선 Interactor에 보냄, Interactor에선 api로 보내 확인후 listener를 통해 확인, 확인됐으면 안보이게 설정
            mTextLayoutAuthCode.setVisibility(View.GONE);
            mBtnCheckAuthCode.setVisibility(View.GONE);
        } else if (viewId == R.id.btnCRegitFinish) {

            mTextViewError.setVisibility(View.GONE);

            doRegister(mBundleData.getString("platform"));
        } else if (viewId == R.id.chBoxCRegitFemale) {

            mChkBoxMale.setChecked(false);
        } else if (viewId == R.id.chBoxCRegitMale) {

            mChkBoxFemale.setChecked(false);
        }
    }

    @Override
    public void finishRegister(Intent intent) {

        startActivity(intent);
    }

    private void initView(View view) {
        mEditTextId = view.findViewById(R.id.editTextCRegitId);
        mEditTextPwd = view.findViewById(R.id.editTextCRegitPw);
        mEditTextCheckPwd = view.findViewById(R.id.editTextCRegitCheckPw);
        mEditTextEmail = view.findViewById(R.id.editTextCRegitEmail);
        mEditTextPhoneNumber = view.findViewById(R.id.editTextCRegitPhoneNumber);
        mEditTextAuthCode = view.findViewById(R.id.editTextCRegitAuthorizationCode);
        mEditTextBirthdate = view.findViewById(R.id.editTextCRegitBirthdate);

        mTextLayoutId = view.findViewById(R.id.textLayoutCRegitId);
        mTextLayoutPwd = view.findViewById(R.id.textLayoutCRegitPw);
        mTextLayoutCheckPwd = view.findViewById(R.id.textLayoutCRegitCheckPw);
        mTextLayoutEmail = view.findViewById(R.id.textLayoutCRegitEmail);
        mTextLayoutPhoneNumber = view.findViewById(R.id.textLayoutCRegitPhoneNumber);
        mTextLayoutAuthCode = view.findViewById(R.id.textLayoutCRegitAuthorizationCode);
        mTextLayoutBirthdate = view.findViewById(R.id.textLayoutCRegitBirthdate);

        mChkBoxFemale = view.findViewById(R.id.chBoxCRegitFemale);
        mChkBoxMale = view.findViewById(R.id.chBoxCRegitMale);
        mChkBoxTermsOfUse = view.findViewById(R.id.chBoxCRegitTermsOfUse);
        mChkBoxPersonalInfo = view.findViewById(R.id.chBoxCRegitPersonalInfo);
        mChkBoxMarketingInfo = view.findViewById(R.id.chBoxCRegitMarketingInfo);

        mBtnCheckIdDuplication = view.findViewById(R.id.btnCRegitCheckIdDuplication);
        mBtnSendAuthCode = view.findViewById(R.id.btnCRegitSendAuthorizationCode);
        mBtnCheckAuthCode = view.findViewById(R.id.btnCRegitCheckAuthorizationCode);
        mBtnFinish = view.findViewById(R.id.btnCRegitFinish);

        mTextViewError = view.findViewById(R.id.labelCRegitError);
    }

    private void initListener() {

        mEditTextId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                mIsIdValid = false;
                mBtnCheckIdDuplication.setBackgroundColor(Color.BLACK);
                mBtnCheckIdDuplication.setText("중복 확인");
                mBtnCheckIdDuplication.setEnabled(true);
                mTextLayoutId.setEndIconMode(TextInputLayout.END_ICON_NONE);
            }
        });

        mEditTextPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                mIsValidPhoneNumber = false;
            }
        });

        mChkBoxFemale.setOnClickListener(this);
        mChkBoxMale.setOnClickListener(this);
        mBtnCheckIdDuplication.setOnClickListener(this);
        mBtnSendAuthCode.setOnClickListener(this);
        mBtnCheckAuthCode.setOnClickListener(this);
        mBtnFinish.setOnClickListener(this);
    }

    private void doRegister(String platform) {

        // TODO: 비즈니스 로직이므로 presenter로 옮겨야 함?
        if (checkValidAndPutData(platform)) {

            mPresenter.register(
                    getContext(),
                    platform,
                    mBundleData.getString("id"),
                    mBundleData.getString("pwd"),
                    mBundleData.getString("birthdate"),
                    mBundleData.getString("gender"),
                    mBundleData.getString("email"),
                    mBundleData.getString("phoneNumber"),
                    mChkBoxMarketingInfo.isChecked()
            );
        }
    }

    private boolean checkValidAndPutData(String platform) {

        if (platform.equals("naver")) {

            if (mValidChecker.isNeededCheck(mChkBoxTermsOfUse, mChkBoxPersonalInfo, mTextViewError)) {

                mBundleData.putString("pwd", "naver");
                mBundleData.putBoolean("marketingAgreement", mChkBoxMarketingInfo.isChecked());

                return true;
            }

            return false;
        } else if (platform.equals("kakao")) {

            if (mValidChecker.isEmailValid(mEditTextEmail)
                    && mValidChecker.isPhoneNumberValid(mEditTextPhoneNumber)
                    && mValidChecker.isBirthdateValid(mEditTextBirthdate)
                    && mValidChecker.isGenderValid(mChkBoxMale, mChkBoxFemale, mTextViewError)
                    && mValidChecker.isNeededCheck(mChkBoxTermsOfUse, mChkBoxPersonalInfo, mTextViewError)) {

                mBundleData.putString("pwd", "kakao");
                mBundleData.putString("birthdate", editTextToString(mEditTextBirthdate));
                mBundleData.putString("gender", discriminateGender());
                mBundleData.putString("email", editTextToString(mEditTextEmail));
                mBundleData.putString("phoneNumber", mValidChecker.phoneNumberToInternationalNumber(editTextToString(mEditTextPhoneNumber)));
                mBundleData.putBoolean("marketingAgreement", mChkBoxMarketingInfo.isChecked());

                return true;
            }

            return false;
        } else {

            if (mIsIdValid) {

                mTextViewError.setVisibility(View.VISIBLE);
                mTextViewError.setText("중복확인을 해주세요.");

                return false;
            }

            if ( mValidChecker.isPwdValid(mEditTextPwd, mEditTextCheckPwd)
                    && mValidChecker.isEmailValid(mEditTextEmail)
                    && mValidChecker.isPhoneNumberValid(mEditTextPhoneNumber)
                    && mValidChecker.isBirthdateValid(mEditTextBirthdate)
                    && mValidChecker.isGenderValid(mChkBoxMale, mChkBoxFemale, mTextViewError)
                    && mValidChecker.isNeededCheck(mChkBoxTermsOfUse, mChkBoxPersonalInfo, mTextViewError)) {

                mBundleData.putString("id", editTextToString(mEditTextId));
                mBundleData.putString("pwd", editTextToString(mEditTextPwd));
                mBundleData.putString("birthdate", editTextToString(mEditTextBirthdate));
                mBundleData.putString("gender", discriminateGender());
                mBundleData.putString("email", editTextToString(mEditTextEmail));
                mBundleData.putString("phoneNumber", mValidChecker.phoneNumberToInternationalNumber(editTextToString(mEditTextPhoneNumber)));
                mBundleData.putBoolean("marketingAgreement", mChkBoxMarketingInfo.isChecked());

                return true;
            }

            return false;
        }
    }

    private String editTextToString(EditText editText) {

        return editText.getText().toString();
    }

    private String discriminateGender() {

        if (mChkBoxMale.isChecked()) {

            return "M";
        } else {

            return "F";
        }
    }

    @Override
    public void onSuccess(IdDuplicate.statusRes res) {

        int status = res.getStatus();
        if (status == UserRestApi.SUCCESS) {

            mIsIdValid = false;

            mBtnCheckIdDuplication.setEnabled(false);
            mBtnCheckIdDuplication.setText("확인 완료");
            mBtnCheckIdDuplication.setBackgroundColor(getResources().getColor(R.color.light_green));
        } else if (status == UserRestApi.ID_DUPLICATE) {

            mEditTextId.setError("중복된 아이디가 존재합니다.");
        }
    }

    @Override
    public void onFailure(Throwable t) {

        Toast.makeText(getActivity(), "에러 발생", Toast.LENGTH_LONG).show();
    }
}