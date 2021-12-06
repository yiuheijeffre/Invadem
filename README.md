# Invadem

## Running Invadem require:
 - Gradle
 - XMing

Run the following command in terminal to open a new window and play Invadem:
```
export DISPLAY=":0"
gradle build
gradle run
```

If it fails to connect to X11 window server, try entering the following:
```
export DISPLAY=":1"
gradle build
gradle run
```
