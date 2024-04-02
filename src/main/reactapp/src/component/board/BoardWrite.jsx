import axios from "axios";
import { useRef } from "react";

export default function BoardWrite(props){

        // 1. 재렌더링 고정 참조 변수
        const boardWriteFormRef = useRef(); // { current : undefined }
        console.log(boardWriteFormRef)

        const onWrite = () =>{
            console.log(boardWriteFormRef); 
            console.log(boardWriteFormRef.current);

        // 2. 
    
        // 2.
        axios.post('/board/post.do' , boardWriteFormRef.current)
        // axios contentType : from 데이터 일시 multipart 가 기본값 
        .then(response =>{console.log(response)
            if(response.data){
                alert('글 등록 성공!')
                window.location.href="/board";
            }else{
                alert('글 등록 실패!')
            }
        
        })
    }


    
    return(<>
    <h3>게시물쓰기</h3>
    <form ref={boardWriteFormRef}>
        <input name="bcontent" type="text" />
        <input type="file" name="uploadList" multiple accept="image/*" />
        <button type="button" onClick={onWrite}> 등록 </button>
    </form>
    </>);
}

/*
    원래 방식

    // 1.
    const onWrite = () =>{
        const boardWriteForm = document.querySelector('#boardWriteForm');

    
    // 2. 
    const boardWriteFormData = new FormData(boardWriteForm);

    // 2.
    axios.post('/board/post.do' , boardWriteFormData)
    .then(response =>{console.log(response)})
    }


*/