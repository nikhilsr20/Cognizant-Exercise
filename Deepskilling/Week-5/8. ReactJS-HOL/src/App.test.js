import { fireEvent, render, screen } from '@testing-library/react';
import App from './App';

test('updates entry and exit counts independently', () => {
  render(<App />);

  const loginButton = screen.getByRole('button', { name: 'Login' });
  const exitButton = screen.getByRole('button', { name: 'Exit' });

  expect(screen.getByText('0 People Entered!!!')).toBeInTheDocument();
  expect(screen.getByText('0 People Left!!!')).toBeInTheDocument();

  fireEvent.click(loginButton);
  fireEvent.click(loginButton);
  fireEvent.click(loginButton);
  fireEvent.click(exitButton);
  fireEvent.click(exitButton);

  expect(screen.getByText('3 People Entered!!!')).toBeInTheDocument();
  expect(screen.getByText('2 People Left!!!')).toBeInTheDocument();
});
