package seoil.capstone.som.ui.register.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputType;
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
import seoil.capstone.som.data.network.api.UserApi;
import seoil.capstone.som.data.network.model.IdDuplicate;
import seoil.capstone.som.ui.address.SearchAddressActivity;
import seoil.capstone.som.ui.register.RegisterCommunicator;
import seoil.capstone.som.util.Utility;

public class ManagerRegisterFragment extends Fragment implements ManagerRegisterContract.View, View.OnClickListener, OnFinishApiListener<IdDuplicate.statusRes> {

    private ManagerRegisterPresenter mPresenter;
    private RegisterCommunicator.Communicator mCommunicator;
    private OnBackPressedCallback mBackPressedCallback;
    private TextInputEditText mEditTextId;
    private TextInputEditText mEditTextPwd;
    private TextInputEditText mEditTextCheckPwd;
    private TextInputEditText mEditTextEmail;
    private TextInputEditText mEditTextCorporateNumber;
    private TextInputEditText mEditTextPhoneNumber;
    private TextInputEditText mEditTextBirthdate;
    private TextInputLayout mTextLayoutId;
    private TextInputLayout mTextLayoutPwd;
    private TextInputLayout mTextLayoutCheckPwd;
    private TextInputLayout mTextLayoutEmail;
    private TextInputLayout mTextLayoutPhoneNumber;
    private TextInputLayout mTextLayoutAuthCode;
    private TextInputLayout mTextLayoutBirthdate;
    private TextInputLayout mTextLayoutCorporateNumber;
    private CheckBox mChkBoxFemale;
    private CheckBox mChkBoxMale;
    private CheckBox mChkBoxTermsOfUse;
    private CheckBox mChkBoxPersonalInfo;
    private CheckBox mChkBoxMarketingInfo;
    private Button mBtnCheckCorporateNumber;
    private Button mBtnFinish;
    private Button mBtnPostNumber;
    private Button mBtnSendAuthCode;
    private Button mBtnCheckAuthCode;
    private Button mBtnCheckIdDuplication;
    private TextView mTextViewError;
    private TextView mTextViewPostalCode;
    private TextView mTextViewAddress;
    private Bundle mBundleData;
    private boolean mIsIdValid;
    private boolean mIsValidPhoneNumber;
    private int mIsValidCorporateNumber;

    public ManagerRegisterFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);

        if (context instanceof RegisterCommunicator.Communicator) {

            mCommunicator = (RegisterCommunicator.Communicator) context;
        } else {

            throw new RuntimeException(context.toString() + " not implement Communicator");
        }

        // 뒤로가기 버튼 콜백 함수
//        mBackPressedCallback = new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//
//                // 들고있던 정보 가지고 되돌아감
//                // mCommunicator.changeAnotherFragment(new SelectUserFragment(), getArguments());
//            }
//        };
//
//        // 뒤로가기 버튼에 콜백 함수 등록
//        requireActivity().getOnBackPressedDispatcher().addCallback(mBackPressedCallback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mPresenter = new ManagerRegisterPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manager_register, container, false);

        initView(view);
        initListener();

        Bundle bundle = getArguments();
        if (bundle.getString("platform").equals("naver")) {


        }
// TODO: 두개 다 테스트용으로 true로 선언, 제대로 된 기능 구현 후 false로 만들고 테스트
        mIsIdValid = false;
        mIsValidPhoneNumber = true;
        mIsValidCorporateNumber = mPresenter.CORPORATE_NUMBER_NOT_VALID;

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


        // 에러 관련 텍스트 뷰는 에러 출력 시에만 VISIBLE로 사용
        mTextViewError.setVisibility(View.GONE);


        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {

            if (resultCode == 2) {

                mTextViewPostalCode.setText(data.getStringExtra("PostalCode"));

                mTextViewAddress.setText(data.getStringExtra("Address") + " " + data.getStringExtra("BuildingName"));
            }
        }
    }

    @Override
    public void onDestroy() {

        mBundleData = null;
        mPresenter.releaseInteractor();
        mPresenter.releaseView();
        mPresenter = null;

        super.onDestroy();
    }

    @Override
    public void onDetach() {

        //mBackPressedCallback.remove();

        super.onDetach();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnMRegitFindAddress) {

            Intent intent = new Intent(getActivity(), SearchAddressActivity.class);
            this.startActivityForResult(intent, 1);
        }

        if (v.getId() == R.id.btnMRegitFinish) {

            mTextViewError.setVisibility(View.GONE);

            doRegister(mBundleData.getString("platform"));
        }

        if (v.getId() == R.id.btnMRegitCheckIdDuplication) {

            mIsIdValid = mPresenter.checkIdValid(mEditTextId.getText().toString(), this);
        }

        if (v.getId() == R.id.btnMRegitCheckCorporateRegistrationNumber) {

            mIsValidCorporateNumber = mPresenter.checkCorporateNumber(mEditTextCorporateNumber.getText().toString());

            if (mIsValidCorporateNumber == mPresenter.CORPORATE_NUMBER_VALID) {

                mBtnCheckCorporateNumber.setEnabled(false);
                mBtnCheckCorporateNumber.setText("확인 완료");
                mBtnCheckCorporateNumber.setBackgroundColor(getResources().getColor(R.color.light_green));
            } else if (mIsValidCorporateNumber == mPresenter.CORPORATE_NUMBER_NOT_NUM) {

                Utility.getInstance().renderKeyboard(getActivity());
                mEditTextCorporateNumber.setError("숫자만 입력해주세요 (ex : 123456789)");
                mEditTextCorporateNumber.requestFocus();
            } else if (mIsValidCorporateNumber == mPresenter.CORPORATE_NUMBER_LENGTH_ERROR) {

                Utility.getInstance().renderKeyboard(getActivity());
                mEditTextCorporateNumber.setError("10자리를 입력해주세요 (ex : 123456789)");
                mEditTextCorporateNumber.requestFocus();
            } else if (mIsValidCorporateNumber == mPresenter.CORPORATE_NUMBER_NOT_VALID) {

                Utility.getInstance().renderKeyboard(getActivity());
                mEditTextCorporateNumber.setError("유효하지 않은 번호 입니다.");
                mEditTextCorporateNumber.requestFocus();
            }
        }
    }

    private void initView(View view) {

        mTextLayoutId = view.findViewById(R.id.textLayoutMRegitId);
        mTextLayoutPwd = view.findViewById(R.id.textLayoutMRegitPw);
        mTextLayoutCheckPwd = view.findViewById(R.id.textLayoutMRegitCheckPw);
        mTextLayoutEmail = view.findViewById(R.id.textLayoutMRegitEmail);
        mTextLayoutPhoneNumber = view.findViewById(R.id.textLayoutMRegitPhoneNumber);
        mTextLayoutAuthCode = view.findViewById(R.id.textLayoutMRegitAuthorizationCode);
        mTextLayoutBirthdate = view.findViewById(R.id.textLayoutMRegitBirthdate);
        mTextLayoutCorporateNumber = view.findViewById(R.id.textLayoutMRegitCorporateRegistrationNumber);

        mEditTextId = view.findViewById(R.id.editTextMRegitId);
        mEditTextPwd = view.findViewById(R.id.editTextMRegitPw);
        mEditTextCheckPwd = view.findViewById(R.id.editTextMRegitCheckPw);
        mEditTextEmail = view.findViewById(R.id.editTextMRegitEmail);
        mEditTextCorporateNumber = view.findViewById(R.id.editTextMRegitCorporateNumber);
        mEditTextPhoneNumber = view.findViewById(R.id.editTextMRegitPhoneNumber);
        mEditTextBirthdate = view.findViewById(R.id.editTextMRegitBirthdate);

        mChkBoxFemale = view.findViewById(R.id.chBoxMRegitFemale);
        mChkBoxMale = view.findViewById(R.id.chBoxMRegitMale);
        mChkBoxPersonalInfo = view.findViewById(R.id.chBoxMRegitPersonalInfo);
        mChkBoxMarketingInfo = view.findViewById(R.id.chBoxMRegitMarketingInfo);
        mChkBoxTermsOfUse = view.findViewById(R.id.chBoxMRegitTermsOfUse);

        mBtnFinish = view.findViewById(R.id.btnMRegitFinish);
        mBtnPostNumber = view.findViewById(R.id.btnMRegitFindAddress);
        mBtnSendAuthCode = view.findViewById(R.id.btnMRegitSendAuthorizationCode);
        mBtnCheckAuthCode = view.findViewById(R.id.btnMRegitCheckAuthorizationCode);
        mBtnCheckIdDuplication = view.findViewById(R.id.btnMRegitCheckIdDuplication);
        mBtnCheckCorporateNumber = view.findViewById(R.id.btnMRegitCheckCorporateRegistrationNumber);

        mTextViewError = view.findViewById(R.id.labelMRegitError);
        mTextViewPostalCode = view.findViewById(R.id.editTextMRegitPostalCode);
        mTextViewAddress = view.findViewById(R.id.editTextMRegitAddress);
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

        mEditTextCorporateNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                mIsValidCorporateNumber = mPresenter.CORPORATE_NUMBER_NOT_VALID;
                mBtnCheckCorporateNumber.setBackgroundColor(Color.BLACK);
                mBtnCheckCorporateNumber.setText("확인");
                mBtnCheckCorporateNumber.setEnabled(true);
                mTextLayoutCorporateNumber.setEndIconMode(TextInputLayout.END_ICON_NONE);
            }
        });

        mBtnPostNumber.setOnClickListener(this);
        mBtnFinish.setOnClickListener(this);
        mBtnCheckIdDuplication.setOnClickListener(this);
        mBtnCheckCorporateNumber.setOnClickListener(this);
    }

    private boolean checkValidAndPutData(String platform) {

        int emailCode, phoneNumberCode, birthDateCode, neededCheckCode, idCode, pwCode, genderCode, corporateCode;

        neededCheckCode = mPresenter.neededCheck(mChkBoxTermsOfUse.isChecked(), mChkBoxPersonalInfo.isChecked());


        if (platform.equals("naver")) {

            if (neededCheckCode != mPresenter.NEEDED_VALID) {

                mBundleData.putString("pwd", "naver");
                mBundleData.putBoolean("marketingAgreement", mChkBoxMarketingInfo.isChecked());

                return true;
            } else if (neededCheckCode == mPresenter.TERMS_OF_USE_NEEDED) {

                mTextViewError.setVisibility(View.VISIBLE);
                mTextViewError.setText("이용약관 동의가 필요합니다.");
            } else if (neededCheckCode == mPresenter.PERSONAL_INFO_NEEDED) {

                mTextViewError.setVisibility(View.VISIBLE);
                mTextViewError.setText("개인정보 취급방침 동의가 필요합니다.");
            }

            return false;
        } else if (platform.equals("kakao")) {

            emailCode = mPresenter.emailValid(mEditTextEmail.getText().toString());
            phoneNumberCode = mPresenter.phoneNumberValid(mEditTextPhoneNumber.getText().toString());
            birthDateCode = mPresenter.birthdateValid(mEditTextBirthdate.getText().toString());

            if(emailCode != mPresenter.EMAIL_VALID) {

                if (emailCode == mPresenter.EMAIL_EMPTY) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextEmail.setError("이메일을 입력해주세요.");
                    mEditTextEmail.requestFocus();
                } else if (emailCode == mPresenter.EMAIL_NOT_VALID) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextEmail.setError("올바른 이메일을 입력해주세요.");
                    mEditTextEmail.requestFocus();
                }
            } else if (phoneNumberCode != mPresenter.PHONE_VALID) {

                if (phoneNumberCode == mPresenter.PHONE_EMPTY) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextPhoneNumber.setError("핸드폰 번호를 입력해주세요.");
                    mEditTextPhoneNumber.requestFocus();
                } else if (phoneNumberCode == mPresenter.PHONE_ERROR_OTHER_CHAR) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextPhoneNumber.setError("숫자만 입력해주세요. (ex : 19500101)");
                    mEditTextPhoneNumber.requestFocus();
                } else if (phoneNumberCode == mPresenter.PHONE_LENGTH_ERROR) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextPhoneNumber.setError("핸드폰 번호가 맞는지 확인해주세요.(010xxxxxxxx)");
                    mEditTextPhoneNumber.requestFocus();
                }
            } else if (birthDateCode != mPresenter.BIRTH_VALID) {

                if (birthDateCode == mPresenter.BIRTH_EMPTY) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextBirthdate.setError("생년월일을 입력해주세요.");
                    mEditTextBirthdate.requestFocus();
                } else if (birthDateCode == mPresenter.BIRTH_ERROR_LENTGTH) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextBirthdate.setError("8자리를 입력해주세요. (ex : 19500101)");
                    mEditTextBirthdate.requestFocus();
                } else if (birthDateCode == mPresenter.BIRTH_ERROR_OTHER_CHAR) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextBirthdate.setError("숫자만 입력해주세요. (ex : 19500101)");
                    mEditTextBirthdate.requestFocus();
                }
            } else if (neededCheckCode != mPresenter.NEEDED_VALID) {

                if (neededCheckCode == mPresenter.TERMS_OF_USE_NEEDED) {

                    mTextViewError.setVisibility(View.VISIBLE);
                    mTextViewError.setText("이용약관 동의가 필요합니다.");
                } else if (neededCheckCode == mPresenter.PERSONAL_INFO_NEEDED) {

                    mTextViewError.setVisibility(View.VISIBLE);
                    mTextViewError.setText("개인정보 취급방침 동의가 필요합니다.");
                }
            } else {

                mBundleData.putString("pwd", "kakao");
                mBundleData.putString("birthdate", editTextToString(mEditTextBirthdate));
                mBundleData.putString("gender", discriminateGender());
                mBundleData.putString("email", editTextToString(mEditTextEmail));
                mBundleData.putString("phoneNumber", mPresenter.phoneNumberToInternationalNumber(editTextToString(mEditTextPhoneNumber)));
                mBundleData.putBoolean("marketingAgreement", mChkBoxMarketingInfo.isChecked());

                return true;
            }

            return false;
        } else {

            idCode = mPresenter.idValid(mEditTextId.getText().toString());
            pwCode = mPresenter.pwdValid(mEditTextPwd.getText().toString(), mEditTextCheckPwd.getText().toString());
            emailCode = mPresenter.emailValid(mEditTextEmail.getText().toString());
            phoneNumberCode = mPresenter.phoneNumberValid(mEditTextPhoneNumber.getText().toString());
            genderCode = mPresenter.genderValid(mChkBoxMale.isChecked(), mChkBoxFemale.isChecked());
            birthDateCode = mPresenter.birthdateValid(mEditTextBirthdate.getText().toString());

            if (mIsIdValid) {

                mTextViewError.setVisibility(View.VISIBLE);
                mTextViewError.setText("중복확인을 해주세요.");

                return false;
            }
            if (idCode != mPresenter.ID_VALID) {

                if(idCode == mPresenter.ID_EMPTY) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextId.setError("아이디를 입력해주세요.");
                    mEditTextId.requestFocus();
                } else if (idCode == mPresenter.ID_SHORT) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextId.setError("아이디가 너무 짧습니다. 3자 이상 입력해주세요.");
                    mEditTextId.requestFocus();
                } else if (idCode == mPresenter.ID_LONG) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextId.setError("아이디가 너무 깁니다. 20자 이하로 입력해주세요.");
                    mEditTextId.requestFocus();
                }
            } else if (pwCode != mPresenter.PWD_VALID) {

                if (pwCode == mPresenter.PWD_EMPTY) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextPwd.setError("비밀번호를 입력해주세요.");
                    mEditTextPwd.requestFocus();
                } else if (pwCode == mPresenter.PWD_CHECK_EMPTY) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextCheckPwd.setError("비밀번호를 입력해주세요.");
                    mEditTextCheckPwd.requestFocus();
                } else if (pwCode == mPresenter.PWD_CHECK_NOT_EQUAL) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextCheckPwd.setError("비밀번호가 서로 다릅니다.");
                    mEditTextCheckPwd.requestFocus();
                }
            }else if(emailCode != mPresenter.EMAIL_VALID) {

                if (emailCode == mPresenter.EMAIL_EMPTY) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextEmail.setError("이메일을 입력해주세요.");
                    mEditTextEmail.requestFocus();
                } else if (emailCode == mPresenter.EMAIL_NOT_VALID) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextEmail.setError("올바른 이메일을 입력해주세요.");
                    mEditTextEmail.requestFocus();
                }
            } else if (phoneNumberCode != mPresenter.PHONE_VALID) {

                if (phoneNumberCode == mPresenter.PHONE_EMPTY) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextPhoneNumber.setError("핸드폰 번호를 입력해주세요.");
                    mEditTextPhoneNumber.requestFocus();
                } else if (phoneNumberCode == mPresenter.PHONE_ERROR_OTHER_CHAR) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextPhoneNumber.setError("숫자만 입력해주세요. (ex : 19500101)");
                    mEditTextPhoneNumber.requestFocus();
                } else if (phoneNumberCode == mPresenter.PHONE_LENGTH_ERROR) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextPhoneNumber.setError("핸드폰 번호가 맞는지 확인해주세요.(010xxxxxxxx)");
                    mEditTextPhoneNumber.requestFocus();
                }
            } else if (mIsValidCorporateNumber != mPresenter.CORPORATE_NUMBER_VALID) {

                if (mIsValidCorporateNumber == mPresenter.CORPORATE_NUMBER_NOT_NUM) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextCorporateNumber.setError("숫자만 입력해주세요 (ex : 123456789)");
                    mEditTextCorporateNumber.requestFocus();
                } else if (mIsValidCorporateNumber == mPresenter.CORPORATE_NUMBER_LENGTH_ERROR) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextCorporateNumber.setError("10자리를 입력해주세요 (ex : 123456789)");
                    mEditTextCorporateNumber.requestFocus();
                } else if (mIsValidCorporateNumber == mPresenter.CORPORATE_NUMBER_NOT_VALID) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextCorporateNumber.setError("유효하지 않은 번호 입니다.");
                    mEditTextCorporateNumber.requestFocus();
                }

            } else if (birthDateCode != mPresenter.BIRTH_VALID) {

                if (birthDateCode == mPresenter.BIRTH_EMPTY) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextBirthdate.setError("생년월일을 입력해주세요.");
                    mEditTextBirthdate.requestFocus();
                } else if (birthDateCode == mPresenter.BIRTH_ERROR_LENTGTH) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextBirthdate.setError("8자리를 입력해주세요. (ex : 19500101)");
                    mEditTextBirthdate.requestFocus();
                } else if (birthDateCode == mPresenter.BIRTH_ERROR_OTHER_CHAR) {

                    Utility.getInstance().renderKeyboard(getActivity());
                    mEditTextBirthdate.setError("숫자만 입력해주세요. (ex : 19500101)");
                    mEditTextBirthdate.requestFocus();
                }
            } else if (genderCode != mPresenter.GENDER_VALID) {

                mTextViewError.setVisibility(View.VISIBLE);
                mTextViewError.setText("성별을 선택해주세요.");
            } else if (neededCheckCode != mPresenter.NEEDED_VALID) {

                if (neededCheckCode == mPresenter.TERMS_OF_USE_NEEDED) {

                    mTextViewError.setVisibility(View.VISIBLE);
                    mTextViewError.setText("이용약관 동의가 필요합니다.");
                } else if (neededCheckCode == mPresenter.PERSONAL_INFO_NEEDED) {

                    mTextViewError.setVisibility(View.VISIBLE);
                    mTextViewError.setText("개인정보 취급방침 동의가 필요합니다.");
                }
            }

            if (mIsIdValid && idCode == mPresenter.ID_VALID && pwCode == mPresenter.PWD_VALID &&
                emailCode == mPresenter.EMAIL_VALID && genderCode == mPresenter.GENDER_VALID &&
                phoneNumberCode == mPresenter.PHONE_VALID && neededCheckCode == mPresenter.NEEDED_VALID &&
                birthDateCode == mPresenter.BIRTH_VALID) {

                mBundleData.putString("id", editTextToString(mEditTextId));
                mBundleData.putString("pwd", editTextToString(mEditTextPwd));
                mBundleData.putString("birthdate", editTextToString(mEditTextBirthdate));
                mBundleData.putString("gender", discriminateGender());
                mBundleData.putString("email", editTextToString(mEditTextEmail));
                mBundleData.putString("phoneNumber", mPresenter.phoneNumberToInternationalNumber(editTextToString(mEditTextPhoneNumber)));
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
    public void finishRegister(Intent intent) {

        startActivity(intent);
    }


    @Override
    public void onFailure(Throwable t) {

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

    @Override
    public void onSuccess(IdDuplicate.statusRes res) {

        int status = res.getStatus();
        if (status == UserApi.SUCCESS) {

            mIsIdValid = false;

            mBtnCheckIdDuplication.setEnabled(false);
            mBtnCheckIdDuplication.setText("확인 완료");
            mBtnCheckIdDuplication.setBackgroundColor(getResources().getColor(R.color.light_green));
        } else if (status == UserApi.ID_DUPLICATE) {

            mEditTextId.setError("중복된 아이디가 존재합니다.");
        }
    }
}