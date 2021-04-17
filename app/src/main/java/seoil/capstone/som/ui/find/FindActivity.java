package seoil.capstone.som.ui.find;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import seoil.capstone.som.R;

public class FindActivity extends AppCompatActivity implements FindContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}