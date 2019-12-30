import React, {Fragment} from 'react';

import MoreVertIcon from '@material-ui/icons/MoreVert';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';

import IconButton from '@material-ui/core/IconButton';
const State = ({states, task, onStatus}) => {
  const [anchorEl, setAnchorEl] = React.useState(null);
  const handleClick = event => {
    console.log("open menu");      
    setAnchorEl(event.currentTarget);
    console.log(event.currentTarget);  
  };

  const handleClose = (event, index) => {
    console.log(index + " close menu "+ states[index]);
    setAnchorEl(null);
  };


    return( 
      <Fragment>
        <IconButton aria-controls="task-menu" aria-haspopup="true" aria-label="settings" onClick={handleClick}>
          <MoreVertIcon />
        </IconButton>
       <form>
        <Menu
          id="task-menu"
          anchorEl={anchorEl}
          keepMounted
          open={Boolean(anchorEl)}
          onClose={handleClose}
        >          
          {states.map((item, index) => {
                const {id, ...itemProps} = item;
                return (

                <MenuItem {...itemProps}
                        onClick={
                             event => {
                             handleClose(event, index);
                             onStatus(item.id, task.id);}
                        }
                        key={item.id}
                        type="submit"
                >
                   {item.name}                 
                </MenuItem>
                );
              })
          }
        </Menu></form>
      </Fragment>)      
}

export default State;