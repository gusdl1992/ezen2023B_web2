import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';


// * 내가 만든 컴포넌트 import(가져오기) 호출
// import 컴포넌트 from 컴포넌트 파일경로;
import JSX선언 from './chapter3/1_JSX선언';

// chapter3 실습
import Book from './chapter3/Book';
import Library from './chapter3/Library';

// chapter4 실습
import Clock from './chapter4/Clock';

// chapter5 실습
import CommentList from './chapter5/CommentList';

// chapter7 예제/실습
import Counter from './chapter7/Counter';
import UseStateList from './chapter7/UseStateList';
import Counter2 from './chapter7/Counter2';

// chapter8 예제/실습
import ConfirmButton from './chapter8/ConfirmButton';
// chapter9 예제/실습
import LandingPage from './chapter9/LandingPage';
import AttendanceBook from './chapter10/AttendanceBook';
import NameForm from './chapter11/NameForm';
// import SignUp from './chapter11/SignUp';

// 11챕터 같이 실습
import SignUp from './component/member/SignUp';

const root = ReactDOM.createRoot(document.getElementById('root'));

// 챕터 7
root.render(
  // <Counter/>
  //<UseStateList/>
  // <Counter2/>

  // 챕터 8
  //<ConfirmButton/>
  
  // 챕터9
  // <LandingPage />

  // 챕터10
  //<AttendanceBook/>

  // 챕터11
  //<NameForm/>
  //<SignUp/>
  <SignUp/>
);



// root.render(  // !!!!!!!!!! 여기가 랜더링 되는 곳
//   <React.StrictMode>
//     {/* <App/> */}
//     {/* <JSX선언/> */}
//     {/* <Book /> */}
//     {/* <Library /> */}
//     <Clock/>
//   </React.StrictMode>
// );

// 챕터 4

// setInterval(()=>{
//   root.render(
//     <React.StrictMode>
//         <Clock/>
//     </React.StrictMode>
//   );
// },1000);

// 챕터 5
// root.render(
//   <CommentList/>
// );


// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
