package seoil.capstone.som.ui.register.customer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.R;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.UserApi;
import seoil.capstone.som.data.network.model.AuthDTO;
import seoil.capstone.som.ui.register.RegisterCommunicator;
import seoil.capstone.som.ui.register.select.SelectUserFragment;
import seoil.capstone.som.util.Utility;

// TODO: 제대로된 MVP으로 만들어져 있지 않음, 추후 리팩토링 필요(presenter내에서 valid검사, id중복확인 요청은 interactor를 통해 수행)
public class CustomerRegisterFragment extends Fragment implements CustomerRegisterContract.View, View.OnClickListener, OnFinishApiListener<AuthDTO.StatusRes> {

    private RegisterCommunicator.Communicator mCommunicator;
    private OnBackPressedCallback mOnBackPressedCallback;
    private CustomerRegisterPresenter mPresenter;
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
    private Bundle mBundleData;
    private boolean mIsIdValid;
    private boolean mIsValidPhoneNumber;
    private int mIdValidCode;
    private Dialog mDialog;
    private long mLastTimeBackPressed;

    public CustomerRegisterFragment() {

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
        mOnBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                if(System.currentTimeMillis() - mLastTimeBackPressed < 1000) {

                    Log.d("test", "t");
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

    @Override
    public void onDetach() {

        mOnBackPressedCallback.remove();
        mCommunicator = null;

        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new CustomerRegisterPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_register, container, false);

        initView(view);
        initListener();

        mIsIdValid = false;
        mIsValidPhoneNumber = false;
        mIdValidCode = mPresenter.ID_EMPTY;

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

        return view;
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
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        if (viewId == R.id.btnCRegitCheckIdDuplication) {

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
        } else if (viewId == R.id.btnCRegitSendAuthorizationCode) {

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
        } else if (viewId == R.id.btnCRegitCheckAuthorizationCode) {

            mPresenter.sendAuthCode(mEditTextPhoneNumber.getText().toString(), mEditTextAuthCode.getText().toString());
        } else if (viewId == R.id.btnCRegitFinish) {

            doRegister(mBundleData.getString("platform"));
        } else if (viewId == R.id.chBoxCRegitFemale) {

            mChkBoxMale.setChecked(false);
        } else if (viewId == R.id.chBoxCRegitMale) {

            mChkBoxFemale.setChecked(false);
        }
    }

    @Override
    public void showDialog(String msg) {

        if (mDialog == null) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(msg)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (mDialog != null) {

                                mDialog = null;
                            }
                        }
                    });

            mDialog = builder.create();
            mDialog.show();
        }
    }

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

    @Override
    public void finishRegister(Intent intent) {

        ((GlobalApplication)getContext().getApplicationContext()).setUserCode("C");

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
                mBtnCheckIdDuplication.setBackgroundColor(getActivity().getResources().getColor(R.color.black));
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
                mBtnSendAuthCode.setText("인증번호 전송");
                mBtnSendAuthCode.setEnabled(true);
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

        int emailCode;
        int phoneNumberCode;
        int birthDateCode;
        int neededCheckCode;
        int idCode;
        int pwCode;
        int genderCode;

        neededCheckCode = mPresenter.neededCheck(mChkBoxTermsOfUse.isChecked(), mChkBoxPersonalInfo.isChecked());

        if (platform.equals("naver")) {

            if (neededCheckCode != mPresenter.NEEDED_VALID) {

                String msg = "";
                if (neededCheckCode == mPresenter.TERMS_OF_USE_NEEDED) {

                    msg += "이용약관 동의가 필요합니다.\n";
                } else if (neededCheckCode == mPresenter.PERSONAL_INFO_NEEDED) {

                    msg += "개인정보 취급방침 동의가 필요합니다.\n";
                }

                showDialog(msg);
            } else {

                mBundleData.putString("pwd", "naver");
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

            if (!mIsIdValid) {

                showDialog("아이디 중복 확인을 해주세요.");
            } else if (idCode != mPresenter.ID_VALID) {

                if(idCode == mPresenter.ID_EMPTY) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextId.setError("아이디를 입력해주세요.");
                    mEditTextId.requestFocus();
                } else if (idCode == mPresenter.ID_SHORT) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextId.setError("아이디가 너무 짧습니다. 8자 이상 입력해주세요.");
                    mEditTextId.requestFocus();
                } else if (idCode == mPresenter.ID_LONG) {

                    Utility.getInstance().activateKeyboard(getActivity());
                    mEditTextId.setError("아이디가 너무 깁니다. 20자 이하로 입력해주세요.");
                    mEditTextId.requestFocus();
                }
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
    public void onSuccess(AuthDTO.StatusRes res) {

        int status = res.getStatus();
        if (status == UserApi.SUCCESS) {

            mIsIdValid = true;

            mBtnCheckIdDuplication.setEnabled(false);
            mBtnCheckIdDuplication.setText("확인 완료");
            mBtnCheckIdDuplication.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.light_green));
        } else if (status == UserApi.ID_DUPLICATE) {

            mEditTextId.setError("중복된 아이디가 존재합니다.");
        }
    }

    @Override
    public void onFailure(Throwable t) {

        showDialog("아이디 중복 확인 불가");
    }
}