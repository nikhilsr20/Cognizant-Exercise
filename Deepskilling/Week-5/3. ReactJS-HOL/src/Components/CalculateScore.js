import '../Stylesheets/mystyle.css';

const percentToDecimal = (decimal) => `${decimal.toFixed(2)}%`;

const calculateScore = (total, goal) => {
  if (goal === 0) {
    return '0.00%';
  }

  return percentToDecimal(total / goal);
};

function CalculateScore({ Name, School, total, goal }) {
  return (
    <section className="student-details">
      <h1>Student Details:</h1>
      <div className="name">
        <strong>Name:</strong> <span>{Name}</span>
      </div>
      <div className="school">
        <strong>School:</strong> <span>{School}</span>
      </div>
      <div className="total">
        <strong>Total:</strong> <span>{total} Marks</span>
      </div>
      <div className="score">
        <strong>Score:</strong> <span>{calculateScore(total, goal)}</span>
      </div>
    </section>
  );
}

export default CalculateScore;
