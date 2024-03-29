package ezenweb.model.dto;

import ezenweb.model.entity.BoardEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
@SuperBuilder
public class BoardDto extends BaseTimeDto{

    private int bno;
    private String bcontent;
    private int bview;
    private int mno_fk;         // (memberEntity)회원번호
    private String memail;      // (memberEntity)회원 이메일



    // - 글쓰기
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bcontent(this.bcontent)
                .build();
    }
}
