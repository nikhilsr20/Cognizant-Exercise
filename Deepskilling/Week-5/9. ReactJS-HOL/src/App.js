import './App.css';
import IndianPlayers from './IndianPlayers';
import ListofPlayers from './ListofPlayers';

function App() {
  const flag = true;

  if (flag) {
    return <ListofPlayers />;
  }
  return <IndianPlayers />;
}

export default App;
