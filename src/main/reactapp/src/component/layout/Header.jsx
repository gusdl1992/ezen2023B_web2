import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { LoginInfoContext } from "../Index";


export default function Header(props){
    
    // - Provider 컴포넌트의 value 호출
    const {loginInfo , setLoginInfo} = useContext(LoginInfoContext)

    // 컴포넌트 생성시 axios 실행해서 로그인 회원정보 호출
    // 1. 컴포넌트가 실행될때 1번 axios 요청 보내서 회원정보 가져온다.
    useEffect( ()=>{
        axios.get('/member/login/info/get.do')
        .then( r => {console.log(r); 
            setLoginInfo(r.data);   // 현재 로그인정보를 state 담는다.

        })
        .catch( r => {console.log(r); })
    },[] );

    // 로그아웃 버튼 클릭시 서버 로그아웃 요청  내가 만든거
    function logoutBtn(){
        axios.get('/member/logout/get.do')
        .then( r => {console.log(r); 
            setLoginInfo('');   // 로그아웃 후 useState loginInfo 공백 처리.
        })
        .catch( r => {console.log(r); })
    }
    
    // 선생님 코드 로그아웃
    const onLogOut = ()=> { 
        axios.get('/member/logout/get.do')
        .then( r => {console.log(r); 
            if(r.data){
                alert("로그아웃 성공");
                window.location.href = "/member/login";
            }else{ alert("로그아웃 실패"); }
        })
        setLoginInfo(''); // 로그아웃 후 useState loginInfo 공백 처리.
    }

    return(<>
        <div>
            {loginInfo && <span>{loginInfo.memail} 님 안녕하세요.<button type="button" onClick={onLogOut}>로그아웃</button></span>}
            <ul>
                <li><Link to="/">홈</Link></li>
                <li><Link to="/member/signup">회원가입</Link></li>
                <li><Link to="/member/login">로그인</Link></li>
                <li><a href="/board/write">글쓰기</a></li>
                <li><a href="/board">전체글보기</a></li>
                <li><a href="/chat">채팅방</a></li>
            </ul>
        </div>

    </>);
}