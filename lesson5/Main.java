package lesson5;



public class Main {

    private static final int size = 10000000;
    private static final int h = size / 2;

    public static void main(String[] args) {
	// write your code here
        method1();
        method2();
    }

    public static void method1() {
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++)
            arr[i] = 1.0f;
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5)* Math.cos(0.2f + i / 5)
                    * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }

    public static void method2() {
        float[] arr = new float[size];
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];
        for (int i = 0; i < arr.length; i++)
            arr[i] = 1.0f;
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr1.length; i++) {
                    arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5)* Math.cos(0.2f + i / 5)
                            * Math.cos(0.4f + i / 2));
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr2.length; i++) {
                    arr2[i] = (float)(arr2[i] * Math.sin(0.2f + i / 5)* Math.cos(0.2f + i / 5)
                            * Math.cos(0.4f + i / 2));
                }

            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - a);
    }
}
