package fulltextsearch.indexer.filetokenizer;

import fulltextsearch.file.FileExtension;

import java.io.File;
import java.util.Map;

public class FileTokenizerFactory {

    private final Map<String, FileTokenizerFactoryMethod> tokenizers;

    public FileTokenizerFactory(Map<String, FileTokenizerFactoryMethod> factoryMethods) {
        this.tokenizers = factoryMethods;
    }

    public FileTokenizer create(File file) {
        String extension = new FileExtension(file).toString(); // ¿porqué no un método privado o un util estático?
        PassFileTokenizerFactory defaultFactory = new PassFileTokenizerFactory(); // NULL object
        FileTokenizerFactoryMethod factoryMethod = tokenizers.getOrDefault(extension, defaultFactory);
        return factoryMethod.create(file);
    }
}
