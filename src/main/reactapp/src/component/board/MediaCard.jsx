import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { Link } from 'react-router-dom';

export default function MediaCard(props) {

  console.log(props);  // { 'board' : board객체{} }

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
        <Button size="small">Share</Button>
        <Button size="small">Learn More</Button>
      </CardActions>
    </Card>
  );
}