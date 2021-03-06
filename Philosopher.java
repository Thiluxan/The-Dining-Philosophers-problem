import java.util.Random;
import java.util.concurrent.Semaphore;

class Philosopher implements Runnable {

    private int id;

    private int amount=0;


    private Semaphore leftChopstick;
    private Semaphore rightChopstick;


    public Philosopher(int id, Semaphore leftChopstick, Semaphore rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    public void run() {
        try {
            while (amount<100) {
                think();
                pickUpLeftChopstick();
                pickUpRightChopstick();
                eat();
                putDownChopsticks();


            }
        } catch (InterruptedException e) {
            System.out.println("Philosopher " + id + " was interrupted.\n");
        }
    }


    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.\n");
        System.out.flush();
        Thread.sleep(new Random().nextInt(10));
    }


    private void pickUpLeftChopstick() throws InterruptedException{
        if(leftChopstick.availablePermits() ==0){
            System.out.println("Philosopher " +id +" is waiting for left  chopstick");
        }


        leftChopstick.acquire();
        System.out.println("Philosopher " + id + " is holding  left  chopstick.\n");

    }


    private void pickUpRightChopstick()  throws InterruptedException{
        if(rightChopstick.availablePermits() ==0){
            System.out.println("Philosopher " +id +" is waiting for right chopstick");
        }

        rightChopstick.acquire();
        System.out.println("Philosopher " + id + " is holding  right   chopstick.\n");

    }


    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating.\n");
        System.out.flush();
        amount+=20;
        Thread.sleep(new Random().nextInt(10));
    }


    private void putDownChopsticks() {
        leftChopstick.release();
        rightChopstick.release();
        System.out.println("Philosopher " + id +  " ate "+amount+"% and"+" released left and right sticks \n");
    }

}