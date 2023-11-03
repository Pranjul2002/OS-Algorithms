class roundrobin{
    static Node head=null,tail=null;
    static class Node{
        String processName;
        int processArivalTime;
        int processBurstTime;
        Node next;

        Node(String processName,int processArivalTime,int processBurstTime){
            this.processName=processName;
            this.processArivalTime=processArivalTime;
            this.processBurstTime=processBurstTime;
            this.next=null;
        }
    }
    public static void add(String processName,int processArivalTime,int processBurstTime){
        Node newProcess=new Node(processName, processArivalTime, processBurstTime);
        if(head==null){
            head=newProcess;
            tail=newProcess;
            return;
        }
        tail.next=newProcess;
        tail=newProcess;
    }

    public static void terminate(){
        if(head==null){
            return;
        }
        head=head.next;
    }

    public static void putBacktoReadyQueue(String processName,int processArivalTime,int processBurstTime){
        add(processName, processArivalTime, processBurstTime);
        head=head.next;
    }

    public static void RR(int timeQuantum){
        if(head==null){
            return;
        }
        if(head.processBurstTime>timeQuantum){
            for(int i=0;i<timeQuantum;i++){
                System.out.print(head.processName+" ");
            }
            head.processBurstTime=head.processBurstTime-timeQuantum;
            putBacktoReadyQueue(head.processName,head.processArivalTime,head.processBurstTime);
            RR(timeQuantum);
        }else{
            for(int i=0;i<head.processBurstTime;i++){
                System.out.print(head.processName+" ");
            }
            terminate();
            RR(timeQuantum);
        }
    }

    public static void main(String[] args) {
        add("p1",0, 4);
        add("p2", 1, 3);
        add("p3", 3, 2);
        add("p4", 5, 1);
        RR(2);
    }
}
