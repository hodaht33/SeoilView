package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentPoint {

    /////////
    // 요청 //
    /////////

    public static class InsertReq {

        @SerializedName("id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class PutReq {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("amount")
        @Expose
        private int amount;

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

    /////////
    // 응답 //
    /////////

    public static class GetRes {

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
