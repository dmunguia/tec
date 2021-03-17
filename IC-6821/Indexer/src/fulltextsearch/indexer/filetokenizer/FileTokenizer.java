package fulltextsearch.indexer.filetokenizer;

public interface FileTokenizer {
    boolean hasNext();
    String next();
}
