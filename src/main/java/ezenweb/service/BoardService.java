package ezenweb.service;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import ezenweb.model.repository.ReplyEntityRepositoty;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardService {

    @Autowired
    private BoardEntityRepository boardEntityRepository;
    @Autowired
    private MemberEntityRepository memberEntityRepository;
    @Autowired
    private ReplyEntityRepositoty replyEntityRepositoty;

    // 1. C
    @Transactional
    public boolean postBoard(){

        // ========== 테스트 =========
        // 1. 회원가입
            // 1. 엔티티 객체 생성
        MemberEntity memberEntity = MemberEntity.builder()
                .memail("qwe@qwe.com")
                .mpassword("1234")
                .mname("유재석")
                .build();
            // 2. 해당 엔티티를 DB에 저장할수 있도록 조작
        MemberEntity saveMemberEntity =  memberEntityRepository.save(memberEntity);
        System.out.println("saveMemberEntity = " + saveMemberEntity);

        // 2. 회원가입된 회원으로 글쓰기
            // 1. 엔티티 객체 생성
        BoardEntity boardEntity= BoardEntity.builder()
                .bcontent("게시물글입니다.")
                .build();
            // 2. ********** 글쓴이[pk 대입]
        boardEntity.setMemberEntity(saveMemberEntity);
            // 3. 해당 엔티티를 DB에 저장할수 있도록 조작
        BoardEntity saveBoardEntity = boardEntityRepository.save(boardEntity);

        // 3. 해당 글에 댓글 작성
            // 1. 엔티티 객체 생성
        ReplyEntity replyEntity = ReplyEntity.builder()
                .rcontent("댓글입니다.")
                .build();
            // 2. ********** FK대입 1 : 작성자
        replyEntity.setMemberEntity(saveMemberEntity);
            // 2. ********** FK대입 2 : 게시물번호
        replyEntity.setBoardEntity(saveBoardEntity);
            // 3. 해당 엔티티를 DB에 저장 할수 있도록 조작
        replyEntityRepositoty.save(replyEntity);

        return false;
    }
    // 2. R
    @Transactional
    public List<Object> getBoard(){
        // 1. 리포지토리를 이용한 모든 엔티티를 호출
        List<BoardEntity> result = boardEntityRepository.findAll();
        System.out.println("result = " + result);
        System.out.println("작성자 = " + result.get(0).getMemberEntity().getMemail() );
        return null;
    }
    // 3. U
    @Transactional
    public boolean putBoard(){
        BoardEntity boardEntity = boardEntityRepository.findById(1).get();
        boardEntity.setBcontent("JPA수정테스트중");
        return false;
    }
    // 4. D
    @Transactional
    public boolean deleteBoard(){
        boardEntityRepository.deleteById(1);
        return false;
    }

}
