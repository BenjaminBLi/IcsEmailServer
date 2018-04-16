public class Testing {
    public static void main(String[] args) {
        Tree test = new Tree();
        test.insertNode(new TNode("20", 5, null, null, null));
        test.insertNode(new TNode("30", 1, null, null, null));
        test.insertNode(new TNode("08", 3, null, null, null));
        test.insertNode(new TNode("04", 1, null, null, null));
        test.insertNode(new TNode("10", 2, null, null, null));
        test.insertNode(new TNode("15", 1, null, null, null));

        test.printTree();
    }
}
