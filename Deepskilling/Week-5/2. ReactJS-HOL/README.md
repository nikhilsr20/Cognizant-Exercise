# ReactJS Hands-on Lab 2

This project is a simple **ReactJS** application developed as part of **ReactJS Hands-on Lab 2**. It demonstrates how to create and render multiple **React class components** within a single application.

## Objective

The application displays three separate pages using React class components.

| Component   | Message Displayed                                            |
| ----------- | ------------------------------------------------------------ |
| **Home**    | Welcome to the Home page of Student Management Portal        |
| **About**   | Welcome to the About page of the Student Management Portal   |
| **Contact** | Welcome to the Contact page of the Student Management Portal |

## Project Structure

```text
StudentApp/
│
├── output/
├── public/
├── src/
│   ├── Components/
│   │   ├── Home.js
│   │   ├── About.js
│   │   └── Contact.js
│   ├── App.css
│   ├── App.js
│   └── index.js
├── package.json
└── README.md
```

## Features

* Built using **Create React App**
* Uses **React Class Components**
* Demonstrates component creation and rendering
* Clean and simple project structure
* Basic CSS styling for layout

## Components

### Home

Displays:

```text
Welcome to the Home page of Student Management Portal
```

### About

Displays:

```text
Welcome to the About page of the Student Management Portal
```

### Contact

Displays:

```text
Welcome to the Contact page of the Student Management Portal
```

## Main Application

`App.js` imports and renders all three components.

```javascript
import './App.css';
import Home from './Components/Home';
import About from './Components/About';
import Contact from './Components/Contact';

function App() {
  return (
    <div className="container">
      <Home />
      <About />
      <Contact />
    </div>
  );
}

export default App;
```

## Getting Started

Install the project dependencies:

```bash
npm install
```

Start the development server:

```bash
npm start
```

Open your browser and visit:

```text
http://localhost:3000
```

## Build the Project

Generate an optimized production build:

```bash
npm run build
```

## Run Tests

Execute the test suite:

```bash
npm test -- --watchAll=false
```




