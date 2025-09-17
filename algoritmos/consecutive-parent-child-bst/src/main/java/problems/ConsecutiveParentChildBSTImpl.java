package problems;

import java.util.Set;
import java.util.TreeSet;

import adt.bst.BSTNode;

public class ConsecutiveParentChildBSTImpl extends BSTInteger implements ConsecutiveParentChildBST {

	@Override
	public Set<Pair> findConsecutives() {
		TreeSet<Pair> set = new TreeSet<>();
		BSTNode<Integer> root = this.getRoot();
		findConsecutives(root, set);
		return set;
	}

	private void findConsecutives(BSTNode<Integer> root, TreeSet<Pair> set) {
		if (!root.isEmpty()) {
			if (!root.getLeft().isEmpty() && root.getData() - root.getLeft().getData() == 1) {
				set.add(new Pair(root.getData(), root.getLeft().getData()));
			}

			if (!root.getRight().isEmpty() && root.getRight().getData() - root.getData() == 1) {
				set.add(new Pair(root.getData(), root.getRight().getData()));
			}

			findConsecutives(root.getLeft(), set);
			findConsecutives(root.getRight(), set);
		}
	}
} 