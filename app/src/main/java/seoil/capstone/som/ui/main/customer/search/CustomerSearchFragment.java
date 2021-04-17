package seoil.capstone.som.ui.main.customer.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import seoil.capstone.som.R;

public class CustomerSearchFragment extends Fragment implements CustomerSearchContract.View {

    public CustomerSearchFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_search, container, false);



        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}