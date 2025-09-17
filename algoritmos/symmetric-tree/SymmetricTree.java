class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        boolean answer = true;
        if (root != null) {
            answer = isSymmetric(root.left, root.right);
        }
        return answer;
    }

    private boolean isSymmetric(TreeNode node1, TreeNode node2) {
        boolean answer = false;
        if (node1 == null && node2 == null) {
            answer = true;
        } else if (node1 != null && node2 != null) {
            answer = node1.val == node2.val;
            if (answer) {
                answer = isSymmetric(node1.left, node2.right)
                        && isSymmetric(node1.right, node2.left);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        /** 
        *        1
        *      /   \
        *     2     2
        *    / \   / \
        *   3   4 4   3
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        SymmetricTree st = new SymmetricTree();
        System.out.println("Is the tree symmetrical? " + st.isSymmetric(root)); // true

        root.right.right = new TreeNode(99);

        System.out.println("Is the tree symmetrical? " + st.isSymmetric(root)); // false
    
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