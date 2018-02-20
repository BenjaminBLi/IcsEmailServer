public class AvailableList {
    /**
     * used to keep track of all free points in the records, so that memory is optimized
     */
    private Available head, tail;
    public AvailableList(){
        this.head = this.tail = null;
    }

    public AvailableList(Available tail, Available head){
        this.head = head;
        this.tail = tail;
    }

    public void addRecord(int recordNumber){
        if(this.head == null){
            this.head = new Available(recordNumber);
            this.tail = head;
        }else{
            this.tail.setNext(new Available(recordNumber));
            this.tail = tail.getNext();
        }
    }

    public int getNextRecord(){
        if(this.head == null){
            return Globals.EMPTY_AVAILABLE_LIST;
        }else{
            int rec = this.head.getRecordNumber();
            this.head = this.head.getNext();
            if(this.head == null)
                this.tail = null;
            return rec;
        }
    }

    public Available getHead() {
        return this.head;
    }
    public Available getTail() {
        return this.tail;
    }

    @Override
    public String toString() {
        String ret = "";
        for(Available p = this.head; p != null; p = p.getNext()){
            ret += p.toString() + "\n";
        }
        return ret;
    }
}
