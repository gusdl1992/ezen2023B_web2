import Comment from "./Comment";

export default function CommentList(props){

    // 서버 또는 props 로 전달 매개변수 값들을 출력
    // 샘플
    let response = [ {name : '유재석' , content : '안녕하세요1'} ,
    {name : '강호동' , content : '안녕하세요2'} ,
    {name : '신동엽' , content : '안녕하세요3'} ];


    return(
        <div>
            {
                response.map( (data) =>{
                    return(
                        <Comment name = {data.name} comment = {data.content}/>
                    );

                })
            }
        </div>
    );
}
{/* <div>
<Comment name = {'이인제'} comment = {'제가 만든 첫 컴포넌트 입니다1.'}/>
<Comment name = {'훈발놈'} comment = {'제가 만든 첫 컴포넌트 입니다2.'}/>
</div> */}