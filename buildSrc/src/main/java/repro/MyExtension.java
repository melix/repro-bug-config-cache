package repro;

import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.provider.SetProperty;

public interface MyExtension {
    DirectoryProperty getSpecsDir();
    SetProperty<String> getSpecNames();
}
