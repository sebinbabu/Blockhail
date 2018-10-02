# Blockhail

That classic block game with some added unnecessary features.

![Blockhail](https://github.com/sebinbabu/Blockhail/raw/master/Blockhail.png)

### Prerequisites

You need to install the following two packages: 
* gradle (to build the source)
* JDK 1.8 or higher

## Features

* Load / save all the moves in the current gameplay
* Undo / redo moves
* Replay : (wittybox.blockhail.Player) to record and replay a gameplay

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. Open up your terminal after installing the prerequisites and put these commands.

Install gradle from [here](https://gradle.org/install/)

```
git clone https://github.com/sebinbabu/Blockhail.git
cd Blockhail
gradle build
```
To invoke the game:
```
java -jar build/libs/Blockhail.jar
```
To invoke the replayer:
```
java -cp build/libs/Blockhail.jar wittybox.blockhail.Player
```

## Usage

* Use the ```java wittybox.blockhail.Main``` command to run the application.
* Use the ```java wittybox.blockhail.Player``` command to run the replayer.
* Press ```s key``` to start / pause.
* Press ```z key``` to save state.
* Press ```x key``` to load state.
* Press ```ARROW_LEFT key``` to move left.
* Press ```ARROW_RIGHT key``` to move right.
* Press ```ARROW_DOWN key``` to move down.
* Press ```ARROW_UP key``` to rotate.
* Press ```u key``` to undo.
* Press ```i key``` to redo.


## Authors / Credits

* **Sebin Babu** - *Initial work* - [Sebin Babu](https://github.com/sebinbabu)

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE.md](LICENSE.md) file for details

