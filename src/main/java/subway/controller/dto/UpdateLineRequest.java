package subway.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import subway.domain.command.LineCommand;

@ToString
@Getter
@AllArgsConstructor
public class UpdateLineRequest {
    private String name;
    private String color;
    private Long upStationId;
    private Long downStationId;
    private Long distance;

    public LineCommand.UpdateLine toCommand(Long id) {
        return new LineCommand.UpdateLine(id, name, color, upStationId, downStationId, distance);
    }
}