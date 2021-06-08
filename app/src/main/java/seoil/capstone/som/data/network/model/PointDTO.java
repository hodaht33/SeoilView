package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// 포인트 DTO
public class PointDTO {

    /////////
    // 요청 //
    /////////

    // 잔여 포인트 추가 요청 DTO
    public static class InsertCurrentReq {

        @SerializedName("id")
        @Expose
        private String id;

        public InsertCurrentReq(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    // 잔여 포인트 수정 요청 DTO
    public static class UpdateCurrentReq {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("amount")
        @Expose
        private int amount;

        public UpdateCurrentReq(String id, int amount) {
            this.id = id;
            this.amount = amount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }

    // 적립 내역 추가 요청 DTO
    public static class InsertSaveReq {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("shopName")
        @Expose
        private String shopName;
        @SerializedName("savePointAmount")
        @Expose
        private int savePointAmount;

        public InsertSaveReq(String id, String shopName, int savePointAmount) {
            this.id = id;
            this.shopName = shopName;
            this.savePointAmount = savePointAmount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public int getSavePointAmount() {
            return savePointAmount;
        }

        public void setSavePointAmount(int savePointAmount) {
            this.savePointAmount = savePointAmount;
        }
    }

    // 사용 내역 추가 요청 DTO
    public static class InsertUsingReq {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("shopName")
        @Expose
        private String shopName;
        @SerializedName("usingPointAmount")
        @Expose
        private int usingPointAmount;

        public InsertUsingReq(String id, String shopName, int usingPointAmount) {
            this.id = id;
            this.shopName = shopName;
            this.usingPointAmount = usingPointAmount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public int getUsingPointAmount() {
            return usingPointAmount;
        }

        public void setUsingPointAmount(int usingPointAmount) {
            this.usingPointAmount = usingPointAmount;
        }
    }

    /////////
    // 응답 //
    /////////

    // 잔여 포인트 응답 DTO
    public static class GetCurrentRes {

        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("point")
        @Expose
        private int point;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }
    }

    // 적립 내역 응답 DTO
    public static class GetSaveRes {

        public class Result {

            @SerializedName("savePointDate")
            @Expose
            private String savePointDate;
            @SerializedName("shopName")
            @Expose
            private String shopName;
            @SerializedName("savePointAmount")
            @Expose
            private int savePointAmount;

            public String getSavePointDate() {
                return savePointDate;
            }

            public void setSavePointDate(String savePointDate) {
                this.savePointDate = savePointDate;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public int getSavePointAmount() {
                return savePointAmount;
            }

            public void setSavePointAmount(int savePointAmount) {
                this.savePointAmount = savePointAmount;
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

    // 사용 내역 응답 DTO
    public static class GetUsingRes {

        public class Result {
            @SerializedName("usingPointDate")
            @Expose
            private String usingPointDate;
            @SerializedName("shopName")
            @Expose
            private String shopName;
            @SerializedName("usingPointAmount")
            @Expose
            private int usingPointAmount;

            public String getUsingPointDate() {
                return usingPointDate;
            }

            public void setUsingPointDate(String usingPointDate) {
                this.usingPointDate = usingPointDate;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public int getUsingPointAmount() {
                return usingPointAmount;
            }

            public void setUsingPointAmount(int usingPointAmount) {
                this.usingPointAmount = usingPointAmount;
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

    // 사용 내역, 적립 내역 응답 DTO
    public static class GetUsingAndSaveRes {

        public class Result {

            @SerializedName("date")
            @Expose
            private String date;
            @SerializedName("amount")
            @Expose
            private int amount;
            @SerializedName("code")
            @Expose
            private String code;    // 'using' or 'save'

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
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
