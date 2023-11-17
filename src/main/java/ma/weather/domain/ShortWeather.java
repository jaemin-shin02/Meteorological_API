package ma.weather.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ShortWeather {
    @Id @GeneratedValue
    @Column(name = "shortWeather_id")
    private Long id;

    private String baseDate;
    private String baseTime;
    private String fcstDate;
    private String fcstTime;

    @OneToMany(mappedBy = "shortWeather")
    private List<WeatherInfo> weatherInfoList = new ArrayList<>();

    public static ShortWeather create(String baseDate, String baseTime, String fcstDate, String fcstTime) {
        ShortWeather shortWeather = new ShortWeather();
        shortWeather.setBaseDate(baseDate);
        shortWeather.setBaseTime(baseTime);
        shortWeather.setFcstDate(fcstDate);
        shortWeather.setFcstTime(fcstTime);

        return shortWeather;
    }

}

// 한 번 조회시 24시간 즉, 24건 조회
// 단기 예보의 경우 매 일 05시에 초기화 되기 때문