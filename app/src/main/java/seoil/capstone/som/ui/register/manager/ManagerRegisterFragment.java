package seoil.capstone.som.ui.register.manager;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import seoil.capstone.som.R;
import seoil.capstone.som.ui.register.RegisterCommunicator;
import seoil.capstone.som.ui.register.select.SelectUserFragment;

public class ManagerRegisterFragment extends Fragment implements ManagerRegisterContract.View {

    private ManagerRegisterPresenter mPresenter;
    private RegisterCommunicator.Communicator mCommunicator;
    private OnBackPressedCallback mBackPressedCallback;
    private TextInputEditText mEditTextId;
    private Button mBtnFinish;

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

        mBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // TODO: 간편 로그인 시에는 정보를 들고 돌아가야 함
                mCommunicator.changeAnotherFragment(new SelectUserFragment());
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(mBackPressedCallback);
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

        mEditTextId = view.findViewById(R.id.editTextTextMRegitId);

        mBtnFinish = view.findViewById(R.id.btnMRegitFinish);

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
}