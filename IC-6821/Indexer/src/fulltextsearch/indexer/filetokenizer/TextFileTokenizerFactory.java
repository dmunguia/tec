package fulltextsearch.indexer.filetokenizer;

import java.io.File;

public class TextFileTokenizerFactory implements FileTokenizerFactoryMethod {
    @Override
    public FileTokenizer create(File file) {
        return new TextFileTokenizer(file);
    }
}
