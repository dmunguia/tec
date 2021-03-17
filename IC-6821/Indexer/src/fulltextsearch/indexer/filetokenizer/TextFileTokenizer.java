package fulltextsearch.indexer.filetokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextFileTokenizer implements FileTokenizer {

    private final Scanner scanner;

    public TextFileTokenizer(File file) {
        try {
            this.scanner = new Scanner(file).useDelimiter(" ");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("Couldn't open file for scanning: %s", file.getAbsolutePath()));
        }
    }

    @Override
    public boolean hasNext() {
        return scanner.hasNext();
    }

    @Override
    public String next() {
        return scanner.next();
    }
}
