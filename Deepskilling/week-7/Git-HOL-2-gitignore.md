# Git HOL 2 - Ignore Unwanted Files Using `.gitignore`

## Description

In this hands-on lab, you will ignore files with the `.log` extension and the `log` folder from Git tracking. You will update the `.gitignore` file, verify the status, and commit only the `.gitignore` file.

## Commands

### 1. Go to the repository

```bash
cd GitDemo
```

### 2. Check whether the working tree is clean

```bash
git status
```

### 3. Create a `.log` file and a `log` folder

```bash
echo "temporary log data" > app.log
mkdir log
echo "folder log data" > log/server.log
```

### 4. Create or update the `.gitignore` file

```bash
notepad++ .gitignore
```

Add these lines to `.gitignore`:

```gitignore
*.log
log/
```

To add them from the command line:

```bash
printf "*.log\nlog/\n" >> .gitignore
```

### 5. Verify Git status

```bash
git status
```

Expected result: `app.log` and the `log/` folder should not appear as untracked files. Only `.gitignore` should appear.

### 6. Stage and commit `.gitignore`

```bash
git add .gitignore
git commit -m "Ignore log files and log folder"
```

### 7. Check the final status

```bash
git status
```

### 8. Push to the remote repository

```bash
git push origin master
```

If the branch is `main`:

```bash
git push origin main
```
