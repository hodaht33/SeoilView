package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// 매출 DTO
public class SalesDTO {

    // 매출 추가 요청 DTO
    public static class Req {

        @SerializedName("salesCode")
        @Expose
        private int salesCode;
        @SerializedName("salesDate")
        @Expose
        private String salesDate;
        @SerializedName("shopId")
        @Expose
        private String shopId;
        @SerializedName("salesName")
        @Expose
        private String salesName;
        @SerializedName("salesAmount")
        @Expose
        private int salesAmount;

        public Req(int salesCode, String salesDate, String shopId, String salesName, int salesAmount) {
            this.salesCode = salesCode;
            this.salesDate = salesDate;
            this.shopId = shopId;
            this.salesName = salesName;
            this.salesAmount = salesAmount;
        }

        public int getSalesCode() {
            return salesCode;
        }

        public void setSalesCode(int salesCode) {
            this.salesCode = salesCode;
        }

        public String getSalesDate() {
            return salesDate;
        }

        public void setSalesDate(String salesDate) {
            this.salesDate = salesDate;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getSalesName() {
            return salesName;
        }

        public void setSalesName(String salesName) {
            this.salesName = salesName;
        }

        public int getSalesAmount() {
            return salesAmount;
        }

        public void setSalesAmount(int salesAmount) {
            this.salesAmount = salesAmount;
        }
    }

    // 매출 응답 DTO
    public static class GetRes {

        public class Result {

            @SerializedName("salesCode")
            @Expose
            private int salesCode;
            @SerializedName("salesDate")
            @Expose
            private String salesDate;
            @SerializedName("salesName")
            @Expose
            private String salesName;
            @SerializedName("salesAmount")
            @Expose
            private int salesAmount;

            public int getSalesCode() {
                return salesCode;
            }

            public void setSalesCode(int salesCode) {
                this.salesCode = salesCode;
            }

            public String getSalesDate() {
                return salesDate;
            }

            public void setSalesDate(String salesDate) {
                this.salesDate = salesDate;
            }

            public String getSalesName() {
                return salesName;
            }

            public void setSalesName(String salesName) {
                this.salesName = salesName;
            }

            public int getSalesAmount() {
                return salesAmount;
            }

            public void setSalesAmount(int salesAmount) {
                this.salesAmount = salesAmount;
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

    // 매출 통계 응답 DTO
    public static class GetStatisticsRes {

        public class Result {

            @SerializedName("salesDate")
            @Expose
            private String salesDate;
            @SerializedName("salesAmount")
            @Expose
            private int salesAmount;

            public String getSalesDate() {
                return salesDate;
            }

            public void setSalesDate(String salesDate) {
                this.salesDate = salesDate;
            }

            public int getSalesAmount() {
                return salesAmount;
            }

            public void setSalesAmount(int salesAmount) {
                this.salesAmount = salesAmount;
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
