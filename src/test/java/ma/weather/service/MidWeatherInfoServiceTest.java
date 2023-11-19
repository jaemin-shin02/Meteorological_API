package ma.weather.service;

import ma.weather.domain.MidWeather;
import ma.weather.domain.MidWeatherInfo;
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
class MidWeatherInfoServiceTest {

    @Autowired
    private MidWeatherService midWeatherService;
    @Autowired
    private MidWeatherInfoService midWeatherInfoService;

    @Test
    public void saveAndFind() throws Exception {
        //given
        MidWeatherInfo midWeatherInfo = MidWeatherInfo.create(3, 3, 2, 4, 11, 10, 13);
        Long save = midWeatherInfoService.save(midWeatherInfo);

        //when
        MidWeatherInfo findOne = midWeatherInfoService.findOne(midWeatherInfo.getId());

        //then
        assertEquals("저장된 ID가 같아야 한다.", save, midWeatherInfo.getId());
        assertEquals("저장된 midWeatherInfo 찾은 midWeatherInfo가 같아야 한다.", midWeatherInfo, findOne);
        assertEquals("저장된 값이 불러온 값과 같아야 한다.", 3, findOne.getDay());
    }

    @Test
    public void findByMidWeatherId() throws Exception {
        //given
        MidWeather midWeather1 = MidWeather.create("11B10101");
        Long save1 = midWeatherService.save(midWeather1);
        MidWeather midWeather2 = MidWeather.create("11B10102");
        Long save2 = midWeatherService.save(midWeather2);

        for(int i=0; i<10; i++){
            MidWeatherInfo midWeatherInfo = MidWeatherInfo.create(3+i, 3+i, 2+i, 4+i, 11+i, 10+i, 13+i);
            midWeatherInfo.setMidWeather(midWeather1);
            Long save = midWeatherInfoService.save(midWeatherInfo);
        }
        for(int i=0; i<7; i++){
            MidWeatherInfo midWeatherInfo = MidWeatherInfo.create(3-i, 3-i, 2-i, 4-i, 11-i, 10-i, 13-i);
            midWeatherInfo.setMidWeather(midWeather2);
            Long save = midWeatherInfoService.save(midWeatherInfo);
        }
        //when
        List<MidWeatherInfo> byMidWeatherId = midWeatherInfoService.findByMidWeatherId(save1);

        //then
        assertEquals("조회된 MidWeatherInfo의 수는 10개", 10, byMidWeatherId.size());
        assertEquals("조회된 MidWeatherInfo의 MidWeather는 11B10101", "11B10101", byMidWeatherId.get(0).getMidWeather().getRegId());
        assertEquals("midWeather의 infoList는 10개", 10, midWeather1.getMidWeatherInfoList().size());
    }


}