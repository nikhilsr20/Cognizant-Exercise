import { Component } from 'react';

class CurrencyConvertor extends Component {
  constructor(props) {
    super(props);
    this.state = {
      amount: '',
      currency: '',
      result: '',
    };
    this.handleAmountChange = this.handleAmountChange.bind(this);
    this.handleCurrencyChange = this.handleCurrencyChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleAmountChange(event) {
    this.setState({ amount: event.target.value });
  }

  handleCurrencyChange(event) {
    this.setState({ currency: event.target.value });
  }

  handleSubmit(event) {
    event.preventDefault();
    const convertedAmount = Number(this.state.amount) * 80;
    const result = `Converting to Euro Amount is ${convertedAmount}`;
    this.setState({ result });
    window.alert(result);
  }

  render() {
    return (
      <section className="currency-converter">
        <h1>Currency Convertor!!!</h1>
        <form onSubmit={this.handleSubmit}>
          <label htmlFor="amount">Amount:</label>
          <input
            id="amount"
            type="number"
            value={this.state.amount}
            onChange={this.handleAmountChange}
          />
          <label htmlFor="currency">Currency:</label>
          <textarea
            id="currency"
            value={this.state.currency}
            onChange={this.handleCurrencyChange}
          />
          <button type="submit">Submit</button>
        </form>
        {this.state.result && <p className="conversion-result">{this.state.result}</p>}
      </section>
    );
  }
}

export default CurrencyConvertor;
