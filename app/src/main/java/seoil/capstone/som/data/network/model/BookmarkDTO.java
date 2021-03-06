package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// 즐겨찾기 DTO
public class BookmarkDTO {

    // 요청 //

    // 추가 요청 DTO
    public static class InsertReq {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("shopId")
        @Expose
        private String shopId;

        public InsertReq(String id, String shopId) {
            this.id = id;
            this.shopId = shopId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }
    }

    // 응답 //

    // 사용자 정보 응답 DTO
    public static class UserInfoRes {

        public class Result {

            @SerializedName("userId")
            @Expose
            private String userId;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }

        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("results")
        @Expose
        private List<Result> results;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<Result> getResults() {
            return results;
        }

        public void setResults(List<Result> results) {
            this.results = results;
        }
    }

    // 매장 정보 응답 DTO
    public static class ShopInfoRes {

        public class Result {

            @SerializedName("shopId")
            @Expose
            private String shopId;
            @SerializedName("shopName")
            @Expose
            private String shopName;
            @SerializedName("shopCategory")
            @Expose
            private String shopCategoory;

            public String getShopId() {
                return shopId;
            }

            public void setShopId(String shopId) {
                this.shopId = shopId;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public String getShopCategoory() {
                return shopCategoory;
            }

            public void setShopCategoory(String shopCategoory) {
                this.shopCategoory = shopCategoory;
            }
        }

        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("results")
        @Expose
        private List<Result> results;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<Result> getResults() {
            return results;
        }

        public void setResults(List<Result> results) {
            this.results = results;
        }
    }

    // 서버 응답 DTO
    public static class StatusRes {

        @SerializedName("status")
        @Expose
        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
