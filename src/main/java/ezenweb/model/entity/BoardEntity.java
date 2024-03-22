package ezenweb.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity // 해당 클래스와 연동DB 내 테이블과 매핑
@Table(name = "board")
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity {  // 테이블
    @Id // PK
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // auto_increment
    private int bno;        // 게시물번호 PK
    @Column(name = "title", length = 10 , nullable = false , unique = true )
    private String btitle; // 게시물제목

    @Column(columnDefinition = "longtext")
    private String btitle2;

    @Column(columnDefinition = "date")
    private String btitle3;

    private boolean 필드0;

    private byte 필드1;
    private short 필드2;
    private long 필드3;

    private char 필드4;

    private double 필드5;
    private float 필드6;

    private Date 필드7;
    private LocalDateTime 필드8;

    @Column( columnDefinition = "int(11) unsigned" )
    private int 필드9;

}
/*
    create table BoardEntitiy(
        bno int ,
        btitle varchar(255)
    )
*/





