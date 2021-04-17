package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import seoil.capstone.som.data.model.User;

public class LoginResponse {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int LOGIN_FAIL_ID = 2;
    public static final int LOGIN_FAIL_PWD = 3;

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("code")
    @Expose
    private int code;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
