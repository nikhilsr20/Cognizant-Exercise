# ReactJS Hands-on Lab 10

This project is a **ReactJS** application developed as part of **ReactJS Hands-on Lab 10**. It demonstrates the use of **JSX**, **JavaScript expressions**, **inline CSS**, and **conditional rendering** to display office space rental information.

## Objectives

* Use **JSX** syntax in React applications.
* Render JSX elements to the DOM.
* Display office details using JavaScript objects.
* Apply **conditional inline styling** based on office rent.

## Features

* Built using **Create React App**
* Uses JSX for rendering UI elements
* Displays office details from JavaScript objects
* Renders a list of office spaces
* Applies conditional inline CSS for rent values
* Clean and simple user interface

## Application Overview

The application displays:

* A page heading
* An office space image
* Office details including:

  * Name
  * Rent
  * Address
* A list of available office spaces

The **Rent** value is styled dynamically using inline CSS:

* **Red** – Rent below **₹60,000**
* **Green** – Rent above **₹60,000**

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


