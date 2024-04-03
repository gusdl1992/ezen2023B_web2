import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useContext } from 'react';
import { LoginInfoContext } from '../Index';

export default function MediaCard(props) {

  console.log(props);  // { 'board' : board객체{} }

  const navigate = useNavigate();
  const {loginInfo} = useContext(LoginInfoContext);
  console.log("loginInfo")
  console.log(loginInfo)
  
  const onDelete = (event , bno , mno_fk) => {

    // if ( 작성자 회언 == 로그인된 회원 ){}
    console.log(bno);
    if(mno_fk != loginInfo.mno){
      alert("삭제가 불가능 합니다.");
      return
    }
    
    // 서버에 삭제 요청 보내기
    axios.delete('/board/delete.do' , { params: {bno : bno} })
    .then(r =>{ console.log(r);
      
      // 1. get방식
      alert("삭제 완료!")
      window.location.href="/board";
      // 2.라우터 방식
        // 1. useNavigate() 훅 필요 , import { useNavigate } from 'react-router-dom';
          // - const navigate = useNavigate();
        // 2. navigate( 라우터 URL );
      // navigate('/board');
        // 3. props 방식
        // props.호출 함수
    })
    .catch(e => {console.log(e);})
  }
  



  return (
    <Card sx={{ maxWidth: 400 }} style={{margin :10}}>
      {/* 이미지를 클릭하여 링크로 이동하고 싶다면, CardMedia를 a 태그로 감싸거나 CardMedia 자체를 클릭 가능한 요소로 만들어야 합니다. */}
      <a href={"/uploadimg/" + props.board.bimgList[0]}>
      <CardMedia
        sx={{ height: 200 }}
        image={"/uploadimg/" + props.board.bimgList[0]}
        title="green iguana"
      />
      </a>
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {props.board.memail}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {props.board.bcontent}
        </Typography>
      </CardContent>
      <CardActions>
        { // 자기 회원만 버튼 보이게 하기
          props.board.mno_fk == loginInfo.mno &&
          <Button onClick={(event) => onDelete (event , props.board.bno , props.board.mno_fk)} size="small">삭제</Button>
        }
        
      </CardActions>
    </Card>
  );
}