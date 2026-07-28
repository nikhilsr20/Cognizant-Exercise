# Git HOL 3 - Branching and Merging

## Description

In this hands-on lab, you will create a branch named `GitNewBranch`, add and commit changes in that branch, compare the branch with the master branch, view visual differences using P4Merge/difftool, merge the branch into master, review the log, and delete the merged branch.

## Commands

### 1. Go to the repository

```bash
cd GitDemo
```

### 2. Check the current status

```bash
git status
```

### 3. Create a new branch

```bash
git branch GitNewBranch
```

### 4. List local and remote branches

```bash
git branch -a
```

The `*` mark shows the current branch.

### 5. Switch to the new branch

```bash
git checkout GitNewBranch
```

Alternative command:

```bash
git switch GitNewBranch
```

### 6. Add or update a file in the branch

```bash
echo "This file is created in GitNewBranch" > branch-file.txt
git status
```

### 7. Stage and commit the changes

```bash
git add branch-file.txt
git commit -m "Add file in GitNewBranch"
```

### 8. Check the status

```bash
git status
```

### 9. Switch to the master branch

```bash
git checkout master
```

If the default branch is `main`:

```bash
git checkout main
```

### 10. Check the differences between master and the branch

```bash
git diff master GitNewBranch
```

If the default branch is `main`:

```bash
git diff main GitNewBranch
```

### 11. Configure P4Merge as the Git difftool

```bash
git config --global diff.tool p4merge
git config --global difftool.p4merge.path "C:/Program Files/Perforce/p4merge.exe"
git config --global difftool.prompt false
```

### 12. View the visual difference in P4Merge

```bash
git difftool master GitNewBranch
```

If the default branch is `main`:

```bash
git difftool main GitNewBranch
```

### 13. Merge the branch into master

```bash
git merge GitNewBranch
```

### 14. Observe the log after merging

```bash
git log --oneline --graph --decorate
```

### 15. Delete the merged branch

```bash
git branch -d GitNewBranch
```

### 16. Verify the final status and branches

```bash
git status
git branch -a
```
