# ReactJS Hands-on Lab 8

This project is a simple **ReactJS** application developed as part of **ReactJS Hands-on Lab 8**. It demonstrates the use of **React State** by implementing a mall entry and exit counter.

## Objective

Create a React application named **counterapp** with a class component called **CountPeople** that keeps track of:

* **Entry Count** – Number of people entering the mall.
* **Exit Count** – Number of people leaving the mall.

## Features

* Built using **Create React App**
* Uses **React Class Components**
* Demonstrates React **State** management
* Updates the UI dynamically when the state changes
* Simple and interactive counter application

## How It Works

The application maintains two state variables:

* `entrycount` – Stores the total number of entries.
* `exitcount` – Stores the total number of exits.

Two buttons allow users to update the counters:

* **Login** – Increments the entry counter.
* **Exit** – Increments the exit counter.

Whenever a button is clicked, the corresponding state value is updated using `setState()`, and React automatically re-renders the component to display the latest count.

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

Create an optimized production build:

```bash
npm run build
```

## Run Tests

Execute the test suite:

```bash
npm test -- --watchAll=false

