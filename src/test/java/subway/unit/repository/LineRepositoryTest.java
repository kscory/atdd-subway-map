package subway.unit.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import subway.domain.entity.line.Line;
import subway.domain.exception.SubwayDomainException;
import subway.domain.exception.SubwayDomainExceptionType;
import subway.domain.repository.LineRepository;
import subway.fixtures.LineFixture;
import subway.setup.BaseTestSetup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class LineRepositoryTest extends BaseTestSetup {

    @Autowired
    LineRepository sut;

    @Nested
    @DisplayName("findByIdOrThrow")
    class FindByIdOrThrow {
        @Test
        public void sut_return_line() {
            // given
            Line line = sut.save(LineFixture.prepareRandom(1L, 2L));

            transactionTemplate.execute(status -> {
                // when
                Line actual = sut.findByIdOrThrow(line.getId());

                // then
                assertThat(actual).usingRecursiveComparison().isEqualTo(line);

                return null;
            });
        }

        @Test
        public void sut_throws_if_not_found_line() {
            // when
            SubwayDomainException actual = (SubwayDomainException) catchThrowable(() -> sut.findByIdOrThrow(123123L));

            // then
            assertThat(actual.getExceptionType()).isEqualTo(SubwayDomainExceptionType.NOT_FOUND_LINE);
        }
    }
}