import { useState } from "react";

export default function NameForm(props){
    
    // 1. 함수
    function 제출1(){
        let nameInput = document.querySelector('#nameInput').value;
    }

    // 2. 함수2
        // 1. state 변수
    const [value , setvalue] = useState('');
        // 2. state 변경함수 e(event : 해당 이벤트(change)를 발생한 결과 정보 객체 )
    const handleChange = (e)=> { 
        setvalue(e.target.value); 
        e.preventDefault(); // 브라우저들의 다른 이벤트 들을 제거.
    }


    // 3. 
    const [ value2 , setValuew2 ] = useState('');
    const handleChange2 = (e) => { 
        setValuew2(e.target.value); 
        e.preventDefault();
    }

    // 4.
    const [ value3 , setValuew3] = useState('grape');
    const handleChange3 = (e) => {
        setValuew3(e.target.value);
    }

    // 3. 제출함수
    const handleSubmit = (e) => { 
        console.log(value); 
        console.log(value2); 
        console.log(value3);
    }

    return(<>
        <form action="">
            이름 : <input id="nameInput" type="text" />
            <button type="button" onAbort={제출1}>제출1</button>
        </form>
        <form action="">
            이름 : 
            <input 
                type="text" 
                value={value} 
                onChange={handleChange} 
            /><br/>
            요청사항 : 
            <textarea 
                value={value2} 
                onChange={handleChange2}>
            </textarea> <br/>

            과일을 선택하세요.
            <select value={value3} onChange={handleChange3}>
                <option value="apple"> 사과 </option>
                <option value="banana"> 바나나 </option>
                <option value="grape"> 포도 </option>
                <option value="watermelon"> 수박 </option>
            </select>

            <button type="button" onClick={handleSubmit}>제출2</button>
        </form>
    </>);
}