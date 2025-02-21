# Gradle configuration cache bug

Running `./gradlew myTask` yields:

```
FAILURE: Build failed with an exception.

* What went wrong:
Could not load the value of field `provider` of `org.gradle.internal.serialize.codecs.core.ProviderBackedFileCollectionSpec` bean found in field `__specs__` of task `:myTask` of type `repro.MyTask`.
> null array

```

Change from:

```
org.gradle.configuration-cache=true
```

to:

```
org.gradle.configuration-cache=false
```

in `gradle.properties` and everything works fine.

## Works in Kotlin?!

Now, checkout the `cc/fixed` branch. The only thing which is changed is that the tasks' spec property is set from Kotlin code instead of Java. The code is the same. Now, the build passes.
