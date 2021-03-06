package seoil.capstone.som.ui.find.pwd;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import seoil.capstone.som.R;
import seoil.capstone.som.util.Utility;

// 비밀번호 찾기(변경) 뷰(프레그먼트)
public class FindPwdFragment extends Fragment implements FindPwdContract.View {

    private FindPwdPresenter mPresenter;
    private TextInputLayout textInputLayoutId;
    private TextInputLayout textInputLayoutAuthCode;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutPasswordCheck;
    private TextInputEditText editTextId;
    private TextInputEditText editTextAuthCode;
    private TextInputEditText editTextPassword;
    private TextInputEditText editTextPasswordCheck;
    private TextView textViewNotice;
    private Button btnSendAndAuth;
    private Button btnChangePassword;
    private Dialog mDialog;

    // 프레젠터 생성
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new FindPwdPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();
    }

    // 프레젠터 삭제
    @Override
    public void onDestroy() {
        super.onDestroy();

        mPresenter.releaseInteractor();
        mPresenter.releaseView();
        mPresenter.releaseAnother();
        mPresenter = null;
    }

    // UI, 리스너 초기화
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_find_pwd, container, false);

        textInputLayoutId = view.findViewById(R.id.textLayoutFindPwdUserId);
        textInputLayoutAuthCode = view.findViewById(R.id.textLayoutFindPwdAuthCode);
        textInputLayoutPassword = view.findViewById(R.id.textLayoutFindPwdPassword);
        textInputLayoutPasswordCheck = view.findViewById(R.id.textLayoutFindPwdPasswordCheck);
        editTextId = view.findViewById(R.id.editTextFindPwdUserId);
        editTextAuthCode = view.findViewById(R.id.editTextFindPwdAuthCode);
        editTextPassword = view.findViewById(R.id.editTextFindPwdPassword);
        editTextPasswordCheck = view.findViewById(R.id.editTextFindPwdPasswordCheck);
        textViewNotice = view.findViewById(R.id.textViewFindPwdNotice);
        btnSendAndAuth = view.findViewById(R.id.btnFindPwdSendAndAuth);
        btnChangePassword = view.findViewById(R.id.btnFindPwdChangePassword);

        btnSendAndAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPresenter.sendSms(editTextId.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

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

        Utility.getInstance().showDialog(mDialog, msg, getContext(), onClickListener);
    }

    // 인증번호 입력 뷰 활성화
    @Override
    public void visibleAuthView() {

        editTextId.setEnabled(false);
        textInputLayoutAuthCode.setVisibility(View.VISIBLE);
        btnSendAndAuth.setText("인증");
        btnSendAndAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPresenter.sendAuthCode(editTextAuthCode.getText().toString());
            }
        });
    }

    // 변경할 비밀번호 입력 뷰 활성화
    @Override
    public void changeToPasswordView() {

        textInputLayoutId.setVisibility(View.GONE);
        textInputLayoutAuthCode.setVisibility(View.GONE);
        textViewNotice.setVisibility(View.GONE);
        btnSendAndAuth.setVisibility(View.GONE);

        textInputLayoutPassword.setVisibility(View.VISIBLE);
        textInputLayoutPasswordCheck.setVisibility(View.VISIBLE);
        btnChangePassword.setVisibility(View.VISIBLE);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPresenter.changePassword(editTextPassword.getText().toString(), editTextPasswordCheck.getText().toString());
            }
        });
    }

    // 비밀번호 변경 결과 출력 뷰 활성화
    @Override
    public void changeToResultView() {

        textInputLayoutPassword.setVisibility(View.GONE);
        textInputLayoutPasswordCheck.setVisibility(View.GONE);
        btnChangePassword.setVisibility(View.GONE);

        textViewNotice.setVisibility(View.VISIBLE);
        textViewNotice.setText("비밀번호가 변경되었습니다.\n뒤로가기 버튼을 눌러 변경된 비밀번호로 로그인하세요.");
    }
}
