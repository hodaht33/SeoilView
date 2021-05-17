package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatisticsData {

    public static class InsertReq {

        @SerializedName("statisticsDate")
        @Expose
        private String statisticsDate;
        @SerializedName("userId")
        @Expose
        private String userId;
        @SerializedName("shopId")
        @Expose
        private String shopId;

        public InsertReq(String statisticsDate, String userId, String shopId) {
            this.statisticsDate = statisticsDate;
            this.userId = userId;
            this.shopId = shopId;
        }

        public String getStatisticsDate() {
            return statisticsDate;
        }

        public void setStatisticsDate(String statisticsDate) {
            this.statisticsDate = statisticsDate;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }
    }

    public static class GetAgeGroupRes {

        public class Result {

            @SerializedName("ageGroup")
            @Expose
            private int ageGroup;
            @SerializedName("count")
            @Expose
            private int count;

            public int getAgeGroup() {
                return ageGroup;
            }

            public void setAgeGroup(int ageGroup) {
                this.ageGroup = ageGroup;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
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

    public static class GetGenderRes {

        public class Result {

            @SerializedName("gender")
            @Expose
            private String gender;
            @SerializedName("count")
            @Expose
            private int count;

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("results")
        @Expose
        private List<GetAgeGroupRes.Result> results;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<GetAgeGroupRes.Result> getResults() {
            return results;
        }

        public void setResults(List<GetAgeGroupRes.Result> results) {
            this.results = results;
        }
    }

    public static class StatusRes {

        @SerializedName("status")
        @Expose
        private int status;
    }
}
