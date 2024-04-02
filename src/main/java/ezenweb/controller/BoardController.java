package ezenweb.controller;


import ezenweb.model.dto.BoardDto;
import ezenweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController // 해당 컨트롤러가 데이터를 주고 받는:  역할 @Controller  + @ResponseBody
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;


    @PostMapping("/post.do")
    public boolean postBoard(BoardDto boardDto){
        // 엔티티 객체 = 레코드
        System.out.println("boardDto = " + boardDto);
        return boardService.postBoard(boardDto);
    }



    @GetMapping("/get.do")
    public List<BoardDto> getBoard(){
        return boardService.getBoard();
    }
    @PutMapping("/put.do")
    public boolean putBoard(){
        return boardService.putBoard();
    }
    @DeleteMapping("/delete.do")
    public boolean deleteBoard(){
        return boardService.deleteBoard();
    }
}
