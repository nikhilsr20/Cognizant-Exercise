function CourseDetails({ courses }) {
  let content;

  if (courses.length === 0) {
    content = <p>No course details available</p>;
  } else {
    content = (
      <ul>
        {courses.map((course) => (
          <li key={course.id}>
            <h2>{course.name}</h2>
            <p>{course.date}</p>
          </li>
        ))}
      </ul>
    );
  }

  return (
    <section className="detail-column">
      <h1>Course Details</h1>
      {content}
    </section>
  );
}

export default CourseDetails;
