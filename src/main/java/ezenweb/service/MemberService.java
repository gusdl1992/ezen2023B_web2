package ezenweb.service;

import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberEntityRepository memberEntityRepository;

    // 1. 회원가입
    public int doSignupPost(MemberDto memberDto){
        System.out.println("MemberService.doSignupPost");
        System.out.println("memberDto = " + memberDto);
        int result = idCheck(memberDto);
        if (result != 0 ){
            try {
                // -- Dao 아닌 엔티티 이용한 레코드 저장하는 방법
                // 1. 엔티티를 만든다
                // 2. 리포지토리를 통한 엔티티를 저장한다. ( 엔티티 저장 성공시 매핑된 엔티티 반환 )
                MemberEntity savedEntity = memberEntityRepository.save(memberDto.toEntity()); // insert
                // 3. 엔티티 생성이 되었는지 아닌지 확인 (PK)
                if(savedEntity.getMno() > 0){
                    return 1;
                }
            }catch (Exception e){
                System.out.println("e = " + e);
                return -1;
            }
        }
        return result;
    }

    // * 로그인 했다는 증거/기록
    @Autowired
    private HttpServletRequest request;

    // 2. 로그인 ( 세션 저장 )
    public boolean doLoginPost(MemberDto memberDto){
        System.out.println("MemberService.doLoginPost");
        System.out.println("memberDto = " + memberDto);
        // 1. 리포지토리를 통한 모든 회원 엔티티 호출
        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();
        // 2. dto와 동일한 아이디/패스워드 찾는다.
        for(int i=0 ; i< memberEntityList.size() ; i++){
            MemberEntity m = memberEntityList.get(i);
            // 3. 만약에 아이디가 동일하면( 엔티티와 dto )
            if(m.getMemail().equals(memberDto.getMemail())){
                // 4. 만약에 비밀번s호가 동일하면
                if (m.getMpassword().equals(memberDto.getMpassword())){
                    // 5. 세션 저장
                    request.getSession().setAttribute("loginInfo",memberDto);
                    return true;
                }
            }
        }
        return false;
    }

    // 3. 로그아웃 ( 세션 삭제 )
    public boolean doLogOutGet(){
        request.getSession().setAttribute("loginInfo" , null);
        return true;
    }

    // 4. 현재 로그인 회원 정보 호출 ( 세션 값 반환/호출 )
    public MemberDto doLoginInfo(){
        Object object = request.getSession().getAttribute("loginInfo");
        if (object != null){
            return  (MemberDto)object; // 강제 형변환
        }

        return null;
    }

    // 아이디 중복 검사 중복 0 , 중복통과 1
    public int idCheck(MemberDto memberDto){
        System.out.println("MemberService.idCheck");
        // 1. 리포지토리를 통한 모든 회원 엔티티 호출
        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();
        // 리스트에서 아이디 중복 검사
        for(int i = 0 ; i < memberEntityList.size() ;i++){
            MemberEntity m = memberEntityList.get(i);
            if (m.getMemail().equals(memberDto.getMemail())){
                return 0;
            }
        }
        return 1;
    }

}
