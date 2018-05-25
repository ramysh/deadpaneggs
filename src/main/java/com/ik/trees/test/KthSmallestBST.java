package com.ik.trees.test;

import com.ik.trees.util.Tree;

import static com.ik.trees.util.Tree.Node;

/**
 * @author rpurigella
 */
public class KthSmallestBST {

    public static void main(String[] args) {
        Node root = Tree.createTree("30 20 10 5 # # 11 # # 25 21 # # 26 # # 40 35 31 # # 36 # # 45 41 # # 46 # #");
        Tree.printInorder(root);
    }
}
