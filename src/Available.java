public class Available {
    private int recordNumber;
    private Available next;

    public Available(){
        this.recordNumber = -1;
        this.next = null;
    }

    public Available(int n){
        this.recordNumber = n;
        this.next = null;
    }

    public int getRecordNumber() {
        return recordNumber;
    }
    public Available getNext() {
        return next;
    }
    public void setNext(Available next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "" + this.recordNumber;
    }
}
