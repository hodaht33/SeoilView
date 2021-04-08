package seoil.capstone.som.ui.main.manager.event;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import seoil.capstone.som.R;

public class ManagerEventFragment extends Fragment implements ManagerEventContract.View {

    public ManagerEventFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_event, container, false);



        return view;
    }

    @Override
    public void showProgressBar() {

    }
}