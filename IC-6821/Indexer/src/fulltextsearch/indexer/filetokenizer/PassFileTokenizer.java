package fulltextsearch.indexer.filetokenizer;

public class PassFileTokenizer implements FileTokenizer {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        return "";
    }
}
