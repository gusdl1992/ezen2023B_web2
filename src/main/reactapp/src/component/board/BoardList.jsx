import axios from "axios";
import { useEffect, useState } from "react";
import MediaCard from "./MediaCard";

export default function BoardList(props){

    // 1. useState 변수
    const [boardList, setBoardList] = useState([]);
    console.log(boardList)

    // 2. 
    useEffect( ()=>{

        axios.get('/board/get.do')
        .then(response => {
            console.log(response)
            // 컴포넌트 렌더링된 이후 axios 가 데이터를 가져왔어.
            // 그럼 컴포넌트 다시 랜더링(새로고침) 해야하는데..
            // 서버로 받은 데이터를 setState 에 넣어주면 재랜더링
            setBoardList(response.data)

        })
        .catch(error => {
            console.error("데이터 가져오는 중 오류 발생:", error); // 오류 발생 시 로그로 출력
        });
    } , [])


    return (<>
        <div style={{ display: "flex" }}>
            {boardList.map( (board)=>{
                    return (
                        <MediaCard board = {board} />
                    )
                })}
        </div>
        </>
    )
}