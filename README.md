# Multitel Query Language (MQL)
<i>A user friendly language to query the database</i>

# Demo

Clone the repository and go to the root directory of the project.

#### Backend

**Step 1**: Make sure that java 8 is installed on the machine.
  * [java 8 installs](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

**Step 2**: Make sure that sqlite 3 is installed and running on the machine.
  * [sqlite3 installs](https://www.tutorialspoint.com/sqlite/sqlite_installation.htm)
  
**Step 3**: Make sure that gradle build tool is installed and working in the command line.
  * [gradle installs](https://gradle.org/install)
  
**Step 4**: Build and run MQL server from the root of the project. Server is listening by default on port 4567.
* **Unix**
  ```
  ./gradlew run
  ```
* **Windows**
  ```
  gradlew run
  ```

#### Front-end

**Step 1**: Make sure that [nodeJS](https://nodejs.org/en/) is installed. I suggest using a node version manager.  
  * [nvm](https://github.com/creationix/nvm) -- Mac or Linux.
  * [nvm-windows](https://github.com/coreybutler/nvm-windows) -- Windows.

**Step 2**: Install global dependencies. Execute these commands anywhere in the command line.
  * [Webpack](https://github.com/webpack/webpack) -- Module bundler

```shell
$ npm install -g webpack
```
**Step 3**: Install project dependencies.
Go to the demo directory of the project and execute the instruction below from the command line.
These dependencies can be found in package.json.

```shell
$ npm install
```
**Step 4**: Build and start the server from the command line in the demo directory. <b>The server is running on port 8080 by default on your local machine</b>.

```shell
$ npm start
```
##### Tests
**Front-end Tests**  <i>(From the demo directory)</i>
```shell
$ npm test
```

**Back-end Tests**
* **Unix** 
  ```
  ./gradlew test
  ```
* **Windows**
  ```
  gradlew test
  ```

# Configurations (CMS)

## Some gotchas using the cms
<b>Operators</b> and <b>Junctions</b> must have specific top level definitions. Synonyms, meanwhile, 
can be anything you want.
- Operators: LESS, GREATER, BETWEEN, LIKE, NOT, OTHER
- Junctions: OR, AND

All <b>Tables</b>, <b>Attributes</b>, and <b>Foreign Keys</b> top level definitions must be
an exact match with the database talking to the MQL service.

## Installation
#### Front-end
**Step 1**: Make sure that [nodeJS](https://nodejs.org/en/) is installed. I suggest using a node version manager.  
  * [nvm](https://github.com/creationix/nvm) -- Mac or Linux.
  * [nvm-windows](https://github.com/coreybutler/nvm-windows) -- Windows.

**Step 2**: Install project dependencies.
Go to the CMS client directory of the project and execute the instruction below from the command line.
These dependencies can be found in package.json.

```shell
$ npm install
```
**Step 3**: Build and start the server from the command line in the CMS client directory. <b>The server is running on port 8000 by default on your local machine</b>.

```shell
$ npm start
```

#### Back-end

**Step 1**: Make sure that [nodeJS](https://nodejs.org/en/) is installed. I suggest using a node version manager.  
  * [nvm](https://github.com/creationix/nvm) -- Mac or Linux.
  * [nvm-windows](https://github.com/coreybutler/nvm-windows) -- Windows.
  
**Step 2**: Make sure that mongoDB 3.4 is installed and running on the machine.
  * [mongoDB 3.4 installs](https://docs.mongodb.com/manual/installation/)

**Step 3**: Install global dependencies. Execute these commands anywhere in the command line.
  * [Webpack](https://github.com/webpack/webpack) -- Module bundler

```shell
$ npm install -g webpack
```

**Step 4**: Install project dependencies.
Go to the CMS api directory of the project and execute the instruction below from the command line.
These dependencies can be found in package.json.

```shell
$ npm install
```
**Step 5**: Build and start the server from the command line in the api directory. <b>The server is running on port 3000 by default on your local machine</b>.

```shell
$ npm start
```
##### Tests
**From the CMS client directory**</i>
```shell
$ npm test
```
**From the CMS api directory**</i>
```shell
$ npm test
```

# Architecture
* https://drive.google.com/file/d/0B3RTyKNvD1VTYy1FRUhZRlRySkU/view?usp=sharing
* https://drive.google.com/file/d/0B3RTyKNvD1VTWHZrQUt6elJEcnM/view?usp=sharing


# TODO
* Implement internalization
* Make user choose between json configurations(re-implementation) and cms configurations (user configuration)
* Improve User experience of cms. As of now, it's too easy for user to make mistakes while defining the language
* Revise cms backend architecture to make it more testable
* Better abstraction for plugging data base with MQL (user configuration)

# Contributors

* Alexandre Désilets-Aubé
* Nicolas Cotton
* Xavier Kedzierski Elgstrand
