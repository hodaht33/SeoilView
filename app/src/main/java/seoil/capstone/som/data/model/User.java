package seoil.capstone.som.data.model;

public class User {

    public static final int USER_CUSTOMER = 0;
    public static final int USER_MANAGER = 1;

    public static final int LOGIN_PLATFORM_NORMAL = 0;
    public static final int LOGIN_PLATFORM_NAVER = 1;
    public static final int LOGIN_PLATFORM_KAKAO = 2;

    private String id;
    private int code;
    private int loginPlatform;

    public User() {

    }

    public User(String id, int code) {
        this.id = id;
        this.code = code;
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

    public int getLoginPlatform() {
        return loginPlatform;
    }

    public void setLoginPlatform(int loginPlatform) {
        this.loginPlatform = loginPlatform;
    }
}
