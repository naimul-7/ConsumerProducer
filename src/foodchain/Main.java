package foodchain;

import java.lang.Runnable;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        /* Create an instance of a FoodChain class containing produce and consume methods */
        final FoodChain chain = new FoodChain();

        /* Create producer thread using lambda expression */
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    chain.produce();
                } catch(InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    chain.consume();
                } catch(InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
