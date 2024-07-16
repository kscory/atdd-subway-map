package subway.unit.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import subway.domain.entity.station.Station;
import subway.domain.exception.SubwayDomainException;
import subway.domain.exception.SubwayDomainExceptionType;
import subway.domain.repository.StationRepository;
import subway.setup.BaseTestSetup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class StationRepositoryTest extends BaseTestSetup {
    @Autowired
    private StationRepository sut;

    @Nested
    @DisplayName("findByIdOrThrow")
    class FindByIdOrThrow {
        @Test
        public void sut_return_station() {
            // given
            Station station = sut.save(new Station("서울역"));

            transactionTemplate.execute(status -> {
                // when
                Station actual = sut.findByIdOrThrow(station.getId());

                // then
                assertThat(actual).usingRecursiveComparison().isEqualTo(station);

                return null;
            });
        }

        @Test
        public void sut_throws_if_not_found_station() {
            // when
            SubwayDomainException actual = (SubwayDomainException) catchThrowable(() -> sut.findByIdOrThrow(123123L));

            // then
            assertThat(actual.getExceptionType()).isEqualTo(SubwayDomainExceptionType.NOT_FOUND_STATION);
        }
    }
}
