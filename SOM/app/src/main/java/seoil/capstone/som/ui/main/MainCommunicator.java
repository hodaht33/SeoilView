package seoil.capstone.som.ui.main;

import seoil.capstone.som.base.BaseCommunicator;

public interface MainCommunicator {

    interface Communicator extends BaseCommunicator.Communicator {
        int logout();
    }
}
