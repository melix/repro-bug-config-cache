import repro.*

apply<MyPlugin>()

val myExt = extensions.getByType<MyExtension>()

tasks.named<MyTask>("myTask") {
    specs.from(myExt.specNames.zip(myExt.specsDir) { names, dir -> {
        names.stream()
            .map { path: String? -> dir.file(path!!) }
            .toList()
    }})
}
