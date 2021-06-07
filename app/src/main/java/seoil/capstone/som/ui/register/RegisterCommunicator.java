package seoil.capstone.som.ui.register;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import seoil.capstone.som.base.BaseCommunicator;

// 프레그먼트에서 액티비티의 행동 제어를 하기 위한 인터페이스
public interface RegisterCommunicator {

    interface Communicator extends BaseCommunicator.Communicator {

        void changeAnotherFragment(Fragment fragment, Bundle bundle);
    }
}
