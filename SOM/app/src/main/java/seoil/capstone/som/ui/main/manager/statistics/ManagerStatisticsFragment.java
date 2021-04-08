package seoil.capstone.som.ui.main.manager.statistics;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import seoil.capstone.som.R;

public class ManagerStatisticsFragment extends Fragment implements ManagerStatisticsContract.View {

    public ManagerStatisticsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_statistics, container, false);



        return view;
    }

    @Override
    public void showProgressBar() {

    }
}