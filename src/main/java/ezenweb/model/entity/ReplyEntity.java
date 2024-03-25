package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reply")
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter @ToString @Builder
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;

    private String rcontent;

    // FK 필드 ( 단방향 )
    @JoinColumn(name = "bno_fk") // FK 필드명
    @ManyToOne  // 해당 필드 참조
    private BoardEntity boardEntity;

    // FK 필드 ( 단방향 )
    @JoinColumn(name = "mno_fk") // FK 필드명
    @ManyToOne  // 해당 필드 참조
    private MemberEntity memberEntity;
}
