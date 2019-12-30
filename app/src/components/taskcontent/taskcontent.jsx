import React, {Fragment} from 'react';
import { withStyles } from '@material-ui/core/styles';

import { Button, Dialog, Typography, IconButton} from '@material-ui/core';
import CloseIcon from '@material-ui/icons/Close';

import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';

const DialogTitle = withStyles(theme => ({
  root: {
    borderBottom: `1px solid ${theme.palette.divider}`,
    margin: 0,
    width:  300,
    padding: theme.spacing.unit * 2,
  },
  closeButton: {
    position: 'absolute',
    right: theme.spacing.unit,
    top: theme.spacing.unit,
    color: theme.palette.grey[500],
  },
}))(props => {
  const { children, classes, onClose} = props;
  return (
    <MuiDialogTitle disableTypography className={classes.root}>
      <Typography variant="h6">{children.name}</Typography>
      {onClose ? (
        <IconButton aria-label="Close" className={classes.closeButton} onClick={onClose}>
          <CloseIcon />
        </IconButton>
      ) : null}
    </MuiDialogTitle>
  );
});

const DialogContent = withStyles(theme => ({
  root: {
    margin: 0,
    width: 300,
    padding: theme.spacing.unit * 2,
  },
}))(MuiDialogContent);

const DialogActions = withStyles(theme => ({
  root: {
    borderTop: `1px solid ${theme.palette.divider}`,
    margin: 0,
    padding: theme.spacing.unit,
  },
}))(MuiDialogActions);

const ButtonStyled = withStyles(theme => ({
  root: {
    padding: theme.spacing.unit,
  },
}))(Button);

class TaskContent extends React.Component {
  state = {
    open: false,
    statusTask: { id:4 ,  name:"Новое"}
  };


  handleClickOpen = () => {
    this.setState({
      open: true,
    });
  };

  handleClose = () => {
    this.setState({ open: false });
  };

  render() {
    const {task, states} = this.props;
    return (
      <Fragment>
        <Button variant="outlined" color="primary" style={{border: "0px"}} onClick={this.handleClickOpen}>
            Подробнее...
        </Button>
        <Dialog
          onClose={this.handleClose}
          aria-labelledby="customized-dialog-title"
          open={this.state.open}
        >
          <DialogTitle id="customized-dialog-title" children={task} onClose={this.handleClose} />  
          <DialogContent>
            <Typography gutterBottom>
              {task.body}              
            </Typography>           
          </DialogContent>
          <DialogActions>
            {states.map(state => {  
                let buttonStyle = {
                                    borderColor: "green", color: "green"};
                if(state.name === "Выполнено") {
                  buttonStyle = {borderColor: "blue", color: "blue"};
                }
                
                return (
                <ButtonStyled key={state.id} onClick={this.handleClose} variant="outlined" color="secondary">
                        {state.name}
                </ButtonStyled>)
                })
            }            
          </DialogActions>
        </Dialog>
      </Fragment>
    );
  }
}

export default TaskContent;
