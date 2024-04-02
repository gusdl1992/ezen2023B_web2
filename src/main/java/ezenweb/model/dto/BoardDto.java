package ezenweb.model.dto;

import ezenweb.model.entity.BoardEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    // 1. 출력용 게시물 이미지 필드 ( 파일이름만 여러개 출력하면 되서  SPRING --> JS )
    private List<String> bimgList = new ArrayList<>();
    // 2. 등록용 게시물 이미지 필드 ( JS ---Multipart/Form(바이트) ---> SPRING )
    private List<MultipartFile> uploadList = new ArrayList<>();

    // - 글쓰기
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bcontent(this.bcontent)
                .build();
    }
}
