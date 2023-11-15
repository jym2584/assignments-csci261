import java.util.Arrays;
import java.util.Scanner;

public class WeightedInversionsTwo {

    static long weightOfInversionCount = 0;

    public static long merge(long[] array, long[] temp, long[] indexArray, long[] temporaryIndexArray, int left, int midpoint, int right){
        int i = left;
        int j = midpoint;
        int k = left;

        int inversionCount = 0;
        System.out.println(String.format("array: %s", Arrays.toString(array)));
        while((i <= midpoint - 1) && (j <= right)){
            if(array[i] <= array[j]){
                temp[k] = array[i];
                temporaryIndexArray[k] = indexArray[i];
                k++;
                i++;
            }
            else{
                temp[k] = array[j];
                temporaryIndexArray[k] = indexArray[j];

                inversionCount = inversionCount + (midpoint - i);
                System.out.println(inversionCount);

                for(int x = (midpoint - i); x > 0; x--){
                    weightOfInversionCount += (indexArray[j] - indexArray[i + x - 1]);
                }

                k++;
                j++;
            }
        }

        while (i <= midpoint - 1){
            temp[k] = array[i];
            temporaryIndexArray[k] = indexArray[i];
            k++;
            i++;
        }

        while(j <= right){
            temp[k] = array[j];
            temporaryIndexArray[k] = indexArray[j];
            k++;
            j++;
        }

        for(i = left; i <= right; i++){
            array[i] = temp[i];
            indexArray[i] = temporaryIndexArray[i];
        }

        return inversionCount;
    }

    public static long mergeSort(long[] array, long[] temp, long[] indexArray, long[] temporaryIndexArray, int left, int right){

        int midpoint;
        long inversionCount = 0;

        if(right > left){
            midpoint = ((right + left) / 2);

            inversionCount = mergeSort(array, temp, indexArray, temporaryIndexArray, left, midpoint);
            inversionCount += mergeSort(array, temp, indexArray, temporaryIndexArray, midpoint + 1, right);

            inversionCount += merge(array, temp, indexArray, temporaryIndexArray, left, midpoint + 1, right);
        }

        return inversionCount;
    }

    public static void main(String[] args) {
        long[] permutationArray = new long[] {2, 5, 3, 1, 4};
        int permutationSize = permutationArray.length;
        long[] temporaryArray = new long[permutationSize];

        long[] indexArray = new long[permutationSize];
        long[] temporaryIndexArray = new long[permutationSize];

        for(int i = 0; i < permutationSize; i++){
            indexArray[i] = i;
        }

        weightOfInversionCount = 0;

        mergeSort(permutationArray, temporaryArray, indexArray, temporaryIndexArray, 0, (permutationSize - 1));
        System.out.println(weightOfInversionCount);
    }
}