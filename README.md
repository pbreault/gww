# gww

> A gradle wrapper "wrapper" for deeply nested projects

## TL;DR

`gww` is a command line application that pre-processes tasks for the [gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html).
It converts shell paths (`a/b/c` ) to gradle project coordinates (`a:b:c`) to let you leverage your shell's path autocompletion. It also allows you to run multiple commands on the same project without repeating the project path.

## Convert shell paths to project coordinates

`gww` will automatically convert all `/` to `:`. This allows you to leverage your shell's autocompletion to figure out
project paths.

```shell
# Slashes are converted to colons
gww feature/my-feature/sample:assemble
 
# Wrap arguments in double-quotes to keep slashes
gww tasks --include-build "a/b/c/d"
```

Note that you still need to use a `:` between projects and tasks.

## Run multiple tasks on the same project

You can chain tasks on project by separating them with `//`.

```shell
# Path coordinates
gww feature/my-feature/sample:clean//assemble//check

# Regular Gradle coordiates
gww :feature:my-feature:sample:clean//assemble//check
```

## Automatically use the  root project's Gradle wrapper

When you are in a subproject, running `gww` will look for a gradle wrapper in parent directories until it finds one.

```shell
cd feature/my-feature/sample
gww tasks # will be evaluated as ../../../gradlew tasks
```

If this is the only feature you are interested in,  [gdubw/gng](https://github.com/gdubw/gng) is a more lightweight
alternative.

# Install

## Brew

```
brew tap pbreault/gww
brew install gww
```

## From source

```shell
./gradlew installDist
ln -nfs $PATH_TO_REPO/app/build/install/gww/bin/gww /usr/local/bin/gww
```

# Limitations

## Path Conversion only works from the root of the project.

Temporary limitation, just haven't immplemented it yet.

```
cd my-project
gww feature/my-feature/sample:assemble # OK

cd feature
gww myfeature/sample:assemble # Not OK, will be evaluated as ../gradlew myfeature:sample:assemble
```

## Project structure must match the filesystem structure

`gww` assumes that the folder containing `gradlew` is the root of the project. Project names must match the folder in
which they reside.

`gww` runs before gradle without any knowledge of your build, so it has no way to know what changes you made. If you are
working on a project with an unconventional structure, you can still use `:` instead of `/`.
## MacOS/Linux Only

While `gww` should work with any shell, it does not support the Windows command line. It should work with WSL2.

