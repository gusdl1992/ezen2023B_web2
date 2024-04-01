import axios from "axios";

export default function BoardWrite(props){

    // 글등록 요청 함수
    const onWrite = () =>{
        // 1. 전송할 폼 가져온다.
        const writeForm = document.querySelector('#writeForm');
        // 2. 데이터 폼으로 변환
        const writeFormData = new FormData(writeForm);
        // 3. 서버와 통신
        axios.post('/board/post.do' , writeFormData)
        .then( (r)=>{
            console.log(r);
            if(r.data){
                alert('글 등록 성공!')
                window.location.href="/board";
            }else{
                alert('글 등록 실패!')
            }

        });
    } // f e
    
    return(<>
        <form id = "writeForm">
            내용 : <input type="text" name="bcontent"/>
            <button type="button" onClick={onWrite}>글 등록</button>
        </form>
    
    </>);
}