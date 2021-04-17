package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("pwd")
    @Expose
    private String pwd;

    public LoginRequest(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
