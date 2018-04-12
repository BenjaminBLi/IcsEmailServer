public class Testing {
    public static void main(String[] args) {
        Tree test = new Tree();
        test.insertNode(new TNode("4", 5, null, null, null));
        test.insertNode(new TNode("1", 3, null, null, null));
        test.insertNode(new TNode("2", 4, null, null, null));
        test.insertNode(new TNode("6", 4, null, null, null));
        test.insertNode(new TNode("7", 4, null, null, null));

        test.printTree();
        test.deleteNode(test.findNode("4"));
        test.printTree();
    }
}
