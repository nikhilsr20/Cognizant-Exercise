# ReactJS Hands-on Lab 11

This project is a **ReactJS** application developed as part of **ReactJS Hands-on Lab 11**. It demonstrates **React event handling**, **event handlers**, **Synthetic Events**, and **form submission** through a set of interactive examples.

## Objectives

* Handle events in React components
* Pass arguments to event handlers
* Use the `this` keyword in class components
* Work with React Synthetic Events
* Handle form submission and user input

## Features

* Built using **Create React App**
* Counter with **Increment** and **Decrement** functionality
* Multiple event handlers triggered from a single button
* Button that passes arguments to an event handler
* Demonstration of React Synthetic Events
* Currency Converter with form submission and user input

## Application Overview

The application includes the following examples:

### Counter Example

* **Increment** button increases the counter value.
* **Decrement** button decreases the counter value.
* The **Increment** button also displays the message:

```text id="7b2qf3"
Hello! Member1
```

### Welcome Example

A button displays the following message by passing an argument to the event handler:

```text id="t7jovm"
Welcome
```

### Synthetic Event Example

Clicking the button displays:

```text id="dzpv5b"
I was clicked
```

### Currency Converter

The application accepts an amount and a target currency through a form. On submission, the converted amount is displayed.

Example:

```text id="hjnd0t"
Converting to Euro Amount is 6400
```

## Getting Started

Install the project dependencies:

```bash id="xdpm78"
npm install
```

Start the development server:

```bash id="4h54q5"
npm start
```

Open your browser and visit:

```text id="6n8msl"
http://localhost:3000
```

## Build the Project

Generate an optimized production build:

```bash id="bjlwm4"
npm run build
```

## Run Tests

Execute the test suite:

```bash id="4zt8ek"
npm test -- --watchAll=false
```



