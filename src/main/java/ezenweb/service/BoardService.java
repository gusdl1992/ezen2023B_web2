package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.dto.PageDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
                if (fileName != null){
                    BoardImgEntity boardImgEntity = BoardImgEntity.builder()
                            .bimg(fileName) // 방금 위에 업로드된 파일명을 엔티티에 대입
                            .boardEntity(saveBoard) // FK : 게시물 FK
                            .build();
                    // 엔티티 영속성 ( 영속성이 필요한 이유 : DB 저장할려고 => 서버가 종료되면 자바는 사라지니까 )
                    boardImgeEntityRepository.save(boardImgEntity);
                }
            }
            return true;
        }
        return false;
    }
    // 2. R
    @Transactional
    public PageDto getBoard(int page , int view){

        // ========   MAP 스트림 사용    ========

        // 1. pageable 인터페이스를 이용한 페이징 처리  ( import org.springframework.data.domain.Pageable; )
        // Pageable pageable = PageRequest.of(현재페이지 , 페이지당 표시할 레코드 수)
        // - 현재 페이지는 0부터 시작 -> Pageable pageable = PageRequest.of(0,4) -> 1page 에서 4개만
        // findAll( pageable ) , find~~~( pageable )
        // pageable 은 page 순서를  0부터 시작하기 때문에 page 가 1페이지 일때  0페이지로 변환 하기 위해 -1
        Pageable pageable = PageRequest.of(page-1,view);
        // 2. * 페이징처리된 엔티티 호출
        Page<BoardEntity> boardEntityPage = boardEntityRepository.findAll(pageable);

        // -- List 아닌 Page 타입일때 List 동일한 메소드 사용하고 추가 기능
        // 1. 전체 페이지 수 - boardEntityPage.getTotalPages() 토탈 페이지
        System.out.println("boardEntityPage.getTotalPages() = " + boardEntityPage.getTotalPages());
        int count = boardEntityPage.getTotalPages();
        // 2. 전체 게시물 수- boardEntityPage.getTotalElements() 전체 레코드 수
        System.out.println("boardEntityPage.getTotalElements() = " + boardEntityPage.getTotalElements());

        // 엔티티를 Dto 변환
        List<Object> data = boardEntityPage.stream().map( (boardEntity)->{
            return boardEntity.toDto();
        }).collect(Collectors.toList());

        // 2. 페이지 반환값 구성
        PageDto pageDto = PageDto.builder()
                .data(data) // 페이징 처리된 레코드 들을 대입
                .page(page) // 현재 페이지 수
                .count(count) // 전체 페이지 수
                .build();
        return pageDto;

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
    public boolean deleteBoard(int bno){
        
        // 1. 회원번호 = 회원서비스에 검증
        MemberDto memberDto = memberService.doLoginInfo();
        if (memberDto == null) return false;
        // 2. 내 게시물 확인
        Optional<BoardEntity> optionalBoardEntity = boardEntityRepository.findById(bno);
        if (optionalBoardEntity.isPresent()){
            if( optionalBoardEntity.get().getMemberEntity().getMno() ==  memberDto.getMno() ){
                boardEntityRepository.deleteById(bno);
                return true;
            }
        }
        return false;
    } //  m e
} // c e
