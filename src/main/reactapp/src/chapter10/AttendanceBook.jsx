
export default function AttendanceBook(props){
    
    // 1. 샘플 데이터
    const students = [
        {id : 1 ,name : 'Inje'} , {id : 2, name : 'Steve'} , {id : 3 ,name : 'Bill'} , {id :4 ,name : 'Jeff'}
    ]


    return(<>
        <ul>
            {
                // ---- JSX 시작 -- 리액트 책 p.313 자주 사용예정

                students.map( (student,index) =>{
                    return (<>
                        <li
                            key={student.id}
                            id={student.id}
                            className={student.id}
                        >
                            {student.name}
                        </li>
                    </>)
                } )
            }
        </ul>

    </>);
}