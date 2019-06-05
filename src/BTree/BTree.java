package BTree;

public class BTree<Key extends Comparable<Key>, Value> {

    private static final int M = 4;

    private Node root; //B-tree 的根结点
    private int height; //B-tree 的高度
    private int N; //B-tree 树中键值对的数目

    private static final class Node {
        private int number; //孩子的数量
        private Entry[] children = new Entry[M]; //孩子集合

        //设置一个节点的孩子数量
        private Node(int k) {
            number = k;
        }
    }

    private static class Entry {
        private Comparable key;
        private Object val;
        private Node next; //指向节点下一个元素

        public Entry(Comparable key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public BTree() {
        root = new Node(0);
    }

    public Value get(Key key) {
        if (key == null) throw new NullPointerException("key must not be null");
        return search(root, key, height);
    }

    /**
     * 搜索操作
     * @param root
     * @param key
     * @param height
     * @return
     */
    public Value search(Node root, Key key, int height) {
        Entry[] children = root.children;

        if (height ==0 ) {
            for (int j=0; j<root.number; j++) {
                if (key.equals(children[j].key))
                    return (Value) children[j].val;
            }
        }
        else {
            for (int j = 0; j < root.number; j++) {
                if (j + 1 == root.number || key.compareTo((Key) root.children[j+1].key)<0) {
                    return search(children[j].next, key, height-1);

                }
            }
        }
        return null;
    }
}
