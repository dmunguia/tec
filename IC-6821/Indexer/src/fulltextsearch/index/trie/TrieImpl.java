package fulltextsearch.index.trie;

import java.util.*;

public class TrieImpl<V> implements Trie<V> {

    private class TrieNode {
        private Map<Character, TrieNode> children;
        private Set<V> values;

        public TrieNode() {
            children = new HashMap<>();
            values = new HashSet<>();
        }

        public Map<Character, TrieNode> getChildren() {
            return children;
        }

        public Set<V> getValues() {
            return values;
        }
    }

    private TrieNode root;

    public TrieImpl() {
        root = new TrieNode();
    }

    @Override
    public void insert(String key, V value) {
        TrieNode current = root;
        for (char c : key.toCharArray()) {
            current = current.getChildren().computeIfAbsent(c, __ -> new TrieNode());
        }

        current.getValues().add(value);
    }

    @Override
    public Collection<V> find(String key) {
        TrieNode current = root;
        for (char c : key.toCharArray()) {
            TrieNode node = current.getChildren().get(c);
            if (node == null) {
                return Collections.emptySet();
            }
            current = node;
        }

        return current.getValues();
    }
}