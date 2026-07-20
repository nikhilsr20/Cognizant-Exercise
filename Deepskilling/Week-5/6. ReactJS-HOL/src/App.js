import './App.css';
import CohortDetails from './CohortDetails';

const cohorts = [
  {
    code: 'INTADMDF10',
    technology: '.NET FSD',
    startDate: '22-Feb-2022',
    status: 'Scheduled',
    coach: 'Aathma',
    trainer: 'Jojo Jose',
  },
  {
    code: 'ADM21JF014',
    technology: 'Java FSD',
    startDate: '10-Sep-2021',
    status: 'Ongoing',
    coach: 'Apoorv',
    trainer: 'Elisa Smith',
  },
  {
    code: 'CDBJF21025',
    technology: 'Java FSD',
    startDate: '24-Dec-2021',
    status: 'Ongoing',
    coach: 'Aathma',
    trainer: 'John Doe',
  },
];

function App() {
  return (
    <main className="dashboard">
      <h1>Cohorts Details</h1>
      <section className="cohort-list">
        {cohorts.map((cohort) => (
          <CohortDetails cohort={cohort} key={cohort.code} />
        ))}
      </section>
    </main>
  );
}

export default App;
