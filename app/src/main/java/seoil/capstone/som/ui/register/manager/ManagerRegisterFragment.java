package seoil.capstone.som.ui.register.manager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.R;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.UserApi;
import seoil.capstone.som.data.network.model.AuthDTO;
import seoil.capstone.som.ui.address.SearchAddressActivity;
import seoil.capstone.som.ui.register.RegisterCommunicator;
import seoil.capstone.som.ui.register.select.SelectUserFragment;
import seoil.capstone.som.util.Utility;

// 점주 회원가입 프레그먼트
public class ManagerRegisterFragment extends Fragment implements ManagerRegisterContract.View, View.OnClickListener, OnFinishApiListener<AuthDTO.StatusRes> {

    private RegisterCommunicator.Communicator mCommunicator;
    private OnBackPressedCallback mOnBackPressedCallback;
    private ManagerRegisterPresenter mPresenter;
    private TextInputEditText mEditTextId;
    private TextInputEditText mEditTextPwd;
    private TextInputEditText mEditTextCheckPwd;
    private TextInputEditText mEditTextEmail;
    private TextInputEditText mEditTextCorporateNumber;
    private TextInputEditText mEditTextPhoneNumber;
    private TextInputEditText mEditTextAuthCode;
    private TextInputEditText mEditTextBirthdate;
    private TextInputEditText mEditTextMarketName;
    private TextInputEditText mEditTextDetailedAddress;
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
    private TextView mTextViewPostalCode;
    private TextView mTextViewAddress;
    private Bundle mBundleData;
    private Spinner mSpinnerCategory;
    private Dialog mDialog;
    private boolean mIsIdValid;
    private boolean mIsValidPhoneNumber;
    private boolean mIsCategorySelected;
    private long mLastTimeBackPressed;
    private int mIsValidCorporateNumber;
    private int mIdValidCode;

    // 뒤로가기 버튼 콜백 생성 및 등록, 중계자 생성
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof RegisterCommunicator.Communicator) {

            mCommunicator = (RegisterCommunicator.Communicator) context;
        } else {

            throw new RuntimeException(context.toString() + " not implement Communicator");
        }

        // 뒤로가기 버튼 콜백 함수
        mOnBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                if(System.currentTimeMillis() - mLastTimeBackPressed < 1000) {

                    // 들고있던 정보 가지고 되돌아감
                    mCommunicator.changeAnotherFragment(new SelectUserFragment(), getArguments());

                    return;
                }

                mLastTimeBackPressed = System.currentTimeMillis();
                Toast.makeText(getActivity(),"'뒤로' 버튼을 한 번 더 누르면 회원 선택 창으로 돌아갑니다.", Toast.LENGTH_SHORT).show();
            }
        };

        // 뒤로가기 버튼에 콜백 함수 등록
        requireActivity().getOnBackPressedDispatcher().addCallback(mOnBackPressedCallback);
    }

    // 뒤로가기 버튼 콜백, 중계자 삭제
    @Override
    public void onDetach() {

        mOnBackPressedCallback.remove();
        mCommunicator = null;

        super.onDetach();
    }

    // 프레젠터 생성
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mBundleData = new Bundle();
        mPresenter = new ManagerRegisterPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();
    }

    // 프레젠터 삭제
    @Override
    public void onDestroy() {

        mBundleData = null;
        mPresenter.releaseInteractor();
        mPresenter.releaseView();
        mPresenter = null;

        super.onDestroy();
    }

    // UI, 리스너 초기화
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manager_register, container, false);

        initView(view);
        initListener();

        mIsIdValid = false;
        mIsValidPhoneNumber = false;
        mIsValidCorporateNumber = mPresenter.CORPORATE_NUMBER_NOT_VALID;
        mIdValidCode = mPresenter.ID_EMPTY;

        mBundleData = getArguments();
        String platform = mBundleData.getString("platform");

        // 네이버나 카톡 간편로그인을 통해 이미 있는 정보들은 입력받지 않기 위해 없앰
        if (platform.equals("naver")) {

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

        return view;
    }

    // 다음 주소 api를 이용한 액티비티에서 주소를 받아옴
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
    public void showProgress() {

        Utility.getInstance().activateProgressAnim(getContext(), getView().findViewById(R.id.fragmentManagerRegisterLayout));
    }

    @Override
    public void hideProgress() {

        if(mPresenter.getIsValid()) {

            mBtnCheckCorporateNumber.setEnabled(false);
            mBtnCheckCorporateNumber.setText("확인 완료");
            mBtnCheckCorporateNumber.setBackgroundColor(getResources().getColor(R.color.light_green));
        }

        Utility.getInstance().deactivateProgressAnim();
    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        if (viewId == R.id.btnMRegitFindAddress) {

            mBtnPostNumber.setError(null);
            Intent intent = new Intent(getActivity(), SearchAddressActivity.class);
            this.startActivityForResult(intent, 1);
        }

        if (viewId == R.id.btnMRegitFinish) {

            doRegister(mBundleData.getString("platform"));
        }

        if (viewId == R.id.btnMRegitCheckIdDuplication) {

            mIdValidCode = mPresenter.idValid(mEditTextId.getText().toString());
            if(mIdValidCode == mPresenter.ID_VALID) {

                mIsIdValid = mPresenter.checkIdValid(mEditTextId.getText().toString(), this);
            } else if(mIdValidCode == mPresenter.ID_EMPTY) {

                Utility.getInstance().activateKeyboard(getActivity());
                mEditTextId.setError("아이디를 입력해주세요.");
                mEditTextId.requestFocus();
            } else if (mIdValidCode == mPresenter.ID_SHORT) {

                Utility.getInstance().activateKeyboard(getActivity());
                mEditTextId.setError("아이디가 너무 짧습니다. 8자 이상 입력해주세요.");
                mEditTextId.requestFocus();
            } else if (mIdValidCode == mPresenter.ID_LONG) {

                Utility.getInstance().activateKeyboard(getActivity());
                mEditTextId.setError("아이디가 너무 깁니다. 20자 이하로 입력해주세요.");
                mEditTextId.requestFocus();
            }
        } else if (viewId == R.id.btnMRegitCheckCorporateRegistrationNumber) {

            mIsValidCorporateNumber = mPresenter.checkCorporateNumber(mEditTextCorporateNumber.getText().toString());

            if (mIsValidCorporateNumber == mPresenter.CORPORATE_NUMBER_VALID) {

                showProgress();
                mPresenter.checkCorporateNumberValid(mEditTextCorporateNumber.getText().toString());

            } else if (mIsValidCorporateNumber == mPresenter.CORPORATE_NUMBER_NOT_NUM) {

                Utility.getInstance().activateKeyboard(getActivity());
                mEditTextCorporateNumber.setError("숫자만 입력해주세요 (ex : 123456789)");
                mEditTextCorporateNumber.requestFocus();
            } else if (mIsValidCorporateNumber == mPresenter.CORPORATE_NUMBER_LENGTH_ERROR) {

                Utility.getInstance().activateKeyboard(getActivity());
                mEditTextCorporateNumber.setError("10자리를 입력해주세요 (ex : 123456789)");
                mEditTextCorporateNumber.requestFocus();
            } else if (mIsValidCorporateNumber == mPresenter.CORPORATE_NUMBER_NOT_VALID) {

                Utility.getInstance().activateKeyboard(getActivity());
                mEditTextCorporateNumber.setError("유효하지 않은 번호 입니다.");
                mEditTextCorporateNumber.requestFocus();
            }
        } else if (viewId == R.id.btnMRegitSendAuthorizationCode) {

            int phoneValid = mPresenter.phoneNumberValid(mEditTextPhoneNumber.getText().toString());

            if (phoneValid != mPresenter.PHONE_VALID) {

                if (phoneValid == mPresenter.PHONE_EMPTY) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextPhoneNumber.setError("핸드폰 번호를 입력해주세요.");
                    mEditTextPhoneNumber.requestFocus();
                } else if (phoneValid == mPresenter.PHONE_LENGTH_ERROR) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextPhoneNumber.setError("'-'없이 11자리로 입력해주세요.");
                    mEditTextPhoneNumber.requestFocus();
                } else if (phoneValid == mPresenter.PHONE_ERROR_OTHER_CHAR) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextPhoneNumber.setError("숫자만 입력해주세요.");
                    mEditTextPhoneNumber.requestFocus();
                }
            } else {

                mPresenter.sendSms(mEditTextPhoneNumber.getText().toString());
                mBtnCheckAuthCode.setVisibility(View.VISIBLE);
                mTextLayoutAuthCode.setVisibility(View.VISIBLE);
            }
        } else if (viewId == R.id.btnMRegitCheckAuthorizationCode) {

            mPresenter.sendAuthCode(mEditTextPhoneNumber.getText().toString(), mEditTextAuthCode.getText().toString());
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
        mEditTextAuthCode = view.findViewById(R.id.editTextMRegitAuthorizationCode);
        mEditTextBirthdate = view.findViewById(R.id.editTextMRegitBirthdate);
        mEditTextMarketName = view.findViewById(R.id.editTextMRegitMarketName);
        mEditTextDetailedAddress = view.findViewById(R.id.editTextMRegitDetailedAddress);

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

        mTextViewPostalCode = view.findViewById(R.id.editTextMRegitPostalCode);
        mTextViewAddress = view.findViewById(R.id.editTextMRegitAddress);

        mSpinnerCategory = view.findViewById(R.id.spinnerMRegitCategory);
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

        //Spinner 입력
        @SuppressLint("ResourceType")
        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.spinnerArray, R.layout.spinner_item);
        mSpinnerCategory.setAdapter(spinnerAdapter);
        mSpinnerCategory.setFocusable(true);
        mSpinnerCategory.setFocusableInTouchMode(true);
        mSpinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (mSpinnerCategory.getSelectedItemPosition() > 0) {

                    mIsCategorySelected = true;
                } else {

                    mIsCategorySelected = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                mIsCategorySelected = false;
            }
        });

        mBtnPostNumber.setOnClickListener(this);
        mBtnFinish.setOnClickListener(this);
        mBtnCheckIdDuplication.setOnClickListener(this);
        mBtnCheckCorporateNumber.setOnClickListener(this);
        mBtnSendAuthCode.setOnClickListener(this);
        mBtnCheckAuthCode.setOnClickListener(this);
    }

    private boolean checkValidAndPutData(String platform) {

        int emailCode;
        int phoneNumberCode;
        int birthDateCode;
        int pwCode;
        int genderCode;
        int marketNameCode;
        int addressCode;
        int postalCode;
        int detailedAddressCode;
        int neededCheckCode = mPresenter.neededCheck(mChkBoxTermsOfUse.isChecked(), mChkBoxPersonalInfo.isChecked());

        if (platform.equals("naver")) {

            if (neededCheckCode != mPresenter.NEEDED_VALID) {

                if (neededCheckCode == mPresenter.TERMS_OF_USE_NEEDED) {

                    showDialog("이용약관 동의가 필요합니다.");
                } else if (neededCheckCode == mPresenter.PERSONAL_INFO_NEEDED) {

                    showDialog("개인정보 취급방침 동의가 필요합니다.");
                }
            } else {

                mBundleData.putString("pwd", "naver");
                mBundleData.putString("shopCode", editTextToString(mEditTextCorporateNumber));
                mBundleData.putString("shopName", editTextToString(mEditTextMarketName));
                mBundleData.putString("shopCategory", mSpinnerCategory.getSelectedItem().toString());
                mBundleData.putString("shopPostCode", mTextViewPostalCode.getText().toString());
                mBundleData.putString("shopAddress", mTextViewAddress.getText().toString() + " " + editTextToString(mEditTextDetailedAddress));
                mBundleData.putBoolean("marketingAgreement", mChkBoxMarketingInfo.isChecked());

                return true;
            }

            return false;
        } else if (platform.equals("kakao")) {

            emailCode = mPresenter.emailValid(mEditTextEmail.getText().toString());
            phoneNumberCode = mPresenter.phoneNumberValid(mEditTextPhoneNumber.getText().toString());
            birthDateCode = mPresenter.birthdateValid(mEditTextBirthdate.getText().toString());

            if(emailCode != mPresenter.EMAIL_VALID) {

                if (emailCode == mPresenter.EMAIL_EMPTY) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextEmail.setError("이메일을 입력해주세요.");
                    mEditTextEmail.requestFocus();
                } else if (emailCode == mPresenter.EMAIL_INVALID) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextEmail.setError("올바른 이메일을 입력해주세요.");
                    mEditTextEmail.requestFocus();
                }
            } else if (phoneNumberCode != mPresenter.PHONE_VALID) {

                if (phoneNumberCode == mPresenter.PHONE_EMPTY) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextPhoneNumber.setError("핸드폰 번호를 입력해주세요.");
                    mEditTextPhoneNumber.requestFocus();
                } else if (phoneNumberCode == mPresenter.PHONE_ERROR_OTHER_CHAR) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextPhoneNumber.setError("숫자만 입력해주세요. (ex : 19500101)");
                    mEditTextPhoneNumber.requestFocus();
                } else if (phoneNumberCode == mPresenter.PHONE_LENGTH_ERROR) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextPhoneNumber.setError("핸드폰 번호가 맞는지 확인해주세요.(010xxxxxxxx)");
                    mEditTextPhoneNumber.requestFocus();
                }
            } else if (!mIsValidPhoneNumber) {

                showDialog("핸드폰 인증을 해주세요.");
            } else if (birthDateCode != mPresenter.BIRTH_VALID) {

                if (birthDateCode == mPresenter.BIRTH_EMPTY) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextBirthdate.setError("생년월일을 입력해주세요.");
                    mEditTextBirthdate.requestFocus();
                } else if (birthDateCode == mPresenter.BIRTH_ERROR_LENGTH) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextBirthdate.setError("8자리를 입력해주세요. (ex : 19500101)");
                    mEditTextBirthdate.requestFocus();
                } else if (birthDateCode == mPresenter.BIRTH_ERROR_OTHER_CHAR) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextBirthdate.setError("숫자만 입력해주세요. (ex : 19500101)");
                    mEditTextBirthdate.requestFocus();
                }
            } else if (neededCheckCode != mPresenter.NEEDED_VALID) {

                String msg = "";
                if (neededCheckCode == mPresenter.TERMS_OF_USE_NEEDED) {

                    msg += "이용약관 동의가 필요합니다.\n";
                }

                if (neededCheckCode == mPresenter.PERSONAL_INFO_NEEDED) {

                    msg += "개인정보 취급방침 동의가 필요합니다.\n";
                }

                showDialog(msg);
            } else {

                mBundleData.putString("pwd", "kakao");
                mBundleData.putString("birthdate", editTextToString(mEditTextBirthdate));
                mBundleData.putString("gender", discriminateGender());
                mBundleData.putString("email", editTextToString(mEditTextEmail));
                mBundleData.putString("phoneNumber", editTextToString(mEditTextPhoneNumber));
                mBundleData.putString("shopCode", editTextToString(mEditTextCorporateNumber));
                mBundleData.putString("shopName", editTextToString(mEditTextMarketName));
                mBundleData.putString("shopCategory", mSpinnerCategory.getSelectedItem().toString());
                mBundleData.putString("shopPostCode", mTextViewPostalCode.getText().toString());
                mBundleData.putString("shopAddress", mTextViewAddress.getText().toString() + " " + editTextToString(mEditTextDetailedAddress));
                mBundleData.putBoolean("marketingAgreement", mChkBoxMarketingInfo.isChecked());

                return true;
            }

            return false;
        } else {

            pwCode = mPresenter.pwdValid(mEditTextPwd.getText().toString(), mEditTextCheckPwd.getText().toString());
            emailCode = mPresenter.emailValid(mEditTextEmail.getText().toString());
            phoneNumberCode = mPresenter.phoneNumberValid(mEditTextPhoneNumber.getText().toString());
            genderCode = mPresenter.genderValid(mChkBoxMale.isChecked(), mChkBoxFemale.isChecked());
            birthDateCode = mPresenter.birthdateValid(mEditTextBirthdate.getText().toString());
            marketNameCode = mPresenter.marketNameValid(mEditTextMarketName.getText().toString());
            addressCode = mPresenter.addressValid(mTextViewAddress.getText().toString());
            postalCode = mPresenter.postalCodeValid(mTextViewPostalCode.getText().toString());
            detailedAddressCode = mPresenter.detailedAddressValid(mEditTextDetailedAddress.getText().toString());

            if (!mIsIdValid) {

                showDialog("아이디 중복 확인을 해주세요.");
            } else if (pwCode != mPresenter.PWD_VALID) {

                if (pwCode == mPresenter.PWD_EMPTY) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextPwd.setError("비밀번호를 입력해주세요.");
                    mEditTextPwd.requestFocus();
                } else if (pwCode == mPresenter.PWD_INVALID) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextPwd.setError("@$!%*#?& 중 하나의 특수문자를 포함한 10자 이상의 비밀번호를 입력해주세요.");
                    mEditTextPwd.requestFocus();
                } else if (pwCode == mPresenter.PWD_CHECK_EMPTY) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextCheckPwd.setError("비밀번호를 입력해주세요.");
                    mEditTextCheckPwd.requestFocus();
                } else if (pwCode == mPresenter.PWD_CHECK_NOT_EQUAL) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextCheckPwd.setError("비밀번호가 서로 다릅니다.");
                    mEditTextCheckPwd.requestFocus();
                }
            }else if(emailCode != mPresenter.EMAIL_VALID) {

                if (emailCode == mPresenter.EMAIL_EMPTY) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextEmail.setError("이메일을 입력해주세요.");
                    mEditTextEmail.requestFocus();
                } else if (emailCode == mPresenter.EMAIL_INVALID) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextEmail.setError("올바른 이메일을 입력해주세요.");
                    mEditTextEmail.requestFocus();
                }
            } else if (phoneNumberCode != mPresenter.PHONE_VALID) {

                if (phoneNumberCode == mPresenter.PHONE_EMPTY) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextPhoneNumber.setError("핸드폰 번호를 입력해주세요.");
                    mEditTextPhoneNumber.requestFocus();
                } else if (phoneNumberCode == mPresenter.PHONE_ERROR_OTHER_CHAR) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextPhoneNumber.setError("숫자만 입력해주세요. (ex : 19500101)");
                    mEditTextPhoneNumber.requestFocus();
                } else if (phoneNumberCode == mPresenter.PHONE_LENGTH_ERROR) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextPhoneNumber.setError("핸드폰 번호가 맞는지 확인해주세요.(010xxxxxxxx)");
                    mEditTextPhoneNumber.requestFocus();
                }
            } else if (!mIsValidPhoneNumber) {

                showDialog("핸드폰 인증을 해주세요.");
            } else if (mIsValidCorporateNumber != mPresenter.CORPORATE_NUMBER_VALID) {

                showDialog("사업자 번호를 입력한 뒤 확인해주세요.");
            } else if (marketNameCode != mPresenter.MARKET_NAME_VALID) {

                Utility.getInstance().activateKeyboard(getActivity());
                mEditTextMarketName.setError("매장명 입력해주세요");
                mEditTextMarketName.requestFocus();
            } else if (!mIsCategorySelected) {

                showDialog("카테고리를 선택해주세요.");
            } else if (postalCode != mPresenter.POSTAL_CODE_VALID
                    || addressCode != mPresenter.MARKET_ADDRESS_VALID) {

                showDialog("주소를 찾아 입력해주세요.");
            } else if (detailedAddressCode != mPresenter.DETAILED_ADDRESS_VALID) {

                Utility.getInstance().activateKeyboard(getActivity());
                mEditTextDetailedAddress.setError("상세 주소를 입력해주세요 (ex : 동/호/층)");
                mEditTextDetailedAddress.requestFocus();
            } else if (birthDateCode != mPresenter.BIRTH_VALID) {

                if (birthDateCode == mPresenter.BIRTH_EMPTY) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextBirthdate.setError("생년월일을 입력해주세요.");
                    mEditTextBirthdate.requestFocus();
                } else if (birthDateCode == mPresenter.BIRTH_ERROR_LENGTH) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextBirthdate.setError("8자리를 입력해주세요. (ex : 19500101)");
                    mEditTextBirthdate.requestFocus();
                } else if (birthDateCode == mPresenter.BIRTH_ERROR_OTHER_CHAR) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextBirthdate.setError("숫자만 입력해주세요. (ex : 19500101)");
                    mEditTextBirthdate.requestFocus();
                }
            } else if (genderCode != mPresenter.GENDER_VALID) {

                showDialog("성별을 선택해주세요.");
            } else if (neededCheckCode != mPresenter.NEEDED_VALID) {

                String msg = "";
                if (neededCheckCode == mPresenter.TERMS_OF_USE_NEEDED) {

                    msg += "이용약관 동의가 필요합니다.\n";
                }

                if (neededCheckCode == mPresenter.PERSONAL_INFO_NEEDED) {

                    msg += "개인정보 취급방침 동의가 필요합니다.\n";
                }

                showDialog(msg);
            } else {

                mBundleData.putString("id", editTextToString(mEditTextId));
                mBundleData.putString("pwd", editTextToString(mEditTextPwd));
                mBundleData.putString("birthdate", editTextToString(mEditTextBirthdate));
                mBundleData.putString("gender", discriminateGender());
                mBundleData.putString("email", editTextToString(mEditTextEmail));
                mBundleData.putString("phoneNumber", editTextToString(mEditTextPhoneNumber));
                mBundleData.putString("shopCode", editTextToString(mEditTextCorporateNumber));
                mBundleData.putString("shopName", editTextToString(mEditTextMarketName));
                mBundleData.putString("shopCategory", mSpinnerCategory.getSelectedItem().toString());
                mBundleData.putString("shopPostCode", mTextViewPostalCode.getText().toString());
                mBundleData.putString("shopAddress", mTextViewAddress.getText().toString() + " " + editTextToString(mEditTextDetailedAddress));
                mBundleData.putBoolean("marketingAgreement", mChkBoxMarketingInfo.isChecked());

                return true;
            }

            return false;
        }
    }

    // 에디트 텍스트의 문자열 받아오기
    private String editTextToString(EditText editText) {

        return editText.getText().toString();
    }

    // 성별 구분
    private String discriminateGender() {

        if (mChkBoxMale.isChecked()) {

            return "M";
        } else {

            return "F";
        }
    }

    // 알림(다이얼로그) 창 출력
    @Override
    public void showDialog(String msg) {

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (mDialog != null) {

                    mDialog = null;
                }
            }
        };

        if (mDialog == null) {

            Utility.getInstance().showDialog(mDialog, msg, getContext(), onClickListener);
        }
    }

    // 핸드폰 인증 완료 시 인증 버튼 변경
    @Override
    public void changePhoneAuthButton(int status) {

        if (status == UserApi.SUCCESS) {

            mIsValidPhoneNumber = true;
            mBtnSendAuthCode.setText("인증 완료");
            mBtnSendAuthCode.setEnabled(false);
            mBtnSendAuthCode.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.light_green));
            mTextLayoutAuthCode.setVisibility(View.GONE);
            mBtnCheckAuthCode.setVisibility(View.GONE);
        } else if (status == UserApi.ERROR_INVALID_AUTH) {

            showDialog("유효하지 않은 인증번호 입니다.");
        }
    }

    // 회원가입 완료
    @Override
    public void finishRegister(Intent intent) {

        ((GlobalApplication)getContext().getApplicationContext()).setUserId(mBundleData.getString("id"));
        ((GlobalApplication)getContext().getApplicationContext()).setUserCode("C");

        startActivity(intent);
    }

    // 회원가입 수행
    private void doRegister(String platform) {

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
                    mBundleData.getString("shopCode"),
                    mBundleData.getString("shopName"),
                    mBundleData.getString("shopPostCode"),
                    mBundleData.getString("shopAddress"),
                    mBundleData.getString("shopCategory"),
                    mChkBoxMarketingInfo.isChecked()
            );
        }
    }

    // 아이디 중복 확인 성공
    @Override
    public void onSuccess(AuthDTO.StatusRes res) {

        int status = res.getStatus();
        if (status == UserApi.SUCCESS) {

            mIsIdValid = true;

            mBtnCheckIdDuplication.setEnabled(false);
            mBtnCheckIdDuplication.setText("확인 완료");
            mBtnCheckIdDuplication.setBackgroundColor(getResources().getColor(R.color.light_green));
        } else if (status == UserApi.ID_DUPLICATE) {

            mEditTextId.setError("중복된 아이디가 존재합니다.");
        }
    }

    // 아이디 중복 확인 실패
    @Override
    public void onFailure(Throwable t) {

        showDialog("아이디 중복 확인 불가");
    }
}