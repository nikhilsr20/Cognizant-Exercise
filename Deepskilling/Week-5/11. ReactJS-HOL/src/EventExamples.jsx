import { Component } from 'react';

class EventExamples extends Component {
  constructor(props) {
    super(props);
    this.state = {
      counter: 0,
    };
    this.increment = this.increment.bind(this);
    this.decrement = this.decrement.bind(this);
    this.sayHello = this.sayHello.bind(this);
    this.handleIncrement = this.handleIncrement.bind(this);
    this.sayWelcome = this.sayWelcome.bind(this);
    this.handleSyntheticEvent = this.handleSyntheticEvent.bind(this);
  }

  increment() {
    this.setState((previousState) => ({
      counter: previousState.counter + 1,
    }));
  }

  decrement() {
    this.setState((previousState) => ({
      counter: previousState.counter - 1,
    }));
  }

  sayHello() {
    window.alert('Hello! Member!');
  }

  handleIncrement() {
    this.increment();
    this.sayHello();
  }

  sayWelcome(message) {
    window.alert(message);
  }

  handleSyntheticEvent(event) {
    window.alert('I was clicked');
  }

  render() {
    return (
      <section className="event-examples">
        <p className="counter-value">{this.state.counter}</p>
        <button type="button" onClick={this.handleIncrement}>
          Increment
        </button>
        <button type="button" onClick={this.decrement}>
          Decrement
        </button>
        <button type="button" onClick={() => this.sayWelcome('welcome')}>
          Say welcome
        </button>
        <button type="button" onClick={this.handleSyntheticEvent}>
          Click on me
        </button>
      </section>
    );
  }
}

export default EventExamples;
