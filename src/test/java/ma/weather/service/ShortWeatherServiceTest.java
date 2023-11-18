package ma.weather.service;

import ma.weather.domain.ShortWeather;
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
class ShortWeatherServiceTest {

    @Autowired
    private ShortWeatherService shortWeatherService;

    @Test
    public void saveAndFindOne() throws Exception {
        //given
        ShortWeather shortWeather = ShortWeather.create("20231118", "0500", "20231118", "0600");

        //when
        Long save = shortWeatherService.save(shortWeather);
        ShortWeather one = shortWeatherService.findOne(save);

        //then
        assertEquals("저장된 ID가 같아야 한다.", save, shortWeather.getId());
        assertEquals("저장된 ShortWeather과 찾은 ShortWeather이 같아야 한다.", shortWeather, one);
        assertEquals("저장된 값이 불러온 값과 같아야 한다.", "20231118", one.getBaseDate());
    }

    @Test
    public void findByBaseDateAndTime() throws Exception {
        //given
        ShortWeather shortWeather1 = ShortWeather.create("20231118", "0500", "20231118", "0600");
        ShortWeather shortWeather2 = ShortWeather.create("20231119", "0500", "20231119", "0600");
        ShortWeather shortWeather3 = ShortWeather.create("20231118", "0600", "20231118", "0600");

        shortWeatherService.save(shortWeather1);
        shortWeatherService.save(shortWeather2);
        shortWeatherService.save(shortWeather3);
        //when
        List<ShortWeather> byBaseDateAndTime = shortWeatherService.findByBaseDateAndTime("20231118", "0500");

        //then
        assertEquals("findByBaseDateAndTime로 찾은 List의 size는 1", 1, byBaseDateAndTime.size());
        assertEquals("리스트의 첫 요소의 fcstDate의 경우 20231118이여야 한다.", "20231118", byBaseDateAndTime.get(0).getFcstDate());
        assertEquals("리스트의 첫 요소의 fcstTime의 경우 0600이여야 한다.", "0600", byBaseDateAndTime.get(0).getFcstTime());
    }
}