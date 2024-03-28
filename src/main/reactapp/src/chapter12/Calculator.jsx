// React 라이브러리에서 useState를 가져옵니다.
import { useState } from "react";
// TemperatureInput 컴포넌트를 가져옵니다.
import TemperatureInput from "./TemperatureInput";

// 물이 끓는지 판단하는 컴포넌트입니다.
function BoilingVerdict(props) {
    // 섭씨 온도가 100도 이상이면 "물이 끓습니다."를 반환합니다.
    if (props.celsius >= 100) {
        return <p>물이 끓습니다.</p>
    }
    // 그렇지 않으면 "물이 끓지 않습니다."를 반환합니다.
    return <p>물이 끓지 않습니다.</p>
}

// 화씨를 섭씨로 변환하는 함수입니다.
function toCelsius(fahrenheit) {
    return ((fahrenheit - 32) * 5) / 9;
}

// 섭씨를 화씨로 변환하는 함수입니다.
function toFahrenheit(celsius) {
    return (celsius * 9) / 5 + 32;
}

// 주어진 온도와 변환 함수를 사용하여 변환을 시도하는 함수입니다.
function tryConvert(temperature, convert) {
    const input = parseFloat(temperature);
    // 유효하지 않은 입력이면 빈 문자열을 반환합니다.
    if (Number.isNaN(input)) {
        return '';
    }
    // 변환된 온도를 반올림하여 반환합니다.
    const output = convert(input)
    const rounded = Math.round(output * 1000) / 1000;
    return rounded.toString();
}

// Calculator 컴포넌트를 내보냅니다.
export default function Calculator(props) {
    // useState 훅을 사용하여 상태를 정의합니다.
    const [temperature, setTemperature] = useState('');
    const [scale, setScale] = useState("c");

    // 섭씨 온도가 변경될 때 호출되는 함수입니다.
    const handleCelsiusChange = (temperature) => {
        // 상태를 업데이트하고 섭씨로 단위를 설정합니다.
        setTemperature(temperature);
        setScale("c");
    };

    // 화씨 온도가 변경될 때 호출되는 함수입니다.
    const handleFahrenheitChange = (temperature) => {
        // 상태를 업데이트하고 화씨로 단위를 설정합니다.
        setTemperature(temperature);
        setScale("f");
    };

    // 현재 온도를 섭씨 또는 화씨로 변환합니다.
    const celsius =
        scale === "f" ? tryConvert(temperature, toCelsius) : temperature;
    const fahrenheit =
        scale === "c" ? tryConvert(temperature, toFahrenheit) : temperature;

    // JSX를 반환합니다.
    return (
        <>
            {/* 섭씨를 입력하는 TemperatureInput 컴포넌트 */}
            <TemperatureInput
                scale="c"
                temperature={celsius}
                onTemperatureChange={handleCelsiusChange}
            />
            {/* 화씨를 입력하는 TemperatureInput 컴포넌트 */}
            <TemperatureInput
                scale="f"
                temperature={fahrenheit}
                onTemperatureChange={handleFahrenheitChange}
            />
            {/* 물이 끓는지 판단하는 BoilingVerdict 컴포넌트 */}
            <BoilingVerdict celsius={parseFloat(celsius)} />
        </>
    )
}