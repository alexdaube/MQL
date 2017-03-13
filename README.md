# Multitel Query Language (MQL)
<i>A user friendly language to query the database</i>

# Configurations

Entities and their respective attributes must be specified inside the 
json file found at this path:
<i>mqlservice/src/main/configuration/entities_config.json</i>
<br><b>The file must follow this format</b>:
  ```
  {
    "entities": [
      {
        "keyword": "Site",
        "foreign_keys": [],
        "synonyms": [
          "plants",
          "building"
        ],
        "attributes": [
          {
            "keyword": "SiteId",
            "synonyms": [
              "id"
            ]
          },
          {
            "keyword": "Name",
            "synonyms": []
          },
        ]
      }
    ]
  }
  ```
It is important not to modify the junction 
and operator configurations files!

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
  ```shell
  ./gradlew mqlservice:run
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
# Tests


**Front-end Tests**  <i>(From the demo directory)</i>
```shell
$ npm test
```

**Backend Tests**
```shell
./gradlew mqlservice:test
```


# Contributors

* Alexandre Désilets-Aubé
* Nicolas Cotton
* Xavier Kedzierski Elgstrand
