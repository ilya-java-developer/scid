import React from "react";
import { Paper, Tabs, Tab } from "@material-ui/core";

export default props => {
  const { muscles } = props;

  return (
    <Paper>
      <Tabs value={0} indicatorColor="primary" textColor="primary" centered>
        <Tab label="All" />
        {muscles.map(group => <Tab label={group} />)}
      </Tabs>
    </Paper>
  );
};
