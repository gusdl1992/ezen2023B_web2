import { useState } from "react";




export default function 컴포넌트명(props){
    
    // 1. 변수
    let countValue = 0;

    // 2. 함수
    function 클릭함수(){
        countValue++; // 변수 값 1증가
        console.log("함수 내부"+countValue);
    }
        console.log("컴포넌트안"+countValue);

    // 3. 상태관리 변수 !!! : state 값의 변화 ( set~~ ) 변화가 있으면 해당 컴포넌트 재호출/렌더링
        // 1. import { useState } from "react";
        // 2. useState('초기값')
            // [0] : state의 초기값 또는 값;
            // [1] : state의 set 함수 ( state 값을 변경 할때 사용 되는 함수 )
    let 상태관리변수 = useState('훅이란 무엇인가?');
    console.log(상태관리변수);
    console.log(상태관리변수[0]);

    

    const [count , setCount] = useState(0)

    const [inputValue1 , setInputValue1] = useState('');

    function inputValuew1Update(e){     // e : event 
        console.log(e);                 // e : 이벤트를 발생한 결과물 객체
        console.log(e.target);          // e.target :  이벤트를 발생시킨 주체자(마크업) == this
        console.log(e.target.value);    // e.target.value : 이벤트를 발생시킨 주체자의 value 속성
        setInputValue1(e.target.value); // state 변경함수인 set 함수를 호출해서 값 대입. = 재랜더링 == 컴포넌트 다시 호출!!
    }


    return (<> 
        <div>
            <p>일반변수 : 총{countValue} 번 클릭했습니다.</p>
            <button onClick={() => countValue++} type="button">
                클릭
            </button>
        </div>

        <div>
            <p>state 변수 : 총{count} 번 클릭했습니다.</p>
            <button onClick={ () => setCount(count+1) } type="button">
                클릭
            </button>
        </div>


        <div>
            <input type="text" />
            <input type="text" value={inputValue1} />
            <input type="text" value={inputValue1} onChange={inputValuew1Update}/>
        </div>

    </>);
}