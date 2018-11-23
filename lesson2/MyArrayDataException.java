package lesson2;

public class MyArrayDataException extends RuntimeException {
    private int i;
    private int j;

    public MyArrayDataException(int i, int j) {
        //super("");
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
