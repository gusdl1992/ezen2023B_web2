package ezenweb.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // 해당 클래스와 연동DB 내 테이블과 매핑
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity {  // 테이블
    @Id
    @Column(name = "게시물번호")
    private int bno;            // 게시물 번호
    @Column(columnDefinition = "text" , name = "제목") // DB 타입을 변경 할수 있다.
    private String btitle;      // 제목

}





