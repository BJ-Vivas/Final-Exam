
package testsubject4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


class SocialNetwork {

    private Map<String, List<String>> graph;

    public SocialNetwork(List<String> users, List<Pair<String, String>> friendships) {
        this.graph = buildAdjacencyList(users, friendships);
    }

    public List<String> getFriendRecommendations(String targetUser) {
        
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(targetUser);
        visited.add(targetUser);

        List<String> recommendations = new ArrayList<>();

        while (!queue.isEmpty()) {
            String current = queue.poll();

            
            for (String neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

       
        for (String user : visited) {
            if (!user.equals(targetUser) && !isDirectFriend(targetUser, user)) {
                recommendations.add(user);
            }
        }

        return recommendations;
    }

   
    private boolean isDirectFriend(String user1, String user2) {
        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            if (entry.getKey().equals(user1) && entry.getValue().contains(user2) ||
                entry.getKey().equals(user2) && entry.getValue().contains(user1)) {
                return true;
            }
        }
        return false;
    }

    
    private static Map<String, List<String>> buildAdjacencyList(List<String> users, List<Pair<String, String>> friendships) {
        Map<String, List<String>> graph = new HashMap<>();
        for (Pair<String, String> friendship : friendships) {
            graph.computeIfAbsent(friendship.first, k -> new ArrayList<>()).add(friendship.second);
            graph.computeIfAbsent(friendship.second, k -> new ArrayList<>()).add(friendship.first);
        }
        return graph;
    }
}

class Pair<T, U> {
    public T first;
    public U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
}
