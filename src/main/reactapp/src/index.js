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

const root = ReactDOM.createRoot(document.getElementById('root'));

// 챕터 5
root.render(
  <CommentList/>
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


// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
