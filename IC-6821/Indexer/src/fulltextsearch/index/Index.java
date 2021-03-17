package fulltextsearch.index;

import java.io.File;
import java.util.Set;

public interface Index {
    void index(String term, File file);
    Set<File> search(String term);
    void persist();
    void restore();
}
