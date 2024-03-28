// Calculator 컴포넌트를 './Calculator'에서 가져옵니다.
// 이 컴포넌트는 사용되지 않으므로 나중에 제거할 수 있습니다.
import Calculator from "./Calculator";

// 온도 단위에 대한 이름을 정의한 객체입니다.
const scaleNames = {
    c: '섭씨',
    f: '화씨',
};

// TemperatureInput 컴포넌트를 내보냅니다.
export default function TemperatureInput(props) {
    console.log(props)

    // 입력 필드의 값이 변경될 때 호출되는 함수입니다.
    const handleChange = (e) => {
        console.log("123 : " + e)
        // 부모 컴포넌트에서 전달된 함수를 호출하여 온도 값 변경을 알립니다.
        props.onTemperatureChange(e.target.value);
    }

    // JSX를 반환합니다.
    return (
        <>
            <fieldset>
                <legend>
                    온도를 입력해 주세요 (단위:{scaleNames[props.scale]})
                </legend>
                {/* 입력 필드 */}
                <input type="text" value={props.temperature} onChange={handleChange} />
            </fieldset>
        </>
    )
}