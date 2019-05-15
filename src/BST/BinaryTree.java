package BST;


import java.util.ArrayList;

public class BinaryTree<E extends Comparable<E>>
    extends AbstractTree<E> {

    protected TreeNode<E> root;
    protected int size = 0;


    public BinaryTree() {
    }

    public BinaryTree(E[] objects) {
        for (int i=0; i<objects.length; i++) {
            insert(objects[i]);
        }
    }

    @Override
    public boolean search(Comparable e) {
        TreeNode<E> current = root;

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                return true;
        }
        return false;
    }


    public boolean insert(E e) {
        if(root == null) {
            root = createNewNode(e);
        }
        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;

            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                }
                else
                    return false;
            }

            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            }else {
                parent.right = createNewNode(e);
            }

        }
        size++;
        return true;
    }

    protected TreeNode<E> createNewNode(E e) {
        TreeNode<E> node = new TreeNode<>(e);
        return node;
    }


    @Override
    public void inorder() {
        inorder(root);
    }

    //inorder traversal
    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    //postorder traversal...
    //preordre traversal...



    //内部静态类
   public static class TreeNode<E extends Comparable<E>> {
        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    /** Returns a path from the root leading to the specified element*/
    public ArrayList<TreeNode<E>> path(E e) {
        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root;

        while (current != null) {
            list.add(current);
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                break;
        }
        return list;
    }


    /**
     * @TODO
     * @param e
     * @return
     */
    @Override
    public boolean delete(Comparable e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            }
            else
                break;
        }

        if (current == null)
            return false;

        if (current.left == null) {
            if (parent == null) {
                root = current.right;
            }
            else {
                if (e.compareTo(parent.element) > 0)
                    parent.left = current.right;
                else
                    parent.right = current.left;
            }
        }

        return true;
    }
}



