import React, {Component} from 'react';
import {AppBar, Toolbar, Typography} from '@material-ui/core';

export default class Header extends Component {
  render(){
    return(
      <AppBar position="static">
        <Toolbar> 
          <Typography variant="subtitle2" color="inherit">
             Excercise dataBase
          </Typography>
        </Toolbar>
      </AppBar>
    );

  };
}