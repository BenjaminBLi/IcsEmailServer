import java.io.RandomAccessFile;
import java.util.Random;
import java.util.RandomAccess;

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

    public TNode findNode(String partialKey, int where){
        if(partialKey.length() == Globals.IDENTIFICATION_LEN){
            return findNode(partialKey);
        } else if(root == null){
            return null;
        }
        int n = partialKey.length();
        if(partialKey.compareTo(root.getIdentification().substring(0, n)) < 0){
            Tree t = new Tree(root.getLeft());
            return t.findNode(partialKey, where);
        } else if(partialKey.compareTo(root.getIdentification().substring(0, n)) > 0){
            Tree t = new Tree(root.getRight());
            return t.findNode(partialKey, where);
        } else {
            TNode p = root;
            if(where == 0){
                TNode q = p.getLeft();
                while(q != null){
                    if(q.getIdentification().substring(0, n).equals(partialKey)){
                        p = q;
                        q = q.getLeft();
                    } else {
                        q = q.getRight();
                    }
                }
            } else {
                TNode q = p.getRight();
                while(q != null){
                    if(q.getIdentification().substring(0, n).equals(partialKey)){
                        p = q;
                        q = q.getRight();
                    } else {
                        q = q.getLeft();
                    }
                }
            }
            return p;
        }
    }

    public void writeLevel(int level, RandomAccessFile f) {
        if (level == 0) {
            try {
                if (root != null) {
                    f.write(root.getIdentification().getBytes());
                    f.writeInt(root.getRecordNumber());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (root != null) {
            Tree tree = new Tree(root.getLeft());
            tree.writeLevel(level - 1, f);

            tree = new Tree(root.getRight());
            tree.writeLevel(level - 1, f);
        }
    }

    public int height() {
        if (this.root == null) {
            return 0;
        } else {
            int maxLevel = 0;
            Tree tree = new Tree(root.getLeft());
            maxLevel = tree.height();

            tree = new Tree(root.getRight());
            int tmp = tree.height();
            maxLevel = tmp > maxLevel ? tmp : maxLevel;

            return maxLevel + 1;
        }
    }

    public void breadthFirstSave(String fileName){
        try{
            RandomAccessFile f = new RandomAccessFile(fileName, "rw");
            f.setLength(0);

            for (int i = 0; i < height(); i++) {
                    writeLevel(i, f);
            }

            f.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void breadthFirstRetrieve(String fileName){
        try{
            RandomAccessFile f = new RandomAccessFile(fileName, "rw");

            int nodes = (int) (f.length() / (Globals.IDENTIFICATION_LEN + Globals.INT_LEN));

            TNode p = null;

            byte identification[] = new byte[Globals.IDENTIFICATION_LEN];
            String identificationString = Globals.STR_NULL;

            for (int i = 0; i < nodes; i++) {
                identificationString = Globals.STR_NULL;
                f.read(identification);


                for (int j = 0; j < identification.length; j++) {
                    identificationString = identificationString + (char) identification[j];
                }

                p = new TNode(identificationString, f.readInt(), null, null, null);
                this.insertNode(p);
            }
        }catch (Exception e){
            System.out.println("Couldn't retrieve tree file " + fileName);
        }
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
