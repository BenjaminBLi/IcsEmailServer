import java.util.*;

public class Tester {
	public static void main(String[] args) {
		int listSize = 51;

		System.out.println(check(listSize));
	}

	public static boolean check(int listSize) {
		ArrayList list = generateOrder(0, listSize);
		listSize += (listSize - 50);
		ArrayList<String> protectedNodes = new ArrayList<>();

		Tree tree = new Tree();

		TreeSet<String> uniqueIds = new TreeSet<>();

		for (int i = 0; i < 50; i++) {
			protectedNodes.add(rightPad("" + list.get(i), 5, '0'));
			tree.insertNode(new TNode(rightPad("" + list.get(i), 5, '0'), 1, null, null, null));
			uniqueIds.add(rightPad("" + list.get(i), 5, '0'));
		}

		boolean ok = true;
		for (int i = 50; i < listSize; i++) {
			String currentId = rightPad("" + list.get(i), 5, '0');

			if (tree.findNode(currentId) != null) {
				tree.deleteNode(tree.findNode(currentId));
				uniqueIds.remove(currentId);
			} else {
				uniqueIds.add(currentId);
				tree.insertNode(new TNode(currentId, 1, null, null, null));
			}
			TNode[] arr = setToTNode(uniqueIds);

			Tree constructed = buildTreeFromArray(arr);
			ok &= (checkConnectionsAndHeight(tree, constructed));
			ok &= checkSorted(tree.getRoot());
			for(String node : protectedNodes){
			    ok &= tree.findNode(node) != null;
            }
		}
		return ok;
	}

	public static boolean checkSorted(TNode curr) {
		boolean valid = true;
		if (curr.getLeft() != null) {
			valid &= curr.getLeft().getId().compareTo(curr.getId()) < 0;
			valid &= checkSorted(curr.getLeft());
		}
		if (curr.getRight() != null) {
			valid &= curr.getRight().getId().compareTo(curr.getId()) > 0;
			valid &= checkSorted(curr.getRight());
		}
		return valid;
	}


	public static String rightPad(String text, int desiredLength, char paddingItem) {
		int difference = desiredLength - text.length();
		for (int i = 0; i < difference; i++) {
			text = "" + paddingItem + text;
		}
		return text;

	}


	public static ArrayList generateOrder(int min, int max) {
		if (max < min) {
			int temp = min;
			min = max;
			max = temp;
		}
		ArrayList list = new ArrayList();
		for (int i = min; i < max; i++) {
			list.add(new Integer(i));
			//list.add (new Integer (i));

		}
		Collections.shuffle(list);
		for (int i = min + 50; i < max; i++)
			list.add(list.get(i));
		return list;
	}


	public static TNode[] setToTNode(TreeSet<String> a) {
		TNode[] arr = new TNode[a.size()];
		int idx = 0;
		for(String s : a){
			arr[idx++] = new TNode(rightPad("" + s, 5, '0'), 1, null, null, null);
		}
		return arr;
	}


	public static Tree buildTreeFromArray(TNode lst[]) {
		Tree tree = new Tree(lst[0]);
		//TNode curr = tree.getRoot ();
		for (int i = 0; i < lst.length; i++)
			tree.insertNode(lst[i]);
		return tree;
	}


	public static boolean checkConnectionsAndHeight(Tree given, Tree constructed) {

		boolean ok = true;

		if (given.getRoot() == null || constructed.getRoot() == null) {
			return (given.getRoot() == null) == (constructed.getRoot() == null);
		}

		TNode givenLeft = given.getRoot().getLeft();
		TNode givenRight = given.getRoot().getRight();
		TNode constLeft = constructed.getRoot().getLeft();
		TNode constRight = constructed.getRoot().getRight();

		if ((givenLeft == null) == (constLeft != null)) {
			//System.out.println("YES");
			ok = false;
		} else if (givenLeft != null) {
			Tree newGiven = new Tree(givenLeft);
			Tree newConst = new Tree(constLeft);

			//check connections using identification of each connection between left, right, parent, and itself
			try {
				ok &= givenLeft.getId().equals(constLeft.getId());
				ok &= givenLeft.getParent().getId().equals(constLeft.getParent().getId());
				if (givenLeft.getRight() != null)
					ok &= givenLeft.getRight().getId().equals(constLeft.getRight().getId());
				if (givenLeft.getLeft() != null) ok &= givenLeft.getLeft().getId().equals(constLeft.getLeft().getId());

			} catch (Exception e) {
//                System.out.println("error: " + given.getRoot());
				//System.out.println("HI");
				ok = false;
			}

			ok &= checkConnectionsAndHeight(newGiven, newConst);
		}

		if ((givenRight == null) == (constRight != null)) {
			//System.out.println("ALSO YES");
			ok = false;
		} else if (givenRight != null) {
			Tree newGiven = new Tree(givenLeft);
			Tree newConst = new Tree(constLeft);

			try {
				ok &= givenRight.getId().equals(constRight.getId());
				ok &= givenRight.getParent().getId().equals(constRight.getParent().getId());
				if (givenRight.getRight() != null)
					ok &= givenRight.getRight().getId().equals(constRight.getRight().getId());
				if (givenRight.getLeft() != null)
					ok &= givenRight.getLeft().getId().equals(constRight.getLeft().getId());
				//` ` 1
			} catch (Exception e) {
//                System.out.println("error: " + given.getRoot());

				ok = false;
			}

			ok &= checkConnectionsAndHeight(newGiven, newConst);
		}

		//ok &= given.getRoot ().getHeight () == constructed.getRoot ().getHeight ();

		return ok;
	}
}
