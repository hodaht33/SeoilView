package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// 통계 DTO
public class StatisticsData {

    // 통계(방문 정보) 추가 요청 DTO
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

    // 나이대 통계 응답 DTO
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

    // 성별 통계 응답 DTO
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

    // 일별 매출 통계 응답 DTO
    public static class GetDayRes {

        public class Result {

            @SerializedName("salesDate")
            @Expose
            private String salesDate;
            @SerializedName("salesName")
            @Expose
            private String salesName;
            @SerializedName("salesAmount")
            @Expose
            private int salesAmount;

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
        private List<GetGenderRes.Result> results;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }


        public List<GetGenderRes.Result> getResults() {
            return results;
        }

        public void setResults(List<GetGenderRes.Result> results) {
            this.results = results;
        }
    }

    // 주별 매출 통계 응답 DTO
    public static class GetWeekRes {

        public class Result {

            @SerializedName("type")
            @Expose
            private String type;
            @SerializedName("startDate")
            @Expose
            private String startDate;
            @SerializedName("endDate")
            @Expose
            private String endDate;
            @SerializedName("salesAmount")
            @Expose
            private int salesAmount;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
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
        private List<GetGenderRes.Result> results;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }


        public List<GetGenderRes.Result> getResults() {
            return results;
        }

        public void setResults(List<GetGenderRes.Result> results) {
            this.results = results;
        }
    }

    // 월별 매출 통계 응답 DTO
    public static class GetMonthRes {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("salesDate")
        @Expose
        private String salesDate;
        @SerializedName("salesAmount")
        @Expose
        private int salesAmount;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

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

    // 서버 응답 DTO
    public static class StatusRes {

        @SerializedName("status")
        @Expose
        private int status;
    }
}
