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

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.List;

public class MyPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        var myExt = project.getExtensions().create("myExt", MyExtension.class);
        myExt.getSpecNames().convention(List.of("foo.txt", "bar.txt"));
        myExt.getSpecsDir().convention(project.getLayout().getProjectDirectory().dir("specs"));
        var myTask = project.getTasks().register("myTask", MyTask.class, task -> {
            task.getOutputFile().convention(project.getLayout().getBuildDirectory().file("specs.txt"));
        });
    }
}
