package education;

import education.minmaxvalue.MinMaxValue;

public class Main {
    public static void main(String[] args) {
        MinMaxValue finder = new MinMaxValue();
        int[] data = { 7, 2, 9, 4, 5, 1, 8, 148, -200 };
        int[] result = finder.findMinMax(data);
        System.out.println("Min = " + result[0] + ", Max = " + result[1]);
    }
}
