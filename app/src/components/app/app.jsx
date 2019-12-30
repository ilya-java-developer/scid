import React, { Component, Fragment } from "react";
import { Header, Footer } from "../layouts";
import Exercises from "../exercises";
import { muscles, exercises } from "../data/store";

import "./app.css";

export default class App extends Component {
  states = {
    exercises
  };

  getExercisesByMuscles() {
    return Object.entries(
      this.states.exercises.reduce((exercises, exercise) => {
        const { muscles } = exercise;
        exercises[muscles] = exercises[muscles]
          ? [...exercises[muscles], exercise]
          : [exercise];
        return exercises;
      }, {})
    );
  }

  render() {
    const exercises = this.getExercisesByMuscles();
    return (
      <Fragment>
        <Header />
        <Exercises exercises={exercises} />
        <Footer muscles={muscles} />
      </Fragment>
    );
  }
}
