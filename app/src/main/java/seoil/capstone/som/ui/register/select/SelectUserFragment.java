package seoil.capstone.som.ui.register.select;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import seoil.capstone.som.R;
import seoil.capstone.som.ui.register.RegisterCommunicator;
import seoil.capstone.som.ui.register.customer.CustomerRegisterFragment;
import seoil.capstone.som.ui.register.manager.ManagerRegisterFragment;

// 회원가입 시 사용자 분류 선택 프레그먼트
public class SelectUserFragment extends Fragment implements View.OnClickListener {

    private RegisterCommunicator.Communicator mCommunicator;
    private Button mBtnToCustomerReg;
    private Button mBtnToManagerReg;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof RegisterCommunicator.Communicator) {

            mCommunicator = (RegisterCommunicator.Communicator) context;
        } else {

            throw new RuntimeException(context.toString() + " not implement Communicator");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_user, container, false);

        mBtnToCustomerReg = view.findViewById(R.id.btnToCustomerRegister);
        mBtnToManagerReg = view.findViewById(R.id.btnToManagerRegister);

        mBtnToCustomerReg.setOnClickListener(this);
        mBtnToManagerReg.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDetach() {

        mCommunicator = null;

        super.onDetach();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnToCustomerRegister) {

            mCommunicator.changeAnotherFragment(new CustomerRegisterFragment(), getArguments());
        } else if (v.getId() == R.id.btnToManagerRegister) {

            mCommunicator.changeAnotherFragment(new ManagerRegisterFragment(), getArguments());
        }
    }
}

