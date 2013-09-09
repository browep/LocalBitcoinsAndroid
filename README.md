LocalBitcoinsAndroid
====================

A sample library for an Android implementation of the Localbitcoins.com api 

Currently it only supports finding local "buy for cash" ads.  It uses device location to find local deals.  Future roadmap [Trello Board](https://trello.com/b/YS5vaxDJ/localbitcoin-android)

*Roadmap Summary*

* oauth2 integration
* escrow view/update
* push message for contacts

Structure
---------

the project has a main maven project that has most of the API interaction, located in `localbtc` and then has an Android implementation in `localbtc-example`.  These design is to de-couple the server interaction from the Android app so the `localbtc` lib can be used in other projects.

Getting Started
---------------

This project uses maven to handle dependencies as well as the [Maven SDK Deployer](https://github.com/mosabua/maven-android-sdk-deployer) to get all relevant Android deps.  It uses [git submodules](http://git-scm.com/book/en/Git-Tools-Submodules) to handle the customized libs.  It uses [Android Volley](https://developers.google.com/events/io/sessions/325304728) to handle the network I/O.

### Building the project ###

- you need to pull all the submodules: `git submodule foreach git pull; git submodule update;`
- follow the instructions for maven sdk deployer.  You should have all of the Android SDK downloaded and installed.
- cd into root directory
- run `mvn package` ( this will build the apks )
- find the apk's in `./localbtc-example/target/localbtc.apk`

Contact
-------

brower.paul@gmail.com

Contributing
------------

Code contributions are always welcome.  Feel free to fork, create Pull Requests.

Tips: 1NLsujn7SVcg3p7CzX3U8uHgCHBiN4tieo

Screenshots
-----------

![ss1](/site/ss1.png)
