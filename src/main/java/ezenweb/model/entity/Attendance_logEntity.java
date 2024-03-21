package ezenweb.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance_logEntity {

    @Id // pk 임시
    private int num;
    @Column(columnDefinition = "date")
    private Date jday;
    @Column(columnDefinition = "time")
    private LocalTime stat_time;
    @Column(columnDefinition = "time")
    private LocalTime end_time;
    @Column(columnDefinition = "time")
    private LocalTime working_time;

    @Column(columnDefinition = "varchar(20)")
    private String jip;
    @Column(columnDefinition = "varchar(255)")
    private String jnote; //255 최대

}


/*
    # 출퇴근 DB 설계
drop table if exists attendance_log;
create table attendance_log(
   jday date,
    stat_time time ,
    end_time time ,
    working_time time,
    jip varchar(20) ,
    jnote varchar(255) ,
    eno int ,
    constraint employee_eno_fk foreign key(eno) references employee(eno)on delete cascade
);


*/