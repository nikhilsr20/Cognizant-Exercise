import { Component } from 'react';

class CountPeople extends Component {
  constructor(props) {
    super(props);
    this.state = {
      entrycount: 0,
      exitcount: 0,
    };
    this.updateEntry = this.updateEntry.bind(this);
    this.updateExit = this.updateExit.bind(this);
  }

  updateEntry() {
    this.setState((previousState) => ({
      entrycount: previousState.entrycount + 1,
    }));
  }

  updateExit() {
    this.setState((previousState) => ({
      exitcount: previousState.exitcount + 1,
    }));
  }

  render() {
    const { entrycount, exitcount } = this.state;

    return (
      <main className="people-counter">
        <section className="counter-item">
          <button type="button" onClick={this.updateEntry}>
            Login
          </button>
          <span>{entrycount} People Entered!!!</span>
        </section>
        <section className="counter-item">
          <button type="button" onClick={this.updateExit}>
            Exit
          </button>
          <span>{exitcount} People Left!!!</span>
        </section>
      </main>
    );
  }
}

export default CountPeople;
