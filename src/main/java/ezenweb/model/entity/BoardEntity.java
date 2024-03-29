package ezenweb.model.entity;


import ezenweb.model.dto.BoardDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity // 해당 클래스와 연동DB내 테이블과 매핑/연결
@Table( name = "board")
@NoArgsConstructor@AllArgsConstructor
@Setter @Getter @ToString @Builder
public class BoardEntity extends BaseTime{ // 테이블

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;

    @Column(columnDefinition = "longtext")
    private String bcontent;

    @Column
    @ColumnDefault("0")
    private int bview;

    // FK 필드 (단방향)
    @JoinColumn(name = "mno_fk") // fk 필드명
    @ManyToOne  // 해당 필드 참조
    private MemberEntity memberEntity;


    // 양방향 : 댓글 FK
    @OneToMany(mappedBy = "boardEntity")
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

    // - 게시물 출력
    public BoardDto toDto(){
        return BoardDto.builder()
                .bno(this.bno)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .mno_fk(memberEntity.getMno() )
                .memail(memberEntity.getMemail() )
                .cdate(this.getCdate())
                .udate(this.getUdate())
                .build();
    }

}

/* 새로 만들기전 코드
    @Id // PK
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // auto_increment
    private int bno;        // 게시물번호 PK

    private String btitle;
    @JoinColumn // fk
    @ManyToOne  // 다수가 하나에게     M:1
    private MemberEntity memberEntity;

*/


/*
    create table BoardEntitiy(
        bno int ,
        btitle varchar(255)
    )


    //    @Column(name = "title", length = 10 , nullable = false , unique = true )
//    private String btitle; // 게시물제목
//
//    @Column(columnDefinition = "longtext")
//    private String btitle2;
//
//    @Column(columnDefinition = "date")
//    private String btitle3;
//
//    private boolean 필드0;
//
//    private byte 필드1;
//    private short 필드2;
//    private long 필드3;
//
//    private char 필드4;
//
//    private double 필드5;
//    private float 필드6;
//
//    private Date 필드7;
//    private LocalDateTime 필드8;
//
//    @Column( columnDefinition = "unsigned int(11)" )
//    private int 필드9;


*/



