/*
 * Copyright 2003-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package repro;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public abstract class MyTask extends DefaultTask {

    @InputFiles
    public abstract ConfigurableFileCollection getSpecs();

    @OutputFile
    public abstract RegularFileProperty getOutputFile();

    @TaskAction
    public void doSomething() throws IOException {
        var files = getSpecs().getFiles();
        var output = getOutputFile().get().getAsFile();
        try (var writer = Files.newBufferedWriter(output.toPath())) {
            for (File file : files) {
                writer.write(file.toString() + "\n");
            }
        }
    }

}
