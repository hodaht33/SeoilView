package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("state")
    @Expose
    private boolean state;

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
