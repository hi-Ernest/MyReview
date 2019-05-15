package BST;

import java.util.Iterator;

public abstract class AbstractTree<E extends Comparable<E>>
    implements Tree {

    @Override
    public void inorder() {

    }

    @Override
    public void postorder() {

    }

    @Override
    public void preorder() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
