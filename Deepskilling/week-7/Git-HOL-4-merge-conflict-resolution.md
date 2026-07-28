# Git HOL 4 - Merge Conflict Resolution

## Description

In this hands-on lab, you will create and resolve a merge conflict. You will create or update `hello.xml` in the `GitWork` branch, add the same file with different content in the master branch, merge the branch to trigger a conflict, resolve the conflict manually or with P4Merge mergetool, and commit the resolved file. At the end, you will add backup files to `.gitignore` and delete the merged branch.

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

### 3. Create and switch to the `GitWork` branch

```bash
git checkout -b GitWork
```

Alternative:

```bash
git switch -c GitWork
```

### 4. Add `hello.xml` in the branch

```bash
cat > hello.xml <<'EOF'
<message>
  <text>Hello from GitWork branch</text>
</message>
EOF
```

### 5. Check the status

```bash
git status
```

### 6. Commit the branch changes

```bash
git add hello.xml
git commit -m "Add hello XML in GitWork branch"
```

### 7. Switch to the master branch

```bash
git checkout master
```

If the default branch is `main`:

```bash
git checkout main
```

### 8. Add the same file in master with different content

```bash
cat > hello.xml <<'EOF'
<message>
  <text>Hello from master branch</text>
</message>
EOF
```

### 9. Commit the master changes

```bash
git add hello.xml
git commit -m "Add hello XML in master branch"
```

### 10. Observe the complete graph log

```bash
git log --oneline --graph --decorate --all
```

### 11. Check the command line diff

```bash
git diff master GitWork
```

If the default branch is `main`:

```bash
git diff main GitWork
```

### 12. Configure P4Merge as the difftool

```bash
git config --global diff.tool p4merge
git config --global difftool.p4merge.path "C:/Program Files/Perforce/p4merge.exe"
git config --global difftool.prompt false
```

### 13. View the visual diff with P4Merge

```bash
git difftool master GitWork
```

If the default branch is `main`:

```bash
git difftool main GitWork
```

### 14. Merge the branch into master

```bash
git merge GitWork
```

A conflict will occur here because `hello.xml` was changed with different content in both branches.

### 15. Observe the conflict markup

```bash
cat hello.xml
git status
```

Conflict markers example:

```text
<<<<<<< HEAD
master branch content
=======
GitWork branch content
>>>>>>> GitWork
```

### 16. Configure P4Merge as the mergetool

```bash
git config --global merge.tool p4merge
git config --global mergetool.p4merge.path "C:/Program Files/Perforce/p4merge.exe"
git config --global mergetool.prompt false
```

### 17. Resolve the conflict using the 3-way merge tool

```bash
git mergetool
```

To resolve it manually, edit the file:

```bash
notepad++ hello.xml
```

Resolved example:

```xml
<message>
  <text>Hello from master and GitWork branch</text>
</message>
```

### 18. Stage and commit the resolved file

```bash
git add hello.xml
git commit -m "Resolve merge conflict in hello XML"
```

### 19. Add backup files to `.gitignore`

P4Merge/mergetool backup files are usually created with the `.orig` extension.

```bash
printf "*.orig\n" >> .gitignore
git add .gitignore
git commit -m "Ignore merge backup files"
```

### 20. List the available branches

```bash
git branch -a
```

### 21. Delete the merged branch

```bash
git branch -d GitWork
```

### 22. Observe the final log

```bash
git log --oneline --graph --decorate
```
