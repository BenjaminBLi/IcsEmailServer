public class TNode {
    private String _identification = "";
    private int recordNumber = -1;
    private int height = 0;
    private TNode left = null;
    private TNode right = null;
    private TNode parent = null;

    public TNode(){
        this._identification = "";
        this.recordNumber = -1;
        this.left = this.right = this.parent = null;
        this.height = 0;
    }

    public TNode(String s, int recordNumber, TNode left, TNode right, TNode parent){
        this._identification = s;
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
        this._identification = identification;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setParent(TNode parent) {
        this.parent = parent;
    }

    public void setRight(TNode right) {
        this.right = right;
    }

    public String getId() {
        return this._identification;
    }

    public int getHeight() {
        return height;
    }

    public int getRecordNumber() {
        return recordNumber;
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

    public boolean isLeaf(){
        return left == null && right == null;
    }

    @Override
    public String toString() {
        if(this == null)
            return "null";
        else
            return "Id: " + this._identification + " Record number: " + this.recordNumber;
    }
}
