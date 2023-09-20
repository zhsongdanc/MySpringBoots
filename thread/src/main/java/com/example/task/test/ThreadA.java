package com.example.task.test;

class ThreadA extends Thread {

    private Object lock;
    private int num;

    public ThreadA(Object lock, int num) {
        this.lock = lock;
        this.num = num;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                lock.notifyAll();

                try {
                    lock.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (num % 2 == 0 && num <= 100) {
                    System.out.println(Thread.currentThread().getName() + num++);
                    if (num == 101) {
                        Thread.interrupted();
                    }
                } else {
                    num++;
                }
            }

        }
    }
}
