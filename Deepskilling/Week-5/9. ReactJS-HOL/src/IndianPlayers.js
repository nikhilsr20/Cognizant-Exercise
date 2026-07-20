const IndianTeam = ['Sachin1', 'Dhoni2', 'Virat3', 'Rohit4', 'Yuvraj5', 'Raina6'];
const T20Players = ['First Player', 'Second Player', 'Third Player'];
const RanjiTrophyPlayers = ['Fourth Player', 'Fifth Player', 'Sixth Player'];
const IndianPlayersList = [...T20Players, ...RanjiTrophyPlayers];

const OddPlayers = ([first, , third, , fifth]) => (
  <ul>
    <li>First : {first}</li>
    <li>Third : {third}</li>
    <li>Fifth : {fifth}</li>
  </ul>
);

const EvenPlayers = ([, second, , fourth, , sixth]) => (
  <ul>
    <li>Second : {second}</li>
    <li>Fourth : {fourth}</li>
    <li>Sixth : {sixth}</li>
  </ul>
);

const ListofIndianPlayers = ({ players }) => (
  <ul>
    {players.map((player) => (
      <li key={player}>Mr. {player}</li>
    ))}
  </ul>
);

function IndianPlayers() {
  return (
    <main className="cricket-page">
      <section>
        <h1>Odd Players</h1>
        {OddPlayers(IndianTeam)}
      </section>
      <section>
        <h1>Even Players</h1>
        {EvenPlayers(IndianTeam)}
      </section>
      <section>
        <h1>List of Indian Players Merged:</h1>
        <ListofIndianPlayers players={IndianPlayersList} />
      </section>
    </main>
  );
}

export {
  EvenPlayers,
  IndianPlayersList,
  IndianTeam,
  ListofIndianPlayers,
  OddPlayers,
};
export default IndianPlayers;
