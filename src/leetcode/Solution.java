package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> tollist = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        if (root == null)
            return new ArrayList<>();
        tollist.addAll(levelOrder(root.left));
        list.add(root.val);


        return tollist;

    }

    public void help() {

    }
}
