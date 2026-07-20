import { render, screen } from '@testing-library/react';
import App from './App';

test('renders all ordered items and prices', () => {
  render(<App />);

  expect(
    screen.getByRole('heading', { name: 'Items Ordered :' })
  ).toBeInTheDocument();
  expect(screen.getByRole('columnheader', { name: 'Name' })).toBeInTheDocument();
  expect(
    screen.getByRole('columnheader', { name: 'Price' })
  ).toBeInTheDocument();

  ['Laptop', 'TV', 'Washing Machine', 'Mobile', 'Fridge'].forEach((item) => {
    expect(screen.getByText(item)).toBeInTheDocument();
  });

  ['80000', '120000', '50000', '30000', '70000'].forEach((price) => {
    expect(screen.getByText(price)).toBeInTheDocument();
  });
});
