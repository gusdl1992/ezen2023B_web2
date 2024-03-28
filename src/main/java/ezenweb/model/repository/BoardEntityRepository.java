package ezenweb.model.repository;

import ezenweb.model.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 매핑된 테이블의 엔티티/레코드 들을 조작/관리 하는 리모콘/인터페이스 역할 , 빈 등록
public interface BoardEntityRepository extends JpaRepository<BoardEntity , Integer> {
    BoardEntity findByBno(int bno);
    // 보더 서비스 에서
    // boardEntityRepository.findById(1); 이용하여 값 가져올수도 있다
}
