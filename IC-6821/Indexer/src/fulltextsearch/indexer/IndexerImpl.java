package fulltextsearch.indexer;

import fulltextsearch.index.Index;
import fulltextsearch.indexer.filesource.FileSource;
import fulltextsearch.indexer.filetokenizer.FileTokenizer;
import fulltextsearch.indexer.filetokenizer.FileTokenizerFactory;

import java.io.File;
import java.util.function.Consumer;

public class IndexerImpl implements Indexer {

    private final FileSource fileSource;
    private final FileTokenizerFactory fileTokenizerFactory;
    private final Index index;

    public IndexerImpl(FileSource fileSource, FileTokenizerFactory fileTokenizerFactory, Index index) {
        this.fileSource = fileSource;
        this.fileTokenizerFactory = fileTokenizerFactory;
        this.index = index;
    }

    @Override
    public void index(Consumer<File> reporter) {
        while (this.fileSource.hasNext()) {
            File file = this.fileSource.next();
            reporter.accept(file);
            FileTokenizer fileTokenizer = this.fileTokenizerFactory.create(file);
            while (fileTokenizer.hasNext()) {
                String word = fileTokenizer.next();
                this.index.index(word, file);
            }
        }

        this.index.persist();
    }
}
