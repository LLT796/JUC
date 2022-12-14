package com.testTree;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean isSymmetric(Node root) {
        List<Integer> result=new ArrayList<>();
        inOrder(root,result);
        int left=0;
        int right=result.size()-1;
        while(left<=right)
        {
            if(result.get(left)!=result.get(right))
            {
                return false;
            }
            else
            {
                left++;
                right--;
            }
        }
        return true;
    }

    public void inOrder(Node root,List<Integer>result)
    {
        if(root==null)
        {
            return;
        }
        inOrder(root.left,result);
        result.add(root.val);
        inOrder(root.right,result);
    }
}

public class Node {
    int val;
    com.testTree.Node left;
    com.testTree.Node right;

    public Node(int val, com.testTree.Node left, com.testTree.Node right)
    {
        this.val=val;
        this.left=left;
        this.right=right;
    }
}


