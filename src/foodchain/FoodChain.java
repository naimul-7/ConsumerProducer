package foodchain;

import java.util.LinkedList;

public class FoodChain {
    LinkedList<Integer> list = new LinkedList<>();
    int capacity = 2;

    // Function called by producer thread
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                /* Producer threads waits while list is full */
                while(list.size() == capacity)
                    wait();

                System.out.println("Producer produced - " + value);

                /* Insert the jobs in the list */
                list.add(value++);

                /* notifies the consumer thread that now it can start consuming */
                notify();

                /* makes the working of program easier to understand */
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        while(true) {
            synchronized (this) {
                /* consumer waits while list is empty */
                while(list.size() == 0)
                    wait();

                /* to retrieve the first job in the list */
                int value = list.removeFirst();

                System.out.println("Consumer consumed - " + value);

                /* Wake up producer thread */
                notify();

                /* Go to sleep */
                Thread.sleep(500);
            }
        }
    }
}
