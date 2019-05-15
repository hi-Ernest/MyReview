package BST;

public interface Tree<E extends Comparable<E>> {

    boolean search(E e);

    boolean delete(E e);

    void inorder();

    void postorder();

    void preorder();

    int getSize();

    boolean isEmpty();

    java.util.Iterator iterator();
}
