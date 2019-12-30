import React, {Component, Fragment} from 'react';
import {Grid} from '@material-ui/core';

import Task from '../task';


import { red } from '@material-ui/core/colors';
import { makeStyles } from '@material-ui/core/styles';


const useStyles = makeStyles(theme => ({
  expand: {
    transform: 'rotate(0deg)',
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
      duration: theme.transitions.duration.shortest,
    }),
  },
  expandOpen: {
    transform: 'rotate(180deg)',
  },
  avatar: {
    backgroundColor: red
  }
}));


export default class User extends Component {
  state = {
    isLoading: true,
    user: {},
    states: [],
    tasks:[],
    expanded: false
  }


  postTask = async (url, task)=>{
    //  Надо запостить задачу, какие-то стейты этой задачи
    console.log(url);
    let data = await(
        await  fetch(url, {
          method: 'PUT',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(task)
        }).then(response => response.json())
        .then(this.handleResponse)
    )
    return data;
  }



  // когда промис созреет, он обновит значение стейта
   handleResponse = (json) => {
    console.log('this is the json data', json);
     this.setState( ({tasks, states, user})=>{
       let newTasks =[...tasks] ;
       const tidx = newTasks.findIndex((el)=> el.id === json.id);
       newTasks[tidx] = json;
       return  { tasks:  newTasks};
     });
  }

  getPostResponse = (task)=> {
    let promise = this.postTask('http://localhost:8080/api/task',task);
    return promise;
  }

  onChangeTaskStatus = (stateId, taskId) =>{
           const {tasks, states} = this.state;
           const tidx = tasks.findIndex((el)=> el.id === taskId);
           const sidx = this.state.states.findIndex((el) => el.id === stateId);
           // Новый объект , который является обновленным
           const newTask ={ id: tasks[tidx].id,
                            name: tasks[tidx].name,
                            body: tasks[tidx].body,
                            state: states[sidx]
           }
           this.getPostResponse(newTask);
  };



    async componentDidMount(props) {
      const {userId} = this.props;

      const response = await fetch("/api/user/" + userId);
      const body = await response.json();

      const classes = useStyles;
      console.log(classes.avatar);
      const statesResponse = await fetch("/api/states");
      const statesBody = await statesResponse.json();

      const taskResponse = await fetch(`/api/user/${userId}/tasks`);
      const contentTasks = await taskResponse.json();

      this.setState({user: body, isLoading: false, tasks: contentTasks});
      this.setState({states: statesBody});

      console.log("tasks ="+ this.state.tasks[0]);

    }




  render(){
      const {isLoading, user, states, tasks} = this.state;
      const {styles} = this.props;

      if (isLoading) {
      return (
        <Fragment>
          <h1>...Loading</h1>
        </Fragment>);
      }

      return (
      <Grid container style={styles.root} spacing={2}>
        <Grid item xs={12}>
          <h1 align="center"> {user.firstName + " " + user.middleName + " " + user.lastName} </h1>
          <Grid container justify="center" spacing={2}
              // style={styles.CardContainer}
              //  key={user.id}>
          >
            {tasks.map(task => {
                                  return (
                                          <Task styles={styles} task={task} user={this.user} onStatus={this.onChangeTaskStatus} states={states}
                                          key={task.id}/>
                                          );
                                })
            }
          </Grid>
        </Grid>
      </Grid>
      );
  }
}