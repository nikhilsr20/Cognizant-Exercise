import { useEffect, useState } from 'react';
import './Getuser.css';

function Getuser() {
  const [person, setPerson] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const getUser = async () => {
      const url = 'https://api.randomuser.me/';
      const response = await fetch(url);
      const data = await response.json();
      setPerson(data.results[0]);
      setLoading(false);
    };

    getUser();
  }, []);

  if (loading) {
    return <h2>Loading...</h2>;
  }

  return (
    <div className="user-container">
      <h1>{person.name.title} {person.name.first}</h1>
      <img src={person.picture.large} alt={person.name.first} />
    </div>
  );
}

export default Getuser;
