package seoil.capstone.som.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import seoil.capstone.som.R;
import seoil.capstone.som.ui.register.select.SelectUserFragment;

// 사용자 분류에 따른 회원가입 프레그먼트를 보여주는 액티비티
public class RegisterActivity extends AppCompatActivity implements RegisterCommunicator.Communicator {

    private int mFragmentLayoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFragmentLayoutId = R.id.fragmentLayoutRegit;

        SelectUserFragment selectUserFragment = new SelectUserFragment();
        selectUserFragment.setArguments(getIntent().getBundleExtra("data"));

        getSupportFragmentManager()
                .beginTransaction()
                .add(mFragmentLayoutId, selectUserFragment)
                .commit();
    }

    // 손님 또는 점주의 회원가입 화면을 전환하기 위한 메서드
    @Override
    public void changeAnotherFragment(Fragment fragment, Bundle bundle) {

        fragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(mFragmentLayoutId, fragment)
                .commit();
    }
}