package lesson2;

public class Main {

    private static String[][] sarray = {
        {"1", "2", "3", "4"},
        {"5", "6", "z", "8"},
        {"9", "as", "11", "12"},
        {"13","14", "15", "16"}
    };

    public static void main(String[] args) {
	// write your code here
        try {
            System.out.println(sumArray(sarray));
        } catch (MyArraySizeException e) {
            System.out.println("Размер массива должен быть 4х4");
        } catch (MyArrayDataException e) {
            System.out.println("в ячейке " + e.getI() + ", " + e.getJ() + " массива неверные данные");
        }
    }

    public static int sumArray(String sarray[][]){

        int result = 0;

        if (sarray.length != 4 || sarray[0].length != 4)
            throw new MyArraySizeException();

        for (int i = 0; i < sarray.length; i++) {
            for (int j = 0; j < sarray[i].length; j++) {
                try {
                    result += Integer.parseInt(sarray[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return result;
    }
}
