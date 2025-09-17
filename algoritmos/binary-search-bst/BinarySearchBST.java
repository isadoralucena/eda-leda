class SymmetricTree {
  public TreeNode searchBST(TreeNode root, int val) {
    TreeNode answer = null;
    if (root == null || root.val == val) {
      answer = root;
    } else if (val < root.val) {
      answer = searchBST(root.left, val);
    } else {
      answer = searchBST(root.right, val);
    }

    return answer;
  }
}

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {
  }

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}