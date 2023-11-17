package ma.weather.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class WeatherInfo {

    @Id @GeneratedValue
    private Long id;

    private String category;
    private String value;

    private int nx;
    private int ny;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shortWeather_id")
    private ShortWeather shortWeather;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usWeather_id")
    private UltraSrtWeather ultraSrtWeather;

    //==연관관계 편의 메서드==//
    public void setShortWeather(ShortWeather shortWeather){
        this.shortWeather = shortWeather;
        shortWeather.getWeatherInfoList().add(this);
    }
    public void setUltraSrtWeather(UltraSrtWeather ultraSrtWeather){
        this.ultraSrtWeather = ultraSrtWeather;
        ultraSrtWeather.getWeatherInfoList().add(this);
    }

    public static WeatherInfo create(String category, String value, int nx, int ny) {
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setCategory(category);
        weatherInfo.setValue(value);
        weatherInfo.setNx(nx);
        weatherInfo.setNy(ny);

        return weatherInfo;
    }

}
