# Git HOL 1 - Git Setup, Notepad++ Integration, First Commit

## Description

In this hands-on lab, you will verify the Git client installation, configure your user name and email, set Notepad++ as the default Git editor, create a local repository, add and commit a `welcome.txt` file, and then pull from and push to a GitLab remote repository.

## Commands

### 1. Check whether Git is installed

```bash
git --version
```

### 2. Configure Git user name and email

```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

### 3. Verify the configuration

```bash
git config --global --list
```

### 4. Check whether Notepad++ opens from Git Bash

```bash
notepad++
```

If the command does not work, add the Notepad++ installation path to the Windows Environment Variable `Path`. Common path:

```text
C:\Program Files\Notepad++\
```

Close Git Bash, open it again, and then check:

```bash
notepad++
```

### 5. Create an alias for Notepad++

```bash
alias notepad++="/c/Program\ Files/Notepad++/notepad++.exe"
```

To make the alias permanent:

```bash
notepad++ ~/.bash_profile
```

Add this line to the file:

```bash
alias notepad++="/c/Program\ Files/Notepad++/notepad++.exe"
```

Reload the profile:

```bash
source ~/.bash_profile
```

### 6. Set Notepad++ as the default Git editor

```bash
git config --global core.editor "notepad++ -multiInst -notabbar -nosession -noPlugin"
```

Verify it:

```bash
git config --global -e
```

To view the complete global configuration:

```bash
git config --global --list
```

### 7. Create a local repository

```bash
mkdir GitDemo
cd GitDemo
git init
```

Verify the hidden Git folder:

```bash
ls -la
```

### 8. Create the `welcome.txt` file

```bash
echo "Welcome to Git hands-on lab" > welcome.txt
```

Verify the file:

```bash
ls
cat welcome.txt
```

### 9. Check Git status

```bash
git status
```

### 10. Add the file to the staging area

```bash
git add welcome.txt
git status
```

### 11. Commit the file

To open the editor and write a multi-line commit message:

```bash
git commit
```

To commit with a direct message:

```bash
git commit -m "Add welcome file"
```

### 12. Connect the GitLab remote repository

Create a repository named `GitDemo` in GitLab. Then use its URL:

```bash
git remote add origin <gitlab-repository-url>
git remote -v
```

### 13. Pull from the remote repository

```bash
git pull origin master
```

If you get an unrelated history error:

```bash
git pull origin master --allow-unrelated-histories
```

### 14. Push the local repository to the remote repository

```bash
git push -u origin master
```

If the default branch is `main`:

```bash
git branch -M main
git push -u origin main
```
