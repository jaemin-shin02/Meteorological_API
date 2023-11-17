package ma.weather.service;

import ma.weather.domain.*;
import ma.weather.dto.MidWeatherResponse;
import ma.weather.dto.WeatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private MidWeatherService midWeatherService;
    @Autowired
    private ShortWeatherService shortWeatherService;
    @Autowired
    private UltraSrtWeatherService ultraSrtWeatherService;

    @Rollback(value = true)
    @Test
    public void shortWeather() throws Exception {
        //given
        WeatherResponse shortTermForecast = weatherService.getShortTermForecast("20231116", "0500", "60", "127");
        //when
        List<ShortWeather> all = shortWeatherService.findAll();
        ShortWeather shortWeather = all.get(0);
        List<WeatherInfo> weatherInfoList = shortWeather.getWeatherInfoList();
        //then
        assertEquals("WeatherResponse의 아이템 갯수는 289개", 289, shortTermForecast.getResponse().getBody().getItems().getItem().size());
        assertEquals("WeatherResponse의 DataType은 JSON", "JSON", shortTermForecast.getResponse().getBody().getDataType());
        assertEquals("저장된 short의 개수는 실행 1번당 24", 24, all.size());
        assertEquals("저장된 Short의 상세 정보 개수는 12", 12, weatherInfoList.size());
    }

    @Rollback(value = true)
    @Test
    public void UltSrtWeather() throws Exception {
        //given
        WeatherResponse mShortTermForecast = weatherService.getMShortTermForecast("20231116", "0500", "60", "127");
        //when
        List<UltraSrtWeather> all = ultraSrtWeatherService.findAll();
        UltraSrtWeather ultraSrtWeather = all.get(0);
        List<WeatherInfo> weatherInfoList = ultraSrtWeather.getWeatherInfoList();
        //then
        assertEquals("WeatherResponse의 아이템 갯수는 60개", 60, mShortTermForecast.getResponse().getBody().getItems().getItem().size());
        assertEquals("WeatherResponse의 DataType은 JSON", "JSON", mShortTermForecast.getResponse().getBody().getDataType());
        assertEquals("저장된 UltraShort의 개수는 실행 1번당 6", 6, all.size());
        assertEquals("저장된 UltraShort의 상세 정보 개수는 10", 10, weatherInfoList.size());

    }

    @Rollback(value = true)
    @Test
    public void midWeather() throws Exception {
        //given
        MidWeatherResponse midTa = weatherService.getMidTa("11B10101", "202311170600");
        //when
        List<MidWeather> all = midWeatherService.findAll();
        MidWeather midWeather = all.get(0);
        List<MidWeatherInfo> midWeatherInfoList = midWeather.getMidWeatherInfoList();
        //then
        assertEquals("midTa의 아이템 갯수는 1개", 1, midTa.getResponse().getBody().getItems().getItem().size());
        assertEquals("WeatherResponse의 DataType은 JSON", "JSON", midTa.getResponse().getBody().getDataType());
        assertEquals("저장된 mid의 개수는 실행 1번당 1", 1, all.size());
        assertEquals("저장된 midWeather의 상세 정보 개수는 8", 8, midWeatherInfoList.size());

    }
}