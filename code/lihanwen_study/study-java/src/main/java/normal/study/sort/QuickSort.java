package normal.study.sort;

/**
 * @author lihanwen
 * @date 20210321
 * 快速排序
 * https://blog.csdn.net/shujuelin/article/details/82423852
 */
public class QuickSort {
    public static void main(String[] args){
        int[] arr = {6,1,2,7,9,3,4,5,10,8};

        quicksort(arr, 0 , arr.length -1);
        for (int i = 0; i< arr.length; i++){
            System.out.println(arr[i]);
        }

    }

    public static void quicksort(int[] arr,int low,int high){
        int i,j,temp,t;
        //当前后数字大小颠倒，则停止查找
        if (low > high){
            return;
        }

        i = low;
        j = high;

        //temp为基准单位
        temp = arr[low];

        while (i < j){
            //先看右边，依次向左递减
            while (temp <= arr[j] && i < j){
                j--;
            }
            //在看左边，依次向右递增
            while (temp >= arr[i] && i < j){
                i++;
            }
            //最终找到满足条件信息，左右两边数值进行交换
            if (i < j){
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }

        //交换完毕后，将基准值与i/j对应数值进行交换
        arr[low] = arr[i];
        arr[i] = temp;

        //递归调用左半边数组
        quicksort(arr, low, j-1);
        //递归调用右半边数组
        quicksort(arr, j +1, high);
    }

}
