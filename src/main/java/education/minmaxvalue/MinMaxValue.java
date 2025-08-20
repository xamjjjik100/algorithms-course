package education.minmaxvalue;

public class MinMaxValue {

    public int[] findMinMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new Error("Массив пустой");
        }

        int min, max, startIndex;

        if (arr.length % 2 == 0) {
            if (arr[0] < arr[1]) {
                min = arr[0];
                max = arr[1];
            } else {
                min = arr[1];
                max = arr[0];
            }
            startIndex = 2;
        } else {
            min = max = arr[0];
            startIndex = 1;
        }

        for (int i = startIndex; i < arr.length - 1; i += 2) {
            int localMin, localMax;
            if (arr[i] < arr[i + 1]) {
                localMin = arr[i];
                localMax = arr[i + 1];
            } else {
                localMin = arr[i + 1];
                localMax = arr[i];
            }
            if (localMin < min) {
                min = localMin;
            }
            if (localMax > max) {
                max = localMax;
            }
        }

        return new int[] { min, max };
    }

}