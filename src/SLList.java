public class SLList {
    private SNode head = null;
    private SNode tail = null;

    public SLList(){
        this.head = null;
        this.tail = null;
    }

    public SNode findNode(String key){
        SNode p;
        for(p = this.head; p != null && !key.equals(p.getName()); p = p.getNext());
        return p;
    }

    public void insertNode(SNode p){
        if(this.head == null){
            this.head = this.tail = p;
        }else {
            SNode q, r;
            q = r = this.head;
            while (q != null && q.getName().compareTo(p.getName()) < 0) {
                r = q;
                q = q.getNext();
            }
            if (q == this.head) {
                p.setNext(q);
                this.head = p;
            }else if(q == null){
                //this is if it went all the way to the end
                this.tail.setNext(p);
                this.tail = p;
            }else{
                r.setNext(p);
                p.setNext(q);
            }
        }
    }

    public void deleteNode(SNode p){
        //note that node p is assumed to be in the list
        if(this.head == this.tail) {
            this.head = this.tail = null;
        }else if(this.head == p) {
            this.head = this.head.getNext();
        }else if(this.tail == p){
            SNode q = this.head;
            while(q.getNext() != p) q = q.getNext();
            this.tail = q;
            q.setNext(null);
        }else{
            SNode q = this.head;
            while(q.getNext() != p) q = q.getNext();
            q.setNext(p.getNext());
        }
    }

    public SNode getHead(){return this.head;}
    public SNode getTail(){return this.tail;}
    public void setHead(SNode head) {this.head = head;}
    public void setTail(SNode tail) {this.tail = tail;}

    public String toString() {
        String ret = "";
        for(SNode p = head; p != null; p = p.getNext())
            ret += p.toString() + "\n";

        return ret;
    }
}
