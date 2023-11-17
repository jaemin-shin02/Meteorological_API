package ma.weather.service;

import lombok.RequiredArgsConstructor;
import ma.weather.domain.MidWeatherInfo;
import ma.weather.repository.MidWeatherInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MidWeatherInfoService {
    private final MidWeatherInfoRepository midWeatherInfoRepository;

    public Long save(MidWeatherInfo midWeatherInfo){
        MidWeatherInfo save = midWeatherInfoRepository.save(midWeatherInfo);
        return save.getId();
    }

    public MidWeatherInfo findOne(Long id){
        return midWeatherInfoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 날씨 상세 정보가 없습니다.")
        );
    }

    public List<MidWeatherInfo> findByMidWeatherId(Long id){
        return midWeatherInfoRepository.findByMidWeatherId(id);
    }
}
