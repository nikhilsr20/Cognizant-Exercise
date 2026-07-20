import './App.css';
import BlogDetails from './components/BlogDetails';
import BookDetails from './components/BookDetails';
import CourseDetails from './components/CourseDetails';

const books = [
  { id: 101, name: 'Master React', price: 670 },
  { id: 102, name: 'Deep Dive into Angular 11', price: 800 },
  { id: 103, name: 'Mongo Essentials', price: 450 },
];

const blogs = [
  {
    id: 201,
    title: 'React Learning',
    author: 'Stephen Biz',
    description: 'Welcome to learning React!',
  },
  {
    id: 202,
    title: 'Installation',
    author: 'Schwezdneier',
    description: 'You can install React from npm.',
  },
];

const courses = [
  { id: 301, name: 'Angular', date: '4/5/2021' },
  { id: 302, name: 'React', date: '6/3/20201' },
];

const renderBlogDetails = (shouldShow) =>
  shouldShow ? <BlogDetails blogs={blogs} /> : null;

function App() {
  const showCourses = true;
  const showBooks = true;
  const showBlogs = true;

  return (
    <main className="details-page">
      {showCourses && <CourseDetails courses={courses} />}
      {showBooks ? <BookDetails books={books} /> : null}
      {renderBlogDetails(showBlogs)}
    </main>
  );
}

export default App;
