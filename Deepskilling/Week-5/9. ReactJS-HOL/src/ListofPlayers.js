const players = [
  { name: 'Jack', score: 50 },
  { name: 'Michael', score: 70 },
  { name: 'John', score: 40 },
  { name: 'Ann', score: 61 },
  { name: 'Elisabeth', score: 61 },
  { name: 'Sachin', score: 95 },
  { name: 'Dhoni', score: 100 },
  { name: 'Virat', score: 84 },
  { name: 'Jadeja', score: 64 },
  { name: 'Raina', score: 75 },
  { name: 'Rohit', score: 80 },
];

const PlayerList = ({ playersToDisplay }) => (
  <ul>
    {playersToDisplay.map(({ name, score }) => (
      <li key={name}>
        Mr. {name} <span>{score}</span>
      </li>
    ))}
  </ul>
);

function ListofPlayers() {
  const playersBelow70 = players.filter(({ score }) => score <= 70);

  return (
    <main className="cricket-page">
      <section>
        <h1>List of Players</h1>
        <PlayerList playersToDisplay={players} />
      </section>
      <section>
        <h1>List of Players having Scores Less than 70</h1>
        <PlayerList playersToDisplay={playersBelow70} />
      </section>
    </main>
  );
}

export { players };
export default ListofPlayers;
