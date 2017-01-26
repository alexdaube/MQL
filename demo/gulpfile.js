var config = require('./webpack.config.js');
var gulp = require('gulp');
var rename = require('gulp-rename');
var sass = require('gulp-sass');
var webpack = require('webpack-stream');
var autoprefixer = require('gulp-autoprefixer');
var connect = require('gulp-connect');
var open = require('gulp-open');
var clean = require('gulp-clean');
var livereload = require('gulp-livereload');
var argv = require('yargs').argv;

var SERVER_PORT = '8000';

function swallowError(error) {
    console.log(error.toString());
    this.emit('end')
}

gulp.task('ts', function() {
    return gulp.src('src/Index.ts')
        .pipe(webpack(config)).on('error', swallowError)
        .pipe(gulp.dest('.'))
        .pipe(livereload());
});

gulp.task('sass', function() {
    return gulp.src('./scss/app.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(autoprefixer('last 2 version'))
        .pipe(rename('app.css'))
        .pipe(gulp.dest('./dist/css'))
        .pipe(livereload());
});

gulp.task('clean', function() {
    return gulp.src(['dist/css', 'dist/js'], {read: false})
        .pipe(clean());
});

gulp.task('watch', function() {
    livereload.listen();
    gulp.watch(['./**/*.ts', './**/*.tsx'], ['ts']);
    gulp.watch(['./**/*.scss'], ['sass']);
});

gulp.task('connect', function() {
    connect.server({
        root: '',
        port: (argv.port ? argv.port : SERVER_PORT),
        livereload: true
    });
});

gulp.task('open', ['connect'], function() {
    gulp.src('./index.html')
        .pipe(open({uri: 'http://localhost:' + (argv.port ? argv.port : SERVER_PORT)}));
});

gulp.task('serve', ['clean','ts', 'sass', 'open', 'watch'], function() {});

gulp.task('default', ['clean', 'ts', 'sass', 'watch'], function() {});
