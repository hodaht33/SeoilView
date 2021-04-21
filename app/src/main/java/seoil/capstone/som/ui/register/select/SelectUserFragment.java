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

public class SelectUserFragment extends Fragment implements SelectUserContract.View, View.OnClickListener {

    private RegisterCommunicator.Communicator mCommunicator;
    private SelectUserContract.Presenter mPresenter;
    private Button mBtnToCustomerReg;
    private Button mBtnToManagerReg;

    public SelectUserFragment() {

    }

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

        mPresenter = new SelectUserPresenter();
        mPresenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_user, container, false);

        mPresenter = new SelectUserPresenter();
        mBtnToCustomerReg = view.findViewById(R.id.btnToCustomerRegister);
        mBtnToManagerReg = view.findViewById(R.id.btnToManagerRegister);

        mBtnToCustomerReg.setOnClickListener(this);
        mBtnToManagerReg.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroy() {

        mPresenter.releaseView();
        mPresenter = null;

        super.onDestroy();
    }

    @Override
    public void onDetach() {

        mCommunicator = null;

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

        if (v.getId() == R.id.btnToCustomerRegister) {

            mCommunicator.changeAnotherFragment(new CustomerRegisterFragment(), getArguments());
        } else if (v.getId() == R.id.btnToManagerRegister) {

            mCommunicator.changeAnotherFragment(new ManagerRegisterFragment(), getArguments());
        }
    }
}

