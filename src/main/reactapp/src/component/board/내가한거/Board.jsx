import axios from "axios";
import { useEffect, useRef, useState } from "react";


export default function Board(props){

    const [board, setBoardInfo] = useState([]);

    useEffect(() => {
        axios.get('/board/get.do')
            .then((response) => {
                console.log("응답 데이터:", response.data); // 응답 데이터를 로그로 출력
                setBoardInfo(response.data);
            })
            .catch((error) => {
                console.error("데이터 가져오는 중 오류 발생:", error); // 오류 발생 시 로그로 출력
            });
    }, []);

    console.log("테스트 데이터:", board); // 테스트 상태를 로그로 출력

    return (
        <>
            {board.map((item, index) => (
                <span key={index}>
                    <p>이메일: {item.memail} &nbsp; 내용: {item.bcontent}</p>

                </span>
            ))}
        </>
    );
}