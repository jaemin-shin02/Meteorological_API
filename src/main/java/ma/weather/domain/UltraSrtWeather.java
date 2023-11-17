package ma.weather.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class UltraSrtWeather {
    @Id @GeneratedValue
    @Column(name = "usWeather_id")
    private Long id;

    private String baseDate;
    private String baseTime;
    private String fcstDate;
    private String fcstTime;

    @OneToMany(mappedBy = "ultraSrtWeather")
    private List<WeatherInfo> weatherInfoList = new ArrayList<>();

    public static UltraSrtWeather create(String baseDate, String baseTime, String fcstDate, String fcstTime) {
        UltraSrtWeather ultraSrtWeather = new UltraSrtWeather();
        ultraSrtWeather.setBaseDate(baseDate);
        ultraSrtWeather.setBaseTime(baseTime);
        ultraSrtWeather.setFcstDate(fcstDate);
        ultraSrtWeather.setFcstTime(fcstTime);

        return ultraSrtWeather;
    }
}
