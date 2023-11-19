package ma.weather.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShortWeatherControllerTest {

    private int port=8080;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetShortWeatherEndpoint() {
        String url = "http://localhost:" + port + "/shortWeather";

        // 테스트를 위한 실제 API 호출
        String response = restTemplate.getForObject(url, String.class);

        // 실제 API 응답이 예상된 형식인지 확인
        assertThat(response).contains("fcstDate", "fcstTime", "tmp", "uuu", "vvv", "vec", "wsd", "sky", "pty", "pop", "wav", "pcp", "reh", "sno");
    }
}