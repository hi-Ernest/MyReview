package BST;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();

        for (int i=0; i<10; i++) {
            tree.insert(i);
        }

        ArrayList<BinaryTree.TreeNode<Integer>> listPath = tree.path(9);

        System.out.println(listPath);

    }
}
