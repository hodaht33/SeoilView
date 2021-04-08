package seoil.capstone.som.ui.main.manager.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import seoil.capstone.som.R;
import seoil.capstone.som.ui.login.LoginActivity;
import seoil.capstone.som.ui.main.MainCommunicator;

public class ManagerHomeFragment extends Fragment implements ManagerHomeContract.View, View.OnClickListener {

    private ManagerHomePresenter mPresenter;
    private MainCommunicator.Communicator mCommunicator;
    private Button mBtnLogout;

    public ManagerHomeFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MainCommunicator.Communicator) {
            mCommunicator = (MainCommunicator.Communicator) context;
        } else {
            throw new RuntimeException(context.toString() + " not implement Communicator");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ManagerHomePresenter();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_home, container, false);

        mBtnLogout = view.findViewById(R.id.btnManagerHomeLogout);
        mBtnLogout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroy() {
        mPresenter.releaseResources();
        mPresenter.releaseContext();
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
    public void showProgressBar() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), LoginActivity.class);

        startActivity(intent);

        getActivity().finish();
    }
}