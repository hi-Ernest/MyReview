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
     * 删除操作
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
                break;//element is in the tree pointed by current
        }

        if (current == null)
            return false;

        //修改
        if (current.left != null && current.right != null) {
            //获取后继节点
            TreeNode<E> successor = successor(current);

            //两者交换位置


            //将删除节点设置为后继节点

        }


        //当前节点没有左节点
        if (current.left == null) {
            if (parent == null) {
                root = current.right;
            }
            else {
                if (e.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        }else {
            //the current node has a left node
            //locate thr rightmost node in left subtree of current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }

            current.element = rightMost.element;

            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            }else {
                parentOfRightMost.left = rightMost.left;
            }
        }

        size--;
        return true;
    }

    private TreeNode<E> successor(TreeNode<E> current) {

        //判断current节点是否有右子树、节点等
        if (current.right != null) {
            return minimum(current.right);
        }

        //没有右孩子则
        //1)current 是一个左孩子,则后继节点是它的父节点
        //2)current 是一个右孩子,
//        TreeNode<E> pa =
        while (current != null) {
            current = current.left;
        }

        return current;

    }

    //查找到最小的节点：返回以node为根节点的最小节点
    private TreeNode<E> minimum(TreeNode<E> node) {

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

}



