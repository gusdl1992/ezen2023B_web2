import React from "react";

export default function Clock(props){

    let name = "유재석";
    // JSX 문법 들어가는 곳
    return(
        <div>
            <h1>안녕 , 리액트</h1>
            <h2>현재 시간 : { new Date().toLocaleTimeString() }</h2>
            {/* JS 주석 */}
        </div>
    );
        //============================================================
}
