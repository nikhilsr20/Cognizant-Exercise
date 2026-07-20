import './App.css';

const officeSpaces = [
  {
    name: 'DBS',
    rent: 50000,
    address: 'Chennai',
    image: '/office1.jpg',
  },
  {
    name: 'Skyline',
    rent: 75000,
    address: 'Bengaluru',
    image: '/office2.jpg',
  },
  {
    name: 'Tech Park',
    rent: 60000,
    address: 'Hyderabad',
    image: '/office3.jpg',
  },
];

function App() {
  return (
    <main className="office-page">
      <h1>Office Space, at Affordable Range</h1>
      <section className="office-list">
        {officeSpaces.map((office) => {
          const rentColor = office.rent <= 60000 ? 'red' : 'green';

          return (
            <article className="office-card" key={office.name}>
              <img
                src={office.image}
                width="300"
                height="200"
                alt={`${office.name} office space`}
              />
              <h2>Name: {office.name}</h2>
              <h3 style={{ color: rentColor }}>Rent: Rs. {office.rent}</h3>
              <h3>Address: {office.address}</h3>
            </article>
          );
        })}
      </section>
    </main>
  );
}

export default App;
