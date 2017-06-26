[![Build Status](https://travis-ci.org/fstiehle/de.htwg.sa.nmm.svg?branch=master)](https://travis-ci.org/fstiehle/de.htwg.sa.nmm)

# de.htwg.sa.nmm
HTWG Konstanz | Software Architecture | Nine Men's Morris

## NMM Mill Service

<https://github.com/FunkeMT/nmm.service.mill>

## DB Scheme

> MySQL Workbench
>
> <https://dev.mysql.com/downloads/workbench/>

![db_scheme](https://cdn.rawgit.com/fstiehle/de.htwg.sa.nmm/master/misc/doc/db/nmm.svg)

## Docker

* clone repo
* start docker (vm)
* build docker image
    * run inside repo:
    * `docker build -t nmm:game .`
* run docker container
    * start in background:
    ```
    docker run -d -i -p 8080:8080 --name nmm-game  nmm:game
    ```

    * start in foreground and interactive:
    ```
    docker run -i -a stdout -p 8080:8080 --name nmm-game-i  nmm:game
    ```
* stop docker container
    ```
    docker stop nmm-game[-i]
    ```
* start docker container again
    * non-interactive
    ```
    docker start nmm-game
    ```
    * interactive
    ```
    docker start -a nmm-game-i
    ```
