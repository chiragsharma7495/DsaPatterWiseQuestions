public class practice {

    public  static int missingNumber(int arr1[] , int arr2[]){
        int sum1 = 0; int sum2 = 0;
        int totalSum = 0;

        for(int i=0; i<arr1.length; i++){
            sum1 += arr1[i];
        }

        for(int i=0; i<arr2.length; i++){
            sum2 += arr2[i];
        }
         totalSum = sum1 - sum2;
        return totalSum;
    }

    public static void main(String[] args) {
        int arr1[] = {1,2,3,4,5};
        int arr2[] = {4,3,2,1};
        System.out.println(missingNumber(arr1,arr2));
    }
}
