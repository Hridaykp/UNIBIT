import java.util.*;
public class task{
    // In this method I make pairs in 2d array which is equal to target
    public static int[][] pairsTarget(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<n; i++) {
            List<Integer>list = new ArrayList<>();
            int elem = target - nums[i];
            if (map.containsKey(elem)) {
                list.add(elem);
                list.add(nums[i]);
                res.add(list);
            }
            map.put(nums[i], i);
        }
        int [][] pairs = new int[res.size()][2];
        for(int i=0; i<res.size(); i++){
            for(int j=0; j<2; j++){
                pairs[i][j] = res.get(i).get(j);
            }
        }
        return pairs;
    }

    // Here I merged all the pairs in 1d array in ascending order
    public static int[] mergedArrays(int[][] arr) {
        List<Integer> list = new ArrayList<>();
        for (int[]a : arr) {
            for (int n : a) {
                list.add(n);
            }
        }
        Collections.sort(list);
        int size = list.size();
        int[] result = new int[size];
        for (int i=0; i<size; i++) 
            result[i] = list.get(i);
        return result;
    }

    //This is the method where I doubled the target and store them in 2d List/array with recursive call using helper method
    private static List<List<Integer>> doublePairsTarget(int[] nums, int target) {
        return helper(nums, 0, 4, target);
    }
    private static List<List<Integer>> helper (int[] nums, int start, int k, int target) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(k == 2) { 
            int left = start, right = len - 1;
            while(left < right) {
                int sum = nums[left] + nums[right];
                if(sum == target) {
                    List<Integer> path = new ArrayList<Integer>();
                    path.add(nums[left]);
                    path.add(nums[right]);
                    res.add(path);
                    while(left < right && nums[left] == nums[left + 1]) 
                        left++;
                    while(left < right && nums[right] == nums[right - 1]) 
                        right--;
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else { 
                    right--;
                }
            }
        } else {
            for(int i = start; i < len - (k - 1); i++) {
                if(i > start && nums[i] == nums[i - 1]) 
                    continue;
                List<List<Integer>> temp = helper(nums, i+1, k-1, target-nums[i]);
                for(List<Integer> t : temp) {
                   t.add(0, nums[i]);
                }                    
                res.addAll(temp);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt(); // length of input array 
            int nums[] = new int[n];
            for(int i=0; i<n; i++){
                nums[i] = sc.nextInt(); // input array
            }
            int target = sc.nextInt();
            int pairs[][] = pairsTarget(nums, target); // pairs which are equals to target
            int mergedArr[] = mergedArrays(pairs);     // merged them in asc order
            List<List<Integer>> result = doublePairsTarget(mergedArr, 2*target);  // final 2d list with doubled target
            System.out.print(result);
        }
    }
}


