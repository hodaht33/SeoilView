package seoil.capstone.som.ui.register;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import seoil.capstone.som.base.BaseCommunicator;

public interface RegisterCommunicator {

    interface Communicator extends BaseCommunicator.Communicator {

        void changeAnotherFragment(Fragment fragment, Bundle bundle);
    }
}
