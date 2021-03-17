package fulltextsearch.indexer.filesource;

import java.io.File;

public interface FileSource {
    boolean hasNext();
    File next();
}
