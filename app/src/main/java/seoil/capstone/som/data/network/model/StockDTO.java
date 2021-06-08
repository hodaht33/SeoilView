package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// 재고 DTO
public class StockDTO {

    // 추가, 수량 수정 요청 DTO
    public static class Req {

        @SerializedName("shopId")
        @Expose
        private String shopId;
        @SerializedName("stockName")
        @Expose
        private String stockName;
        @SerializedName("stockAmount")
        @Expose
        private int stockAmount;

        public Req(String shopId, String stockName, int stockAmount) {
            this.shopId = shopId;
            this.stockName = stockName;
            this.stockAmount = stockAmount;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getStockName() {
            return stockName;
        }

        public void setStockName(String stockName) {
            this.stockName = stockName;
        }

        public int getStockAmount() {
            return stockAmount;
        }

        public void setStockAmount(int stockAmount) {
            this.stockAmount = stockAmount;
        }
    }

    // 이름, 수량 수정 요청 DTO
    public static class UpdateAllReq {

        @SerializedName("shopId")
        @Expose
        private String shopId;
        @SerializedName("stockPrevName")
        @Expose
        private String stockPrevName;
        @SerializedName("stockNewName")
        @Expose
        private String stockNewName;
        @SerializedName("stockAmount")
        @Expose
        private int stockAmount;

        public UpdateAllReq(String shopId, String stockPrevName, String stockNewName, int stockAmount) {
            this.shopId = shopId;
            this.stockPrevName = stockPrevName;
            this.stockNewName = stockNewName;
            this.stockAmount = stockAmount;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getStockPrevName() {
            return stockPrevName;
        }

        public void setStockPrevName(String stockPrevName) {
            this.stockPrevName = stockPrevName;
        }

        public String getStockNewName() {
            return stockNewName;
        }

        public void setStockNewName(String stockNewName) {
            this.stockNewName = stockNewName;
        }

        public int getStockAmount() {
            return stockAmount;
        }

        public void setStockAmount(int stockAmount) {
            this.stockAmount = stockAmount;
        }
    }

    // 검색 응답 DTO
    public static class GetRes {

        public class Result {

            @SerializedName("stockName")
            @Expose
            private String stockName;
            @SerializedName("stockAmount")
            @Expose
            private int stockAmount;

            public String getStockName() {
                return stockName;
            }

            public void setStockName(String stockName) {
                this.stockName = stockName;
            }

            public int getStockAmount() {
                return stockAmount;
            }

            public void setStockAmount(int stockAmount) {
                this.stockAmount = stockAmount;
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
    public class StatusRes {

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
