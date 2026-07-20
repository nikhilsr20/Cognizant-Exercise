import { Component } from 'react';

class Cart extends Component {
  render() {
    const { items } = this.props;

    return (
      <table className="cart-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          {items.map((item) => (
            <tr key={item.itemname}>
              <td>{item.itemname}</td>
              <td>{item.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
    );
  }
}

Cart.defaultProps = {
  items: [],
};

export default Cart;
