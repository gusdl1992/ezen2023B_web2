package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "boardimg")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BoardImgEntity extends BaseTime{


    @Id
    private String bimg;   // 파일명 ( 중복없다. )

    // 단방향 설정
    @JoinColumn( name="bno_fk")
    @ManyToOne
    private BoardEntity boardEntity;
}
