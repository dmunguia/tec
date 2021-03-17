package fulltextsearch.file;

import java.io.File;

public class FileExtension {
    private String extension;

    public FileExtension(File file) {
        String filename = file.getName();
        int index = filename.lastIndexOf('.');
        if (index < 1 || index == filename.length()-1) {
            this.extension = "";
        } else {
            this.extension = filename.substring(index)+1;
        }

    }

    public String toString() {
        return this.extension;
    }
}
