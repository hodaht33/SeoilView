package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("birthdate")
    @Expose
    private String birthdate;
    @SerializedName("gender")
    @Expose
    private int gender;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
