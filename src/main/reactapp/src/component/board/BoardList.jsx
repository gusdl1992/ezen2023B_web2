import axios from "axios";
import { useEffect, useState } from "react";
import MediaCard from "./MediaCard";
import { Pagination } from "@mui/material";

export default function BoardList(props){

    // 1. useState 변수
    const [pageDto, setPageDto] = useState({
        page : 1 ,count : 0 , data : []
    });
    
    // 2. 
    useEffect( ()=>{
        // 페이지 네이션  프론트
        const info = {page : pageDto.page , view : 4}

        axios.get('/board/get.do', {params : info} )
        .then(response => {
            console.log(response)
            // 컴포넌트 렌더링된 이후 axios 가 데이터를 가져왔어.
            // 그럼 컴포넌트 다시 랜더링(새로고침) 해야하는데..
            // 서버로 받은 데이터를 setState 에 넣어주면 재랜더링
            // setPageDto(response.data)
            setPageDto( response.data );
        })
        .catch(error => {
            console.error("데이터 가져오는 중 오류 발생:", error); // 오류 발생 시 로그로 출력
        });
    } , [ pageDto.page ])

    const handleChange = (e, value) => {
        pageDto.page = value;
        setPageDto({...pageDto});
      };

    return (<>
        <div style={{ display: "flex" , flexWrap : "wrap"}}>
            {pageDto.data.map( (board)=>{
                    return (
                        <MediaCard board = {board} 
                        setPageDto = {setPageDto}
                        />
                    )
                })}
        </div>
        <Pagination count={pageDto.count} page={pageDto.page} onChange={handleChange} />
        </>
    )
}
// The current page. 현재페이지
// count : 총 페이지 수
// onChange : 
// 'Pagination' is not defined import { Pagination } from "@mui/material";