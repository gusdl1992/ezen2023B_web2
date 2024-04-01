import { useCallback, useState } from "react"
import ThemeContext from "./ThemeContext";
import MainContent from "./MainContent";


export default function DarkOrLight(props){

    // 1. 테마 상태
    const [theme , setTheme] = useState("light");

    // 2. 상태 변경
    const toggleTheme = useCallback( () => {
        if(theme == "light"){
            setTheme("dark");
        }else if(theme == "dark"){
            setTheme("light");
        }
    }, [theme] );

    return (<>
        <ThemeContext.Provider value={{theme , toggleTheme}}>
            <MainContent />
        </ThemeContext.Provider>
    </>)
}