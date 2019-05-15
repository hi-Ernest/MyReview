package AVL;

public class AVLTree<T extends Comparable<T>> {

    private AVLTreeNode<T> mRoot;


    //节点定义
    class AVLTreeNode<T extends Comparable<T>> {
        T key;
        int height;
        AVLTreeNode<T> left;
        AVLTreeNode<T> right;

        public AVLTreeNode(T key, int height, AVLTreeNode<T> left, AVLTreeNode<T> right) {
            this.key = key;
            this.height = height;
            this.left = left;
            this.right = right;
        }

        public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

    }

    public int height(AVLTreeNode<T> tree) {
        if (tree != null) {
            return tree.height;
        }

        return 0;
    }

    public int height() {
        return height(mRoot);
    }

    public int max(int a, int b) {
        return a > b? a : b;
    }

    /**
     * 返回的是当前根节点
     * @param k2
     * @return
     */
    private AVLTreeNode<T> leftLeftRotation(AVLTreeNode<T> k2) {
        AVLTreeNode<T> k1;

        k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height) + 1;

        return k1;
    }


    private AVLTreeNode<T> rightRightRotation(AVLTreeNode<T> k1) {
        AVLTreeNode<T> k2;

        k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;

        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k1.height) +1;

        return k2;
    }

    private AVLTreeNode<T> leftRightRotation(AVLTreeNode<T> node) {

        node.left = rightRightRotation(node.left);

        return leftLeftRotation(node);
    }

    private AVLTreeNode<T> rightLeftRotation(AVLTreeNode<T> node) {

        node.right = leftLeftRotation(node.right);

        return rightRightRotation(node);
    }


    private void insert(T key) {
        if (key == null) {
            throw new RuntimeException("data can't not be null");
        }
        this.mRoot = insert(mRoot, key);
    }


    private AVLTreeNode<T> insert(AVLTreeNode<T> root, T key) {

        //Create a new node into the tree without a child node
        if (root == null) {
            root = new AVLTreeNode<T>(key,null, null);
        }
        else {
            int cmp = key.compareTo(root.key);

            //insert left root
            if (cmp < 0) {
                root.left = insert(root.left, key);

                //restore balance
                if (height(root.left) - height(root.right) == 2) {
                    //judge left child or right child
                    if (key.compareTo(root.left.key) < 0)
                        root = leftLeftRotation(root);
                    else
                        root = rightRightRotation(root);
                }

            }
            else if (cmp > 0) {
                root.right = insert(root.right, key);

                if (height(root.right) - height(root.left) == 2) {
                    if (key.compareTo(root.right.key) > 0)
                        root = rightRightRotation(root);
                    else
                        root = leftLeftRotation(root);
                }
            }
            else {
                System.out.println("add fail: don't add the same of node");
            }
        }

        root.height = max(height(root.left), height(root.right)) + 1;

        return root;
    }


    private void remove(T key) {
        if (key == null)
            return;

        this.mRoot = remove(mRoot, key);
    }

    private AVLTreeNode<T> remove(AVLTreeNode<T> root, T key) {

        if(root == null) {
            return null;
        }

        int judge = key.compareTo(root.key);

        if (judge < 0) {
            root.left = remove(root, key);

            if (height(root.right) - height(root.left) == 2) {
                AVLTreeNode<T> currentNode = root.right;

                if (height(currentNode.left) > height(currentNode.right)) {
                    //LL
                    root = leftLeftRotation(root);
                }else {
                    //LR
                    root = leftRightRotation(root);
                }
            }
        }else if (judge > 0) {
            root.right = remove(root, key);

            if (height(root.left) - height(root.right) == 2) {
                AVLTreeNode<T> currentNode = root.left;


                if (height(currentNode.right) > height(currentNode.left)) {
                    //RL
                    root = rightRightRotation(root);
                }else {
                    //RL
                    root = rightLeftRotation(root);
                }
            }
        }else if (root.right != null && root.left != null) {

            //looking for a replacement point
            root.key = findMin(root.right).key;
        }



        return root;
    }

    private AVLTreeNode<T> findMin(AVLTreeNode<T> right) {


        return right;

    }

}
