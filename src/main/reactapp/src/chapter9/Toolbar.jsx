
export default function Toolbar(props){
    
    // 1. props 매개 변수
    // const { isLoggedIn , onClickLogin , onClickLogout } = props;
    //console.log(props);
    
    return(<>
        <div>
            {
                props.isLoggedIn && <span> 환영합니다. </span>
            }
            {
                props.isLoggedIn ? 
                (<button onClick={props.onClickLogout}>로그아웃</button>)
                :
                (<button onClick={props.onClickLogin}>로그인</button>)
            }
        </div>
    
    </>);
}