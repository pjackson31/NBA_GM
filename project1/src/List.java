public class List {
    public Node first;
    public Node last;
    public int count;

    public List() {
        count=0;
    }

    public int size(Node n) {
        int s=0;
        while(n!=last){
            n=n.next;
            s++;
        }
        return s;
    }

    public void add(String toAdd) {
        Node ta = new Node(toAdd);

        if (first==null){
            first=ta;
            return;
        }
        ta.next=null;
        last=first;
        while(last.next!=null){
            last=last.next;
        }
        last.next = ta;
        ta.previous=last;
        return;
    }
    public Node find(String toFind) {
        Node current;
        current=first;
        while(current!=null){
            if(current.value.equals(toFind)) {
                return current;
            }
            current=current.next;

        }
        return null;
    }

    public void remove(String toRemove) {//go throught the list and find th ebad array and then make its previous
        //and next point to another point in the list
        //Node hold = new Node(last);
        Node remov = first;

        //last=last.previous;
        if (first.value.equals(toRemove)) {
            first.next.previous = null;
            first = first.next;
            count--;
            return;
        } else {
            remov = find(toRemove);
            if (remov != null) {
                remov.previous.next = remov.next;
                remov.next.previous = remov.previous;
                count--;
                return;
            }
        }
    }


    public void print(){
        Node node=first;
        while(node!=null) {
            System.out.println(node.value);
            node = node.next;
        }
        return;
    }



}



