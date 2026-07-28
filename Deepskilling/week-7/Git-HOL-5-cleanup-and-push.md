# Git HOL 5 - Cleanup and Push Back to Remote Git



## Commands

### 1. Go to the repository

```bash
cd GitDemo
```

### 2. Verify that master is in a clean state

```bash
git checkout master
git status
```

If the default branch is `main`:

```bash
git checkout main
git status
```

Expected result:

```text
nothing to commit, working tree clean
```

### 3. List the available branches

```bash
git branch -a
```

### 4. Pull the latest changes from the remote repository

```bash
git pull origin master
```

If the branch is `main`:

```bash
git pull origin main
```

### 5. Push pending local commits to the remote repository

```bash
git push origin master
```

If the branch is `main`:

```bash
git push origin main
```

### 6. Verify the remote tracking status

```bash
git status
```

Expected result:

```text
Your branch is up to date with 'origin/master'.
nothing to commit, working tree clean
```

For `main`, expected branch text:

```text
Your branch is up to date with 'origin/main'.
nothing to commit, working tree clean
```

### 7. Verify the recent commit history

```bash
git log --oneline --graph --decorate --all
```

### 8. Verify in GitLab

Open the GitLab repository and check:

- `welcome.txt` is available.
- `.gitignore` is available.
- Branch merge commits are visible.
- `hello.xml` is available with the final content after conflict resolution.
