function BlogDetails({ blogs }) {
  return (
    <section className="detail-column">
      <h1>Blog Details</h1>
      {blogs.length ? (
        <ul>
          {blogs.map((blog) => (
            <li key={blog.id}>
              <h2>{blog.title}</h2>
              <h3>{blog.author}</h3>
              <p>{blog.description}</p>
            </li>
          ))}
        </ul>
      ) : (
        <p>No blog details available</p>
      )}
    </section>
  );
}

export default BlogDetails;
