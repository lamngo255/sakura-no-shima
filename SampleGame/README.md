# TratTeam-Game-Project

© Trất's Team - Official Game Repository


## Note 
- "master" is the most important branch. Make sure to finish your work on your branch before pushing to this one.
- Download Git-scm here: https://git-scm.com/download/win

# Guidelines (Git manual)
And here's some most commonly used Git commands:

### Git clone (Https)
```sh
$ git clone https://github.com/mocmeo/tratteam-game-project.git
```

### Set up username and email:
```sh
$ git config --global user.email "<your_email>"
$ git config --global user.name "<your_name>"
```

### Open current directory's location: 
```sh
$ explorer .
```

### Git flow:
```sh
$ git add .
$ git status -s
$ git commit -m "<commit_message>"
$ git push origin <your_branch>
$ git pull origin master
```

### Create new branch
```sh
$ git checkout -b <branch_name>
$ git push origin <branch_name>
```

### Edit commit message (in case of merging two commits)
```sh
$ git commit --amend
$ git push -f origin <your_branch>
```

### Delete commit
```sh
$ git log
$ git reset 7f6d03 --hard
$ git push -f origin master
```