# ReactJS Hands-on Lab 12

This project is a **ReactJS** application developed as part of **ReactJS Hands-on Lab 12**. It demonstrates **conditional rendering** by displaying different components based on the user's authentication status.

## Objectives

* Implement conditional rendering in React
* Display different UI for authenticated and guest users
* Toggle between Login and Logout states
* Manage component rendering based on application state

## Features

* Built using **Create React App**
* Demonstrates React conditional rendering
* Login and Logout functionality
* Dynamic greeting messages
* Simple authentication state management

## Application Overview

The application displays different content depending on whether the user is logged in.

### Guest View

The application displays:

* **Please sign up.**
* **Login** button

### Logged-in View

The application displays:

* **Welcome back**
* **Logout** button

The UI updates automatically when the user logs in or logs out.

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


