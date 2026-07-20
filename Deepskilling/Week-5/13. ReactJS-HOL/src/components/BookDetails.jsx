function BookDetails({ books }) {
  if (!books) {
    return null;
  }

  const bookList =
    books.length > 0 ? (
      <ul>
        {books.map((book) => (
          <li key={book.id}>
            <h2>{book.name}</h2>
            <p>{book.price}</p>
          </li>
        ))}
      </ul>
    ) : (
      <p>No book details available</p>
    );

  return (
    <section className="detail-column">
      <h1>Book Details</h1>
      {bookList}
    </section>
  );
}

export default BookDetails;
