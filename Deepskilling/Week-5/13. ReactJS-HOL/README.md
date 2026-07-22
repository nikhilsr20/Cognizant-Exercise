# ReactJS Hands-on Lab 13

This project is a **ReactJS** application developed as part of **ReactJS Hands-on Lab 13**. It demonstrates **conditional rendering**, **list rendering**, the **`map()`** function, **React keys**, and component composition.

## Objectives

* Render multiple React components
* Display lists using the `map()` function
* Assign unique keys to list items
* Implement conditional rendering
* Organize the application using reusable components

## Features

* Built using **Create React App**
* Demonstrates list rendering with `map()`
* Uses unique keys for efficient rendering
* Implements multiple conditional rendering techniques
* Modular component-based architecture
* Displays books, blogs, and courses in separate sections

## Application Overview

The application consists of three main components:

### Course Details

Displays a list of available courses along with their respective dates.

### Book Details

Displays a collection of books using the `map()` function. Each book item is rendered with a unique React key.

### Blog Details

Displays blog information including:

* Title
* Author
* Description

The application also demonstrates different approaches to conditional rendering, including:

* Logical `&&`
* Ternary operator
* Returning `null`
* Helper functions

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

