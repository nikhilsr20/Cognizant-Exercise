import React, { Component } from 'react';
import Post from './Post';
import './Posts.css';

class Posts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      loading: true,
      error: '',
    };
  }

  async loadPosts() {
    try {
      const response = await fetch('https://jsonplaceholder.typicode.com/posts');

      if (!response.ok) {
        throw new Error('Unable to load posts');
      }

      const data = await response.json();
      const posts = data.map(
        ({ id, title, body }) => new Post(id, title, body)
      );

      this.setState({ posts, loading: false, error: '' });
    } catch (error) {
      this.setState({ posts: [], loading: false, error: error.message });
    }
  }

  componentDidMount() {
    this.loadPosts();
  }

  componentDidCatch(error) {
    this.setState({ loading: false, error: error.message });
    window.alert(`An error occurred: ${error.message}`);
  }

  render() {
    const { posts, loading, error } = this.state;

    if (loading) {
      return <p className="status">Loading posts...</p>;
    }

    if (error) {
      return <p className="status error">{error}</p>;
    }

    return (
      <main className="posts">
        <h1>Blog Posts</h1>
        {posts.map((post) => (
          <article className="post" key={post.id}>
            <h2>{post.title}</h2>
            <p>{post.body}</p>
          </article>
        ))}
      </main>
    );
  }
}

export default Posts;
