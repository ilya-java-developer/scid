import React, { Component, Fragment } from "react";
import User from '../user';
import Footer from "../footer";
import Header from "../header";





export default class Application extends Component {
  state = {
    isLoading: true,
    departments: []
  };

  styles = {
    root:{
      flexGrow: 1
    },
    Paper: {
      padding:20,
       marginTop: 10, 
       marginBottom: 10, 
       height: 300,
       overflowY: 'auto'
    },
    CardContainer:{   
      overflow: 'hidden',  
      overflowY: 'auto',      
      flexGrow: 1,
    },

    testPaper:{
      height: 200,
      width: 300,
    },

    Card: {
      width: "300", 
      minWidth: 300
     
      // height: 200
    }
  };

  async componentDidMount() {
    const response = await fetch("/api/departments");
    const body = await response.json();
    console.log(body);
    this.setState({ departments: body, isLoading: false });
  }

  render() {
    const { departments, isLoading } = this.state;

    function getBossDepName(row) { 
      const {bossDepartment}  = row;
      let name = 'none';
      if(bossDepartment){
          name=bossDepartment.name;
      }
      console.log(name);
      return name;
    }

    if (isLoading) {
      return (
      <Fragment>
        <Header />
        <h1>...Loading</h1>
        <Footer />
      </Fragment>);
    }

    return (
      <Fragment>
        <Header />
          <User userId={2} styles={this.styles} />
        <Footer />
      </Fragment>
    );
  }
}
