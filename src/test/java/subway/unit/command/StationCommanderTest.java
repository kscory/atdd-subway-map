package subway.unit.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import subway.domain.command.StationCommander;
import subway.domain.entity.station.Station;
import subway.domain.repository.StationRepository;
import subway.setup.BaseTestSetup;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class StationCommanderTest extends BaseTestSetup {

    @Autowired
    private StationCommander sut;

    @Autowired
    private StationRepository stationRepository;

    @Nested
    @DisplayName("createStation")
    class CreateLineTest {
        @ParameterizedTest
        @ValueSource(strings = {"강남역", "역삼역", "삼성역"})
        public void sut_creates_station(String name) {
            // when
            Long id = sut.createStation(name);

            // then
            Station actual = stationRepository.findByIdOrThrow(id);
            assertThat(actual.getName()).isEqualTo(name);
        }
    }

    @Nested
    @DisplayName("deleteStationById")
    class DeleteStationByIdTest {
        @ParameterizedTest
        @ValueSource(strings = {"강남역", "역삼역", "삼성역"})
        public void sut_delete_station(String name) {
            // given
            Long id = sut.createStation(name);

            // when
            sut.deleteStationById(id);

            // then
            Optional<Station> actual = stationRepository.findById(id);
            assertThat(actual).isEmpty();
        }
    }
}
