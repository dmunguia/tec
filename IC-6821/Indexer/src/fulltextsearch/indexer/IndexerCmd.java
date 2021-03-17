package fulltextsearch.indexer;

import fulltextsearch.index.Index;
import fulltextsearch.index.TrieIndex;
import fulltextsearch.index.trie.Trie;
import fulltextsearch.index.trie.TrieImpl;
import fulltextsearch.indexer.filesource.DirectoryFileSource;
import fulltextsearch.indexer.filesource.FileSource;
import fulltextsearch.indexer.filetokenizer.FileTokenizerFactory;
import fulltextsearch.indexer.filetokenizer.FileTokenizerFactoryMethod;
import fulltextsearch.indexer.filetokenizer.TextFileTokenizerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class IndexerCmd {

    private final File startingDirectory;

    public IndexerCmd(File startingDirectory) {
        this.startingDirectory = startingDirectory;
    }

    public static void main(String[] args) {
        validateCmdLine(args);
        File startingDirectory = getStartingDirectory(args[0]);

        try {
            IndexerCmd indexerCmd = new IndexerCmd(startingDirectory);
            indexerCmd.execute();
        } catch (Exception e) {
            System.out.printf("Unexpected error occurred: %s%n", e.getMessage());
        }
    }

    private static void printUsage() {
        System.out.println("Usage: indexer <directory location>");
        System.out.println("    <directory location>: the path of the directory to index");
    }

    private static void validateCmdLine(String[] args) {
        if (args.length != 1) {
            printUsage();
            System.exit(1);
        }
    }

    private static File getStartingDirectory(String arg) {
        File startingDirectory = new File(arg);
        if (!startingDirectory.exists() || !startingDirectory.isDirectory()) {
            System.out.printf("Location %s does not exist or is not a directory%n",
                    startingDirectory.getAbsolutePath());
            System.exit(1);
        }
        return startingDirectory;
    }

    public void execute() {
        Indexer indexer = indexer();
        indexer.index((File f) -> {
            if (f.isDirectory()) {
                System.out.println(f.getAbsolutePath());
            }
        });
    }

    private Indexer indexer() {
        return new IndexerImpl(fileSource(), fileTokenizerFactory(), index());
    }

    private FileSource fileSource() {
        return new DirectoryFileSource(this.startingDirectory);
    }

    private FileTokenizerFactory fileTokenizerFactory() {
        Map<String, FileTokenizerFactoryMethod> factoryMethods = new HashMap<>();
        factoryMethods.put("txt", new TextFileTokenizerFactory());

        return new FileTokenizerFactory(factoryMethods);
    }

    private Index index() {
        return new TrieIndex(trie());
    }

    private Trie<File> trie() {
        return new TrieImpl<>();
    }
}
