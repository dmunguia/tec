package fulltextsearch.indexer;

import java.io.File;
import java.util.function.Consumer;

public interface Indexer {
    void index(Consumer<File> reporter);
}
