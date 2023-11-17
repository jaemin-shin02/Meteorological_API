package ma.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {
    @JsonProperty("response")
    private Response response;

    @Data
    public static class Response {
        private Header header;
        private Body body;

        // Getter, Setter, toString 등 필요한 메서드 추가
    }
    @Data
    public static class Header {
        private String resultCode;
        private String resultMsg;

        // Getter, Setter, toString 등 필요한 메서드 추가
    }
    @Data
    public static class Body {
        private String dataType;
        private Items items;
        private int pageNo;
        private int numOfRows;
        private int totalCount;

        // Getter, Setter, toString 등 필요한 메서드 추가
    }
    @Data
    public static class Items {
        private List<Item> item;

        // Getter, Setter, toString 등 필요한 메서드 추가
    }
    @Data
    public static class Item {
        private String baseDate;
        private String baseTime;
        private String category;
        private String fcstDate;
        private String fcstTime;
        private String fcstValue;
        private int nx;
        private int ny;

        // Getter, Setter, toString 등 필요한 메서드 추가
    }

    // Getter, Setter, toString 등 필요한 메서드 추가
}
