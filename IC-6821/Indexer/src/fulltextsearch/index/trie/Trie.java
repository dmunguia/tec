package fulltextsearch.index.trie;

import java.util.Collection;

public interface Trie<V> {
    void insert(String key, V value);
    Collection<V> find(String key);
}
