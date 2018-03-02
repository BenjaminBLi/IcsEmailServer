/*
 * Will eventually be an AVL Tree
 */
public class Tree {
    private TNode root = null;

    public Tree(){
        this.root = null;
    }

    public Tree(TNode root){
        this.root = root;
    }

    public TNode getRoot() {
        return root;
    }

    public void setRoot(TNode root) {
        this.root = root;
    }

    public void buildFromMessagesFile(int whatId){
        int recordNumber = 0;
        Record record = new Record();
        for (recordNumber = 0; recordNumber < Globals.totalRecordsInMessageFile; recordNumber++) {
            record.readFromMessagesFile(recordNumber);
            if(record.getData().charAt(Globals.FIRST_RECORD_MARKER_POS) == Globals.FIRST_RECORD_MARKER){
                Message message = new Message();
                message.readFromMessagesFile(recordNumber);

                String key = Globals.STR_NULL;
                if(whatId == Globals.SENDER_ID){
                    key = message.getIdSenderFirst();
                }else if(whatId == Globals.RECEIVER_ID){
                    key = message.getIdReceiverFirst();
                }else{
                    System.out.println("***INVALID WHATKEY PARAMETER IN buildFromMessagesFile***");
                }

                TNode p = new TNode(key, recordNumber, null, null, null);
                insertNode(p);
            }
        }
    }

    public void insertNode(TNode p){
        if(this.root == null){
            root = p;
        }else if(p.getIdentification().compareTo(root.getIdentification()) < 0){
            if(root.getLeft() == null){
                root.setLeft(p);
                p.setParent(root);
            }else{
                Tree tree = new Tree(root.getLeft());
                tree.insertNode(p);
            }
        }else if(p.getIdentification().compareTo(root.getIdentification()) > 0){
            if(root.getRight() == null){
                root.setRight(p);
                p.setParent(root);
            }else{
                Tree tree = new Tree(root.getRight());
                tree.insertNode(p);
            }
        }else{
            System.out.println("Error: attempting to insert an identification that already exists in tree");
        }
    }

    public TNode findNode(String id){
        if(this.root == null){
            return null;
        }else if(id.equals(this.root.getIdentification())){
            return this.root;
        }else if(this.root.getIdentification().compareTo(id) < 0){
            Tree t = new Tree(this.root.getLeft());
            t.findNode(id);
        }else if(this.root.getIdentification().compareTo(id) > 0) {
            Tree t = new Tree(this.root.getRight());
            t.findNode(id);
        }else{
            return null;
        }
        return null;
    }

    public void printTree(){
        if(this.root != null){
            Tree t = new Tree(this.root.getLeft());
            t.printTree();

            System.out.println(this.root.toString());

            t = new Tree(this.root.getRight());
            t.printTree();
        }
    }

    public void printTree(int level){
        if(this.root != null){
            Tree t = new Tree(this.root.getLeft());
            t.printTree(level+1);

            System.out.println(this.root.toString() + " in level " + level);

            t = new Tree(this.root.getRight());
            t.printTree(level+1);
        }

    }

    @Override
    public String toString() {
        if(this.root == null) return "Empty Tree";
        return this.root.getIdentification();
    }
}
