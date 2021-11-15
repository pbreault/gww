# gww

> A gradle wrapper "wrapper" for deeply nested projects

## Convert from shell path to project coordinates

`gww` will automatically convert all `/` to `:`. This allows you to leverage your shell's autocompletion to figure out project paths.

```shell
# Slashes are converted to colons
gww feature/my-feature/sample:assemble
./gradlew :feature:my-feature:sample:c:assemble

 # Wrap arguments in double-quotes to keep slashes
gww tasks --include-build "a/b/c/d"
./gradlew tasks --include-build "a/b/c/d"
```

Note that you still need to use a `:` before between the project and the task(s).


## Chain multiple tasks for a project

You can chain tasks on project by separating them with `//`.

```shell
gww feature/my-feature/sample:clean//assemble//check
./gradlew :feature:my-feature:sample:clean :feature:my-feature:sample:assemble :feature:my-feature:sample:check
```

## Automatically use the  root project's Gradle wrapper

When you are in a subproject, running `gww` will look for a gradle wrapper in parent directories until it finds one.

```shell
gww tasks
../../../gradlew tasks
```

If this is the only feature you are interested in,  [gdubw/gng](https://github.com/gdubw/gng) is a more lightweight alternative.  

# Limitations

## Project structure must match the filesystem structure

`gww` assumes that the folder containing `gradlew` is the root of the project.
Project names must match the folder in which they reside.

`gww` runs before gradle without any knowledge of your build, so it has no way to know what changes you made.
If you are working on a project with an unconvential structure, you can still use `:` instead of `/`. 


## MacOS/Linux Only

While `gww` should work with any shell, it does not support the Windows command line.
It should work with WSL2.
