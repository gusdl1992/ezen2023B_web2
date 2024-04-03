package ezenweb.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {

    // Controller : 중계자 역할 ( HTTP매핑 , HTTP요청/응답 , 데이터 유효성검사 )등등
    // Service : Controller <--  Service(비지니로직) --> Dao ,  Controller <--> Service(비지니로직)

    @Autowired
    private HttpServletRequest request; // HTTP 로 요청을 보낸 정보와 기능/메소드  담긴 객체 ( 매개변수 와 브라우저 정보 - > 세션)
    @Autowired
    private HttpServletResponse response; // HTTP 로 응답을 보낼 정보와 기능/메소드 가지고 있는 객체

    // 1. 업로드 경로
        // 어디에(PATH) 누구를(파일객체 MultipartFile )
        // 내장서버 경로
    String uploadPath = "C:\\Users\\504\\Desktop\\ezen2023B_web2\\build\\resources\\main\\static\\uploadimg\\";
        // AWS 경로

    // 2. 업로드 서비스 메소드 ( 하나의 multipartFile 존재하는 파일 업로드 )
    public String fileUpload( MultipartFile multipartFile){
        // * 파일 이름 조합하기 : 새로운 식별이름과 실제 파일 이름
        String uuid = UUID.randomUUID().toString();
        // 2. 조합( UUID 와 파일이름의 구번선이 _ 이기떄문에 파일명에 _ 존재할수도 있기때문에 _ 를 - 치환 )
        String filename  = uuid+"_"+multipartFile.getOriginalFilename().replaceAll("_","-");
        // 1. [어디에] 첨부파일을 저장할 경로
        // File 클래스 : 파일 관련된 메소드 제공.
        File file = new File( uploadPath+filename );
        // 2. [무엇을] 첨부파일 객체
        
        
        
        // .transferTo( 경로 )
        try { multipartFile.transferTo( file );}
        catch ( Exception e ){
            System.out.println("multipartFile e = " + e);
            return null;
        }
        return filename; // 반환 : 어떤 이름으로 업로드 했는지 식별명 반환해서
    }
}
