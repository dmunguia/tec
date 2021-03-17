package fulltextsearch.index;

import fulltextsearch.index.trie.Trie;

import java.io.*;
import java.util.Set;

public class TrieIndex implements Index {

    private final File indexFile = new File(String.format("%s%s.index", System.getProperty("user.home"), File.separator));
    private Trie<File> trie;

    public TrieIndex() {
        if (this.indexFile.exists()) {
            restore();
        } else {
            throw new RuntimeException(String.format("Couldn't find index file: %s", this.indexFile.getAbsolutePath()));
        }
    }

    public TrieIndex(Trie<File> trie) {
        this.trie = trie;
    }

    @Override
    public void index(String term, File file) {
        this.trie.insert(term, file);
    }

    @Override
    public Set<File> search(String term) {
        return (Set<File>) this.trie.find(term);
    }

    @Override
    public void persist() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.indexFile))) {
            out.writeObject(this.trie);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void restore() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.indexFile))) {
            this.trie = (Trie<File>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
