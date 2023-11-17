package ma.weather.repository;

import ma.weather.domain.MidWeather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MidWeatherRepository extends JpaRepository<MidWeather, Long> {
    MidWeather findByRegId(String regId);
}
