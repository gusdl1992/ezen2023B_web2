package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "member")
@NoArgsConstructor@AllArgsConstructor
@Setter @Getter @ToString @Builder
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;
    @Column(length = 50 , unique = true)
    private String memail;

    @Column(length = 30)
    private String mpassword;

    @Column(length = 20 , nullable = false)
    private String mname;

    @Column(name = "mrol")
    @ColumnDefault("'user'") // 문자 '' , 숫자 // nullable = false 디폴트가 적용이안됬다...
    private String mrol;


    // 양방향 : 게시물 FK   ** @OneToMany(mappedBy = "해당테이블 fk 자바필드명" )
    @OneToMany(mappedBy = "memberEntity" ) // 자바에서만 양방향
    @ToString.Exclude // 해당 객체 호출시 해당 필드는 호출하지 않는다.
    @Builder.Default  // 빌더패턴 사용해서 객체 생성시 해당 필드의 초기값을 빌더 초기값으로 사용.
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    // 양방향 : 댓글 FK
    @OneToMany(mappedBy = "memberEntity" )
    @ToString.Exclude // 해당 객체 호출시 해당 필드는 호출하지 않는다.
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();


}

/* 새로 만들기전 코드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;    // 회원 번호 pk

    private String mid;

    @ToString.Exclude // 객체 호출시 해당 필드 제외
    @OneToMany( mappedBy = "memberEntity") // 하나가 다수에게 1:M
    private List<BoardEntity> boardEntityList
            = new ArrayList<>();

*/