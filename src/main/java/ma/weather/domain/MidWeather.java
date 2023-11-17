package ma.weather.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class MidWeather {
    @Id @GeneratedValue
    @Column(name = "midWeather_id")
    private Long id;

    private String regId;

    @OneToMany(mappedBy = "midWeather", cascade = CascadeType.ALL)
    private List<MidWeatherInfo> midWeatherInfoList = new ArrayList<>();

    public static MidWeather create(String regId){
        MidWeather midWeather = new MidWeather();
        midWeather.setRegId(regId);

        return midWeather;
    }

}
