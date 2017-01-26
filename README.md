# Getting Started


## MQL Service

### Execute
   Run this command in the root directory <br/> <br/>
   ```./gradlew mqlservice:run```

## Demo

**Step 1**: Make sure that [nodeJS](https://nodejs.org/en/) is installed. I suggest using a node version manager.  
  * [nvm](https://github.com/creationix/nvm) -- Mac or Linux.
  * [nvm-windows](https://github.com/coreybutler/nvm-windows) -- Windows.

**Step 2**: Install global dependencies. Execute these commands anywhere in the command line
  * [TypeScript](https://github.com/Microsoft/TypeScript) -- Superset language for clean Javascript
  * [Typings](https://github.com/typings/typings) -- TypeScript Definition Manager
  * [Gulp](https://github.com/gulpjs/gulp) -- Streaming build system
  * [Webpack](https://github.com/webpack/webpack) -- Module bundler

```shell
$ npm install -g typescript
$ npm install -g typings
$ npm install -g gulp-cli
$ npm install -g webpack
```
**Step 3**: Install project dependencies.
Go to the demo directory of the project and execute the instruction below from the command line.
These dependencies can be found in package.json.

```shell
$ npm install
```
**Step 4**: Build and start the server from the command line in the demo directory.

```shell
$ gulp serve
```

# Contributors

* Alexandre Désilets-Aubé
* Nicolas Cotton
* Xavier Kedzierski Elgstrand
