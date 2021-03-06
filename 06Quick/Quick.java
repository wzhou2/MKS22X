import java.util.Arrays;
public class Quick {
    
    public static int partition(int[] data, int start, int end) {
        int p = (int) (Math.random()*(end-start) + start);
        int pivot = data[p];
        int low = start+1;
        int high = end;
        swap(data, p, start);
        while (low <= high) {
            if (data[low] > pivot) {
                swap(data, low, high);
                high--;
            } else {
                low++;
            }
        }
        // System.out.println("p"+Arrays.toString(data));
        swap(data, start, high);
        // System.out.println("pIndex"+p+",value"+pivot);
        return high;
     }
    
    public static int[] partitionDutch(int[] data, int start, int end) {
        int p = (int) (Math.random()*(end-start) + start);
        int pivot = data[p];
        int low = start;
        int high = end;
        int i = start;
        while (i <= high) {
            if (data[i] == pivot) {
                i++;
            } else if (data[i] < pivot) {
                swap(data, i, low);
                low++;
                i++;
            } else {
                swap(data, i, high);
                high--;
            }
        }
        // System.out.println("p"+Arrays.toString(data));
        // swap(data, start, high);
        // System.out.println("pIndex"+p+",value"+pivot);
        int[] ary = {low, high};
        return ary;
    }
    
    public static void swap(int[] data, int start, int end) {
        int temp = data[end];
        data[end] = data[start];
        data[start] = temp;
    }
    
    public static int quickselect(int[] data, int k) {
        return quickselect(data, k, 0, data.length-1);
    }
    
    public static int quickselect(int[] data, int k, int start, int end) {
        // System.out.println(""+start+","+end+Arrays.toString(data));
        int[] indexes = partitionDutch(data, start, end);
        // System.out.println("part"+Arrays.toString(data));
        // System.out.println("k"+k+",index"+index);
        if (indexes[1] == k) {
            return data[k];
        } else if (indexes[1] < k) {
            return quickselect(data, k, indexes[1], end);
        } else {
            return quickselect(data, k, start, indexes[0]);
        }
    }
    
    public static void quicksort(int[] data) {
        quicksort(data, 0, data.length-1);
	//System.out.println(Arrays.toString(data));
    }
    
    public static void quicksort(int[] data, int start, int end) {
        if (end - start <= 5) {
            // System.out.println("Insertion next"+start+","+end+Arrays.toString(data));
            insertionsort(data, start, end);
        } else {
            // System.out.println("qstart"+start+"end"+end);
            if (start <= end) {
                int[] indexes = partitionDutch(data, start, end);
                // System.out.println("qIndex"+indexes[0]+","+indexes[1]+Arrays.toString(data));
                quicksort(data, start, indexes[0]-1);
                quicksort(data, indexes[1]+1, end);
            }
        }
    }

    public static void insertionsort(int[] data, int start, int end) {
        for (int x = start; x < end+1; x++) {
            int temp = data[x];
            int correctIndex = start;
            while ( correctIndex < x && temp > data[correctIndex]) {
                correctIndex++;
            }
            for (int i = x; i > correctIndex; i--) {
                data[i] = data[i-1];
            }
            data[correctIndex] = temp;
        }
    }
    
    public static void main(String[] args) {
        //int[] set = {17, 61, 67, 47, 93, 12, 20, 4, 44, 68};
        int[] set = {0,0,0,1,99,3,99,2,99};
        // System.out.println("@1"+Arrays.toString(set));
        // swap(set, 0, 8);
        // System.out.println("@1a"+Arrays.toString(set));
        // System.out.println(partition(set, 0, 8));
        // System.out.println("@2"+Arrays.toString(set));
        //System.out.println(partitionDutch(set, 1, 6));
        
        int[] ary = { 2, 10, 15, 23, 0,  5};
        //System.out.println(quickselect(ary, 1));
        //System.out.println(Arrays.toString(ary));
        
        System.out.println(Arrays.toString(ary));
        insertionsort(ary,0,ary.length-1);
        //quicksort(ary);
        System.out.println(Arrays.toString(ary));
        
    }
}
