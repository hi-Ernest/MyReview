package BST;

public class BinarySearchTree {

    private Node root;
    private int size;


    public BinarySearchTree() {
    }

    class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public Node insert(int key) {
        //新增节点
        Node newNode= new Node(key);
        Node current = root;

        Node parent = null;

        if (current == null) {
            root = newNode;
            return newNode;
        }
        while (true) {
            parent = current;
            if (key < current.value) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return newNode;
                }
            }else if (key > current.value) {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return newNode;
                }
            }
        }
    }


    public boolean insert2(int key) {
        Node newNode = new Node(key);

        if (root == null) {
            root = newNode;
        }else {
            Node current = root;
            Node parent = null;

            while (current != null) {
                parent = current;
                if (key < current.value) {
                    current = current.left;
                }else if (key > current.value) {
                    current = current.right;
                }else
                    return false;
            }

            if (key < parent.value) {
                parent.left = newNode;
            }else {
                parent.right = newNode;
            }
        }
        //size++;
        return true;
    }

    /**
     * BST的删除操作
     * @param node
     * @return
     */
    public boolean remove(Node node) {

        Node current = root;
        Node parent = null;

        //相当于搜索Search 找到该删除节点以及它的父亲节点
        //有一种情况是删除节点就是根节点
        while (current != null) {
            if (node.value < current.value) {
                parent = current;
                current = current.left;
            }else if (node.value > current.value) {
                parent = current;
                current = current.right;
            }
            else
                break;
        }

        //解决需删除的节点不存在树里情况
        if (current == null) {
            return false;
        }

        //1.删除的节点没有左子树的情况
        if (current.left == null) {

            //该情况的删除节点是叶子节点
            if (current.right == null) {
                if (current.value < parent.value) {
                    parent.left = null;
                }else
                    parent.right = null;
            }

            //父节点是root的情况
            if (parent == root) {
                root = current.right;
            //判断该删除节点的右节点应该接在parent节点的左边 还是 右边
            }else {
                if (current.right.value < parent.value) {
                    parent.right = current.right;
                }else {
                    parent.left = current.right;
                }
            }
        //2.删除的节点有有左子树的情况
        }else {
            Node parentRight = current;
            Node rightMost = current.left;

            while (rightMost.left != null) {
                parentRight = current;
                rightMost = rightMost.right;
            }

            //得到的rightMost是该删除节点左子树最大值
            //交换：删除节点、rightMost
            current.value = rightMost.value;

            //将rightMost消除(绕开连接)
            if (parentRight.right == rightMost) {
                parentRight.right = rightMost.left;
            }
            //特殊情况：rightMost没有进入while循环,即 该删除节点的左子树只有一个节点
            else{
                parentRight.left = rightMost.left;
            }
        }
        size--;
        return true;
    }
}
