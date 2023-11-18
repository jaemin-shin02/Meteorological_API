package ma.weather.service;

import ma.weather.domain.MidWeather;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MidWeatherServiceTest {

    @Autowired
    private MidWeatherService midWeatherService;

    @Test
    public void saveAndFindOne() throws Exception {
        //given
        MidWeather midWeather = MidWeather.create("11B10101");
        //when
        Long save = midWeatherService.save(midWeather);
        MidWeather one = midWeatherService.findOne(save);

        //then
        assertEquals("저장된 ID가 같아야 한다.", save, midWeather.getId());
        assertEquals("저장된 ShortWeather과 찾은 ShortWeather이 같아야 한다.", midWeather, one);
        assertEquals("저장된 값이 불러온 값과 같아야 한다.", "11B10101", one.getRegId());
    }

    @Test
    public void findByRegId() throws Exception {
        //given
        MidWeather midWeather1 = MidWeather.create("11B10101");
        MidWeather midWeather2 = MidWeather.create("11B10102");
        MidWeather midWeather3 = MidWeather.create("11B10103");

        midWeatherService.save(midWeather1);
        midWeatherService.save(midWeather2);
        midWeatherService.save(midWeather3);
        //when
        MidWeather byRegId = midWeatherService.findByRegId("11B10101");

        //then
        assertEquals("찾은 midWeather와 생성했던 midWeather가 같아야 한다.", midWeather1, byRegId);
    }

}