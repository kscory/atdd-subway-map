package subway.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SubwayDomainExceptionType {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR", "internal server error"),
    NOT_FOUND_STATION(HttpStatus.NOT_FOUND.value(), "NOT_FOUND_STATION", "not found station"),
    NOT_FOUND_LINE(HttpStatus.NOT_FOUND.value(), "NOT_FOUND_LINE", "not found line"),
    INVALID_UP_STATION(HttpStatus.BAD_REQUEST.value(), "INVALID_UP_STATION", "invalid up-station"),
    INVALID_DOWN_STATION(HttpStatus.BAD_REQUEST.value(), "INVALID_DOWN_STATION", "invalid down-station"),
    INVALID_STATION(HttpStatus.BAD_REQUEST.value(), "INVALID_STATION", "invalid station"),
    INVALID_SECTION_SIZE(HttpStatus.BAD_REQUEST.value(), "INVALID_SECTION_SIZE", "invalid section size"),
    ;

    private final int status;
    private final String name;
    private final String message;

    SubwayDomainExceptionType(int status, String name, String message) {
        this.status = status;
        this.name = name;
        this.message = message;
    }
}
