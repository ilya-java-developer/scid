import React from 'react';
import {Card, Grid, Typography, Avatar, CardHeader} from '@material-ui/core';
import CardActions from '@material-ui/core/CardActions';

import { red, yellow, blue, green } from '@material-ui/core/colors';
import { makeStyles } from '@material-ui/core/styles';

import TaskContent from '../taskcontent';
import State from '../state';
import "./task.css";

const useStyles = makeStyles({   
  expand: {
    transform: 'rotate(0deg)',
    marginLeft: 'auto',
  },
  expandOpen: {
    transform: 'rotate(180deg)',
  },
  avatar: {
    backgroundColor: props=> props.backgroundColor}
  ,
});

const Task = ({task, styles, states, user, onStatus}) => {
    let prop =  {backgroundColor: red[500]};
    let stateName = 'Н';
    const state = task.state;
    if(state.name === 'Новая') {
        prop = {backgroundColor: yellow[600]} ;

    }
    if(state.name === 'В работе') {
      prop = {backgroundColor: green[500]};
      stateName='Р';
    }
    if(state.name === 'Выполнена') {
      prop = {backgroundColor: blue[500]};
      stateName='З';
    }
    const classes = useStyles(prop);
    const [expanded, setExpanded] = React.useState(false);


    const tittleTask = ( inStr) => {
      let strshortened = inStr;
       if(inStr.length > 8) {
          strshortened = inStr.slice(0,8)+"...";
       }
       return strshortened;

    }

    function excludeState(id) {
      const arr = states.filter(state => state.id!==id )

     return  arr;
  }

  const  statesFiltered =  excludeState(task.state.id).filter(item => item.name!=="Новое" );

    console.log(classes.avatar);
      return(
        <Grid item key={task.id}>
          <Card style={styles.Card} elevation={4} >
              <CardHeader  avatar={ <Avatar className={classes.avatar}> {stateName} </Avatar> }
                           action={
                                    <State states={statesFiltered} task={task}  value={task.state.id}  onStatus={(sid, tid)=>onStatus(sid, tid)} />
                           }
                           title={
                              <Typography gutterBottom variant="h5" component="h2">
                                  {tittleTask(task.name)}
                              </Typography>
                            }
                           subheader="September 14, 2016"
              />
              <CardActions disableSpacing>
              {/* <Button size="small" color="primary">
                  Подробнее...
              </Button> */}
                 <TaskContent task={task} states={statesFiltered}/>
                  {/* <IconButton
                      className={clsx(classes.expand, {
                                                        [classes.expandOpen]: expanded
                                                      })}                                                  
                      onClick={handleExpandClick}
                      aria-expanded={expanded}
                      aria-label="show more"
                  >
                      <ExpandMoreIcon />
                  </IconButton> */}
               </CardActions>
               {/* <Collapse in={expanded} timeout="auto" accordion="true" unmountOnExit>
                  <CardContent>
                      <Typography paragraph>{task.body}</Typography>
                  </CardContent>
               </Collapse> */}
          </Card>
      </Grid>);
}

export default Task;