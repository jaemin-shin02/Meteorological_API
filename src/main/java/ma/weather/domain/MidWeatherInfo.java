package ma.weather.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MidWeatherInfo {
    @Id
    @GeneratedValue
    private Long id;

    private int day;

    private int taMin;

    private int taMinLow;

    private int taMinHigh;

    private int taMax;

    private int taMaxLow;

    private int taMaxHigh;


    @ManyToOne
    @JoinColumn(name = "midWeather_id")
    private MidWeather midWeather;

    public void setMidWeather(MidWeather midWeather){
        this.midWeather = midWeather;
        midWeather.getMidWeatherInfoList().add(this);
    }

    public static MidWeatherInfo create(int day, int taMin, int taMinLow, int taMinHigh, int taMax, int taMaxLow, int taMaxHigh){
        MidWeatherInfo midWeatherInfo = new MidWeatherInfo();
        midWeatherInfo.setDay(day);
        midWeatherInfo.setTaMin(taMin);
        midWeatherInfo.setTaMinLow(taMinLow);
        midWeatherInfo.setTaMinHigh(taMinHigh);
        midWeatherInfo.setTaMax(taMax);
        midWeatherInfo.setTaMaxLow(taMaxLow);
        midWeatherInfo.setTaMaxHigh(taMaxHigh);

        return midWeatherInfo;
    }
}
