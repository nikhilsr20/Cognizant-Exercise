import { render, screen } from '@testing-library/react';
import App from './App';
import IndianPlayers from './IndianPlayers';

test('renders the full player list and filtered players', () => {
  render(<App />);

  expect(
    screen.getByRole('heading', { name: 'List of Players' })
  ).toBeInTheDocument();
  expect(
    screen.getByRole('heading', {
      name: 'List of Players having Scores Less than 70',
    })
  ).toBeInTheDocument();
  expect(screen.getByText('Mr. Sachin')).toBeInTheDocument();
  expect(screen.getAllByText('Mr. Jack')).toHaveLength(2);
  expect(screen.getAllByText('Mr. Michael')).toHaveLength(2);
});

test('renders destructured and merged Indian players', () => {
  render(<IndianPlayers />);

  expect(screen.getByText('First : Sachin1')).toBeInTheDocument();
  expect(screen.getByText('Second : Dhoni2')).toBeInTheDocument();
  expect(screen.getByText('Fifth : Yuvraj5')).toBeInTheDocument();
  expect(screen.getByText('Sixth : Raina6')).toBeInTheDocument();
  expect(screen.getByText('Mr. First Player')).toBeInTheDocument();
  expect(screen.getByText('Mr. Sixth Player')).toBeInTheDocument();
});
