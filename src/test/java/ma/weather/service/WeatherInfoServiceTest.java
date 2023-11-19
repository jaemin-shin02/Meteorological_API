package ma.weather.service;

import ma.weather.domain.ShortWeather;
import ma.weather.domain.UltraSrtWeather;
import ma.weather.domain.WeatherInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class WeatherInfoServiceTest {

    @Autowired
    private ShortWeatherService shortWeatherService;
    @Autowired
    private UltraSrtWeatherService ultraSrtWeatherService;
    @Autowired
    private WeatherInfoService weatherInfoService;

    @Test
    public void saveAndFind() throws Exception {
        //given
        WeatherInfo weatherInfo = WeatherInfo.create("TMP", "2", 55, 127);
        Long save = weatherInfoService.save(weatherInfo);

        //when
        WeatherInfo findOne = weatherInfoService.findOne(save);

        //then
        assertEquals("저장된 ID가 같아야 한다.", save, weatherInfo.getId());
        assertEquals("저장된 ShortWeather과 찾은 ShortWeather이 같아야 한다.", weatherInfo, findOne);
        assertEquals("저장된 값이 불러온 값과 같아야 한다.", "TMP", findOne.getCategory());
        assertEquals("저장된 값이 불러온 값과 같아야 한다.", "2", findOne.getValue());
        assertEquals("저장된 값이 불러온 값과 같아야 한다.", 55, findOne.getNx());
        assertEquals("저장된 값이 불러온 값과 같아야 한다.", 127, findOne.getNy());
    }

    @Test
    public void findByShortWeather() throws Exception {
        //given
        ShortWeather shortWeather = ShortWeather.create("20231119", "0500", "20231119", "0600");
        Long save = shortWeatherService.save(shortWeather);

        for(int i=0;i<10;i++){
            WeatherInfo weatherInfo = WeatherInfo.create("TMP", "2"+i, 55, 127);
            weatherInfo.setShortWeather(shortWeather);
            Long save1 = weatherInfoService.save(weatherInfo);
        }

        //when
        List<WeatherInfo> bySrtWeatherId = weatherInfoService.findBySrtWeatherId(save);

        //then
        assertEquals("조회된 WeatherInfo의 수는 10개", 10, bySrtWeatherId.size());
        assertEquals("조회된 WeatherInfo의 ShortWeather의 값", "20231119", bySrtWeatherId.get(0).getShortWeather().getFcstDate());
        assertEquals("조회된 WeatherInfo의 ShortWeather의 값", "0600", bySrtWeatherId.get(0).getShortWeather().getFcstTime());
        assertEquals("ShortWeather의 infoList는 10개", 10, shortWeather.getWeatherInfoList().size());

    }

    @Test
    public void findByUltraSrtWeather() throws Exception {
        //given
        UltraSrtWeather ultraSrtWeather = UltraSrtWeather.create("20231119", "0500", "20231119", "0600");
        Long save = ultraSrtWeatherService.save(ultraSrtWeather);

        for(int i=0;i<10;i++){
            WeatherInfo weatherInfo = WeatherInfo.create("TMP", "2"+i, 55, 127);
            weatherInfo.setUltraSrtWeather(ultraSrtWeather);
            Long save1 = weatherInfoService.save(weatherInfo);
        }

        //when
        List<WeatherInfo> bySrtWeatherId = weatherInfoService.findByUltraSrtWeatherId(save);

        //then
        assertEquals("조회된 WeatherInfo의 수는 10개", 10, bySrtWeatherId.size());
        assertEquals("조회된 WeatherInfo의 UltraSrtWeather의 값", "20231119", bySrtWeatherId.get(0).getUltraSrtWeather().getFcstDate());
        assertEquals("조회된 WeatherInfo의 UltraSrtWeather의 값", "0600", bySrtWeatherId.get(0).getUltraSrtWeather().getFcstTime());
        assertEquals("UltraSrtWeather의 infoList는 10개", 10, ultraSrtWeather.getWeatherInfoList().size());
    }

}