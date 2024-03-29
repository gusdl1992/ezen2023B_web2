package ezenweb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseTimeDto {
    public LocalDateTime cdate;        // 작성일
    public LocalDateTime udate;        // 수정일
}
