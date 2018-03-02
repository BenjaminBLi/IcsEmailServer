public class TNode {
    private String identification = "";
    private int recordNumber = -1;
    private TNode left = null;
    private TNode right = null;
    private TNode parent = null;

    public TNode(){
        this.identification = "";
        this.recordNumber = -1;
        this.left = this.right = this.parent = null;
    }

    public TNode(String s, int recordNumber, TNode left, TNode right, TNode parent){
        this.identification = s;
        this.recordNumber = recordNumber;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public void setLeft(TNode left) {
        this.left = left;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setParent(TNode parent) {
        this.parent = parent;
    }

    public void setRight(TNode right) {
        this.right = right;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public String getIdentification() {
        return identification;
    }

    public TNode getLeft() {
        return left;
    }

    public TNode getParent() {
        return parent;
    }

    public TNode getRight() {
        return right;
    }

    @Override
    public String toString() {
        if(this == null)
            return "null";
        else
            return "Id: " + this.identification + " Record number: " + this.recordNumber;
    }
}
