package fulltextsearch.indexer.filetokenizer;

import java.io.File;

public class PassFileTokenizerFactory implements FileTokenizerFactoryMethod {
    @Override
    public FileTokenizer create(File file) {
        return new PassFileTokenizer();
    }
}
