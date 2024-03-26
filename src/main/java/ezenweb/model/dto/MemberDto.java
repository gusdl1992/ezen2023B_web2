package ezenweb.model.dto;

import ezenweb.model.entity.MemberEntity;
import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class MemberDto {

    private int mno;
    private String memail;
    private String mpassword;
    private String mname;
    private String mrol;

    // - dto를 엔티티로 변환하는 메소드 // 되야되는 이유 저장 C
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mno(this.mno)
                .mname(this.mname)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mrol(this.mrol)
                .build();
        // this ?? : 해당 메소드를 호출한 인스턴스
    }

}
