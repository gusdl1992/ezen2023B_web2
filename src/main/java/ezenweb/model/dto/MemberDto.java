package ezenweb.model.dto;

import ezenweb.model.entity.MemberEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
public class MemberDto extends BaseTimeDto{

    private int mno;
    private String memail;
    private String mpassword;
    private String mname;
    private String mrol;


    // - dto를 엔티티로 변환하는 메소드 // 되야되는 이유 저장 C
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mname(this.mname)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .build();
        // this ?? : 해당 메소드를 호출한 인스턴스
    }

}
