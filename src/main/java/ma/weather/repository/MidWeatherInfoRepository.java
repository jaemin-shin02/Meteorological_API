package ma.weather.repository;

import ma.weather.domain.MidWeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MidWeatherInfoRepository extends JpaRepository<MidWeatherInfo, Long> {
    List<MidWeatherInfo> findByMidWeatherId(Long id);
}
