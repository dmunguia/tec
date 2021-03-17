package fulltextsearch.indexer.filesource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class DirectoryFileSource implements FileSource {

    private final Iterator<Path> iterator;

    public DirectoryFileSource(File directory) {
        try {
            this.iterator = Files.walk(directory.toPath()).iterator();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Couldn't access directory %s", directory.getAbsolutePath()));
        }
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public File next() {
        return this.iterator.next().toFile();
    }
}
