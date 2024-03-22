import { useState } from "react";


export default function UseStateList(props){

    // 2. point 상태 관리 변수 ( 리액트 단점 )
        // 1. input 입력된 값
    let [pointInput , setPointInput] = useState('');
        // 2. input 입력된 값들을 저장 하는 리스트 상태관리 변수
    let [pointList , setPointList] = useState([]);


    // 1. 등록 버튼 클릭시.
    function 등록(){
        console.log('등록')

        // =============== 리액트 방식 ========== //
        pointList.push(pointInput);
        setPointList(pointList);    // 재랜더링X

    }; // f e 

    // 3. 
    function 입력변경(e){
        setPointInput(e.target.value);   // 재 랜더링.
    }

    return(<>
        
        <div>
            <input className="pointInput" type="text" onChange={입력변경} />
            <button type="button" onClick={등록} >등록</button>
        </div>
        <div>
            {
                pointList.map((point) => {
                    return ( <div>{point}</div>  )
                })
            }
            <div>{pointList}</div>
        </div>
    
    
    </>)

}