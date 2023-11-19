package ma.weather.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeatherIntegrationTest {

    private int port=8080;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetUltraSrtWeatherEndpoint() {
        String url = "http://localhost:" + port + "/midWeather";

        // 테스트를 위한 실제 API 호출
        String response = restTemplate.getForObject(url, String.class);

        // 실제 API 응답이 예상된 형식인지 확인
        assertThat(response).contains("regId", "day", "taMin", "taMinLow", "taMinHigh", "taMax", "taMaxLow", "taMaxHigh");
    }
}


