package seoil.capstone.som.ui.register.customer;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import seoil.capstone.som.R;
import seoil.capstone.som.ui.register.RegisterCommunicator;
import seoil.capstone.som.ui.register.select.SelectUserFragment;

public class CustomerRegisterFragment extends Fragment implements CustomerRegisterContract.View, View.OnClickListener {

    private CustomerRegisterPresenter mPresenter;
    private RegisterCommunicator.Communicator mCommunicator;
//    private OnBackPressedCallback mBackPressedCallback;
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
    private boolean isCheckId;
    private boolean isCheckPhoneNumber;
    String mPlatform;

    public CustomerRegisterFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

//        if (context instanceof RegisterCommunicator.Communicator) {
//            mCommunicator = (RegisterCommunicator.Communicator) context;
//        } else {
//            throw new RuntimeException(context.toString() + " not implement Communicator");
//        }

//        mBackPressedCallback = new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                // TODO: 간편 로그인 시에는 정보를 들고 돌아가야 함
//                //mCommunicator.changeAnotherFragment(new SelectUserFragment());
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(mBackPressedCallback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new CustomerRegisterPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_register, container, false);

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
        isCheckId = false;
        isCheckPhoneNumber = false;

        mChkBoxFemale.setOnClickListener(this);
        mChkBoxMale.setOnClickListener(this);
        mBtnCheckIdDuplication.setOnClickListener(this);
        mBtnSendAuthCode.setOnClickListener(this);
        mBtnCheckAuthCode.setOnClickListener(this);
        mBtnFinish.setOnClickListener(this);

        // TODO: bundle을 Presenter로 넘겨 naver와 kakao 아님 일반을 판단하여 뷰를 바꾸는 메서드 호출하는 구조로 변경
        Bundle bundle = getArguments();
        mPlatform = bundle.getString("platform");

        if (mPlatform.equals("naver")) {

            // 이미 있는 정보들을 입력받지 않기 위해 없앰
            mTextLayoutId.setVisibility(View.GONE);
            mTextLayoutPwd.setVisibility(View.GONE);
            mTextLayoutCheckPwd.setVisibility(View.GONE);
            mTextLayoutEmail.setVisibility(View.GONE);
            mTextLayoutPhoneNumber.setVisibility(View.GONE);
            mTextLayoutAuthCode.setVisibility(View.GONE);
            mTextLayoutBirthdate.setVisibility(View.GONE);
            mBtnCheckIdDuplication.setVisibility(View.GONE);
            mBtnSendAuthCode.setVisibility(View.GONE);
            mBtnCheckAuthCode.setVisibility(View.GONE);
        } else if (mPlatform.equals("kakao")) {

            mTextLayoutId.setVisibility(View.GONE);
            mTextLayoutPwd.setVisibility(View.GONE);
            mTextLayoutCheckPwd.setVisibility(View.GONE);
            mBtnCheckIdDuplication.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onDestroy() {

        mPresenter.releaseView();
        mPresenter = null;
        mCommunicator = null;

        super.onDestroy();
    }

    @Override
    public void onDetach() {

//        mBackPressedCallback.remove();

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

        int viewId = v.getId();

        if (viewId == R.id.btnCRegitCheckIdDuplication) {


        } else if (viewId == R.id.btnCRegitSendAuthorizationCode) {

            mTextLayoutAuthCode.setVisibility(View.VISIBLE);
            mBtnCheckAuthCode.setVisibility(View.VISIBLE);
        } else if (viewId == R.id.btnCRegitCheckAuthorizationCode) {

            // TODO: Presenter에 인증번호를 보내 확인, Presenter에선 Interactor에 보냄, Interactor에선 api로 보내 확인후 listener를 통해 확인, 확인됐으면 안보이게 설정
        } else if (viewId == R.id.btnCRegitFinish) {

            if (mPlatform.equals("naver")) {

                if (mChkBoxTermsOfUse.isChecked()
                && mChkBoxPersonalInfo.isChecked()) {

                    /*
                        intent.putExtra("birthdate", naverLoginResponse.getBirthdate());
                        intent.putExtra("gender", naverLoginResponse.getGender());
                        intent.putExtra("email", naverLoginResponse.getEmail());
                        intent.putExtra("phoneNumber", naverLoginResponse.getPhoneNumber());
                    */

                    // TODO: db로 데이터 전송 및 확인 후 메인으로 이동
                }
            } else if (mPlatform.equals("kakao")) {

                if (isCheckPhoneNumber
                && mChkBoxTermsOfUse.isChecked()
                && mChkBoxPersonalInfo.isChecked()) {

                    // TODO: db로 데이터 전송 및 확인 후 메인으로 이동
                }
            } else {

                if (isCheckId
                && isCheckPhoneNumber
                && mChkBoxTermsOfUse.isChecked()
                && mChkBoxPersonalInfo.isChecked()) {

                    // TODO: db로 데이터 전송 및 확인 후 로그인 화면으로 이동
                }
            }
        } else if (viewId == R.id.chBoxCRegitFemale) {

            mChkBoxMale.setChecked(false);
        } else if (viewId == R.id.chBoxCRegitMale) {

            mChkBoxFemale.setChecked(false);
        }
    }
}