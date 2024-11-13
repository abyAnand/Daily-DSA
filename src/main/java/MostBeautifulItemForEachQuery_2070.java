import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MostBeautifulItemForEachQuery_2070 {

    public int[] maximumBeauty(int[][] items, int[] queries) {
        // Sort items by price in ascending order
        Arrays.sort(items, Comparator.comparingInt(item -> item[0]));

        // Prepare list to store the price-beauty brackets with a max price value
        List<int[]> beautyBrackets = new ArrayList<>();
        beautyBrackets.add(new int[]{0, 0, Integer.MAX_VALUE}); // Initial bracket with default values

        // Populate beauty brackets based on items
        for (int[] item : items) {
            int price = item[0];
            int beauty = item[1];
            int[] lastBracket = beautyBrackets.get(beautyBrackets.size() - 1); // Get the last added bracket
            int lastMaxBeauty = lastBracket[1]; // Get the maximum beauty recorded in the last bracket

            // If the current item's beauty exceeds the beauty in the last recorded bracket
            // we should add a new price-beauty bracket. This avoids adding items with lower or
            // equal beauty at a higher price, as we already have the best beauty for that price range.
            if (beauty > lastMaxBeauty) {
                lastBracket[2] = price; // Update the maximum price for the last bracket
                beautyBrackets.add(new int[]{price, beauty, Integer.MAX_VALUE}); // Add a new bracket
            }
        }

        // Prepare result array to store maximum beauty per query
        int[] result = new int[queries.length];

        // Resolve each query to find the maximum beauty within the price constraint
        for (int j = 0; j < queries.length; j++) {
            int queryPrice = queries[j];

            // Perform binary search to find the highest price <= queryPrice
            int low = 0;
            int high = beautyBrackets.size() - 1;
            int bestBeauty = 0; // Default beauty if no valid bracket is found

            while (low <= high) {
                int mid = low + (high - low) / 2;
                int[] bracket = beautyBrackets.get(mid);

                // If the price is less than or equal to queryPrice, this is a valid bracket
                if (bracket[0] <= queryPrice) {
                    bestBeauty = bracket[1]; // Update best beauty for this valid bracket
                    low = mid + 1; // Search in the right half (higher price)
                } else {
                    high = mid - 1; // Search in the left half (lower price)
                }
            }

            result[j] = bestBeauty; // Store the result for this query
        }

//        // Resolve each query to find the max beauty within the price constraint
//        for (int j = 0; j < queries.length; j++) {
//            int queryPrice = queries[j];
//            for (int i = beautyBrackets.size() - 1; i >= 0; i--) {
//                if (beautyBrackets.get(i)[0] <= queryPrice) {
//                    result[j] = beautyBrackets.get(i)[1];
//                    break;
//                }
//            }
//        }

        return result;
    }
}