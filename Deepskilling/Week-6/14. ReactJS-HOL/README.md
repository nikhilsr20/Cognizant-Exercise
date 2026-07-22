# ReactJS Hands-on Lab 14

This project implements **ReactJS Hands-on Lab 14** by converting theme passing from **props** to the **React Context API** in an employee management application.

## Project

* **Project Name:** `employeesapp`
* **Base Application:** Employee Management App from the embedded project in `14. ReactJS-HOL.docx`



## Implementation

* Created `ThemeContext` using `createContext('light')`.
* Wrapped the application with `ThemeContext.Provider`.
* Removed `theme` prop passing from `App` → `EmployeesList` → `EmployeeCard`.
* Accessed the theme in `EmployeeCard` using `useContext`.
* Applied the selected theme to the **Edit** and **Delete** buttons.

## Run the Project

```bash
npm install
npm start
```
