package fulltextsearch.indexer.filetokenizer;

import java.io.File;

public interface FileTokenizerFactoryMethod {
    FileTokenizer create(File file);
}
