package seoil.capstone.som.ui.register;

import androidx.fragment.app.Fragment;

import seoil.capstone.som.base.BaseCommunicator;

public interface RegisterCommunicator {
    interface Communicator extends BaseCommunicator.Communicator {
        void changeAnotherFragment(Fragment fragment);
    }
}
