import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }
        int bridge_weight = 0;
        int time = 0;
        int truck_index = 0;
        while (truck_index < truck_weights.length) {
            bridge_weight -= bridge.poll();
            if (bridge_weight + truck_weights[truck_index] > weight) {
                bridge.add(0);
            } else {
                bridge.add(truck_weights[truck_index]);
                bridge_weight += truck_weights[truck_index++];
            }
            time++;
        }
        for (int i = 0; i < bridge_length; i++) {
            bridge.poll();
            time++;
        }
        return time;
    }
}