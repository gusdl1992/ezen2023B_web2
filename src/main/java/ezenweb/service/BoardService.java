package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.BoardImgEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.BoardImgeEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import ezenweb.model.repository.ReplyEntityRepositoty;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BoardService {

    @Autowired
    private BoardEntityRepository boardEntityRepository;
    @Autowired
    private MemberEntityRepository memberEntityRepository;
    @Autowired
    private ReplyEntityRepositoty replyEntityRepositoty;
    @Autowired
    private MemberService memberService;
    @Autowired
    private FileService fileService;
    @Autowired
    private BoardImgeEntityRepository boardImgeEntityRepository;

    // 1. C
    @Transactional
    public boolean postBoard(BoardDto boardDto){




        // ========== 테스트 =========
        // 0. 회원 번호 찾기
        MemberDto loginDto = memberService.doLoginInfo();
        if (loginDto == null) return false;

        // 1. 로그인된 회원 엔티티 찾기
        Optional<MemberEntity> optionalMemberEntity =
        memberEntityRepository.findById(loginDto.getMno());
        // 2. 찾은 엔티티가 존재하지 않으면
        if (!optionalMemberEntity.isPresent()) return false;
        // 3. 엔티티 꺼내기
        MemberEntity memberEntity = optionalMemberEntity.get();
        // - 글쓰기
        BoardEntity saveBoard = boardEntityRepository.save(boardDto.toEntity());


        // - FK 대입
        if (saveBoard.getBno() >= 1){ // 글쓰기를 성공 했으면
            saveBoard.setMemberEntity(memberEntity);
        }

        // 파일 업로드 테스트
        if ( !boardDto.getUploadList().isEmpty() ){ // 첨부파일이 존재하면
            for (int i = 0 ; i < boardDto.getUploadList().size() ; i++){
                String fileName = fileService.fileUpload(boardDto.getUploadList().get(i));
                // save 빈객체 생성
                BoardImgEntity boardImgEntity = BoardImgEntity.builder()
                        .bimg(fileName)
                        .boardEntity(saveBoard)
                        .build();
                boardImgeEntityRepository.save(boardImgEntity);
            }
            return true;
        }

        return false;
    }
    // 2. R
    @Transactional
    public List<BoardDto> getBoard(){
        // ================= 1 MAP X ===============================
/*
        // 1. 리포지토리를 이용한 모든 엔티티를 호출
        List<BoardEntity> result = boardEntityRepository.findAll();
        System.out.println("result = " + result);
        System.out.println("작성자 = " + result.get(0).getMemberEntity().getMemail() );
        // 2. Entity ---> Dto 변환한다.
        List<BoardDto> boardDtoList = new ArrayList<>();
            // 1. 꺼내온 entity 을 순회 한다.
        for (int i = 0 ; i < result.size() ; i++){
            // 2. 하나씩 entity 를 꺼낸다
            BoardEntity boardEntity = result.get(i);
            // 3. 해당 엔티티를  dto 로 변환한다
            BoardDto boardDto = boardEntity.toDto();
                // -- 게시물안에 게시물 사진
            List<String> bimList = new ArrayList<>();
                for(int j = 0 ; j < boardEntity.getBoardImgEntityList().size() ; j++){
                    BoardImgEntity boardImgEntity = boardEntity.getBoardImgEntityList().get(j);
                    String bimg = boardImgEntity.getBimg();
                    bimList.add(bimg);
                }
                boardDto.setBimgList(bimList);
            // 4. 변환된 dto를 리스트에 담는다.
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
*/
        // ============================================================
        // ========   MAP 스트림 사용    ========
        return boardEntityRepository.findAll().stream().map( (boardEntity)->{
               return boardEntity.toDto();
        }).collect(Collectors.toList());

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
