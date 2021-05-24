package seoil.capstone.som.ui.find.id;

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

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.R;

public class FindIdFragment extends Fragment implements FindIdContract.View {

    private FindIdPresenter mPresenter;
    private TextInputLayout textInputLayoutPhoneNumber;
    private TextInputLayout textInputLayoutAuthCode;
    private TextInputEditText editTextPhoneNumber;
    private TextInputEditText editTextAuthCode;
    private TextView textViewFindIdResult;
    private Button btnSendAndAuth;
    private Dialog mDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new FindIdPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mPresenter.releaseView();
        mPresenter.releaseInteractor();
        mPresenter = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_find_id, container, false);

        textInputLayoutPhoneNumber = view.findViewById(R.id.textLayoutFindIdPhoneNumber);
        textInputLayoutAuthCode = view.findViewById(R.id.textLayoutFindIdAuthCode);
        editTextPhoneNumber = view.findViewById(R.id.editTextFindIdPhoneNumber);
        editTextAuthCode = view.findViewById(R.id.editTextFindIdAuthCode);
        textViewFindIdResult = view.findViewById(R.id.textViewFindIdResult);
        btnSendAndAuth = view.findViewById(R.id.btnFindIdSendAndAuth);
        btnSendAndAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPresenter.sendSms(editTextPhoneNumber.getText().toString());
            }
        });

        return view;
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
    public void visibleAuthView() {

        textInputLayoutAuthCode.setVisibility(View.VISIBLE);
        btnSendAndAuth.setText("인증");
        btnSendAndAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPresenter.sendAuthCode(editTextAuthCode.getText().toString());
            }
        });
    }

    @Override
    public void visibleResultView(ArrayList<String> results) {

        textInputLayoutPhoneNumber.setVisibility(View.GONE);
        textInputLayoutAuthCode.setVisibility(View.GONE);
        btnSendAndAuth.setVisibility(View.GONE);

        textViewFindIdResult.setVisibility(View.VISIBLE);

        if (results.isEmpty()) {

            textViewFindIdResult.setText(textViewFindIdResult.getText().toString() + "아이디가 존재하지 않습니다.");

            return;
        }

        String text = textViewFindIdResult.getText().toString();
        for (String id : results) {

            text += id + '\n';
        }

        textViewFindIdResult.setText(text);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
