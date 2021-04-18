package seoil.capstone.som.data.db.model;

import com.google.gson.annotations.SerializedName;

// Local DB Model
public class User {

    public static final int USER_CUSTOMER = 0;
    public static final int USER_MANAGER = 1;

    public static final int LOGIN_PLATFORM_NORMAL = 0;
    public static final int LOGIN_PLATFORM_NAVER = 1;
    public static final int LOGIN_PLATFORM_KAKAO = 2;

    @SerializedName("id")
    private String id;

    @SerializedName("birthdate")
    private String birthdate;

    @SerializedName("gender")
    private int gender;

    @SerializedName("email")
    private String email;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("code")
    private String code;

    public User(String id, String birthdate, int gender, String email, String phoneNumber, String code) {
        this.id = id;
        this.birthdate = birthdate;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.code = code;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
