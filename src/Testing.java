public class Testing {
    public static void main(String[] args) {
        Tree test = new Tree();
        test.insertNode(new TNode("adsf", 5, null, null, null));
        test.insertNode(new TNode("aaaa", 3, null, null, null));
        test.insertNode(new TNode("baaa", 4, null, null, null));
        test.insertNode(new TNode("bbaa", 4, null, null, null));
        test.insertNode(new TNode("ba1a", 4, null, null, null));

        test.printTree();
    }
}
