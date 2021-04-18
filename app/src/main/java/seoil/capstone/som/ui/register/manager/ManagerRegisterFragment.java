package seoil.capstone.som.ui.register.manager;

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

import seoil.capstone.som.R;
import seoil.capstone.som.ui.register.RegisterCommunicator;

public class ManagerRegisterFragment extends Fragment implements ManagerRegisterContract.View, View.OnClickListener {

    private ManagerRegisterPresenter mPresenter;
    private RegisterCommunicator.Communicator mCommunicator;
    private OnBackPressedCallback mBackPressedCallback;
    private TextInputEditText mEditTextId;
    private TextInputEditText mEditTextPwd;
    private TextInputEditText mEditTextCheckPwd;
    private TextInputEditText mEditTextEmail;
    private TextInputEditText mEditTextCoporateNumber;
    private TextInputEditText mEditTextPhoneNumber;
    private TextInputEditText mEditTextBirthdate;
    private CheckBox mChkBoxFemale;
    private CheckBox mChkBoxMale;
    private Button mBtnFinish;
    private boolean isCheckId;
    private boolean isCheckCoporateNumber;
    private boolean isCheckPhoneNumber;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manager_register, container, false);

        mEditTextId = view.findViewById(R.id.editTextMRegitId);
        mEditTextPwd = view.findViewById(R.id.editTextMRegitPw);
        mEditTextCheckPwd = view.findViewById(R.id.editTextMRegitCheckPw);
        mEditTextEmail = view.findViewById(R.id.editTextMRegitEmail);
        mEditTextCoporateNumber = view.findViewById(R.id.editTextMRegitCorporateNumber);
        mEditTextPhoneNumber = view.findViewById(R.id.editTextMRegitPhoneNumber);
        mEditTextBirthdate = view.findViewById(R.id.editTextMRegitBirthdate);
        mChkBoxFemale = view.findViewById(R.id.chBoxMRegitFemale);
        mChkBoxMale = view.findViewById(R.id.chBoxMRegitMale);
        mBtnFinish = view.findViewById(R.id.btnMRegitFinish);

        Bundle bundle = getArguments();
        if (bundle.getString("platform").equals("naver")) {


            /*
            intent.putExtra("birthdate", naverLoginResponse.getBirthdate());
                                intent.putExtra("gender", naverLoginResponse.getGender());
                                intent.putExtra("email", naverLoginResponse.getEmail());
                                intent.putExtra("phoneNumber", naverLoginResponse.getPhoneNumber());
             */
        }

        mBtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "ManagerRegister", Toast.LENGTH_SHORT).show();

                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {

        mPresenter = null;

        super.onDestroy();
    }

    @Override
    public void onDetach() {

        mBackPressedCallback.remove();

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

    }
}