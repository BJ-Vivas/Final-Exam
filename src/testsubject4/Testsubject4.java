package testsubject4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Testsubject4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Enter the number of users: ");
        int numUsers = scanner.nextInt();
        scanner.nextLine(); 

        
        List<String> users = new ArrayList<>();
        System.out.print("(Note: Use comma to separte the users)" + '\n' + "Enter the list of users: ");
        String[] userStrs = scanner.nextLine().split(",");
        for (String userStr : userStrs) {
            users.add(userStr.trim());
        }

        
        System.out.print("Enter the number of friendships: ");
        int numFriendships = scanner.nextInt();
        scanner.nextLine(); 

        
        List<Pair<String, String>> friendships = new ArrayList<>();
        System.out.print("Enter the list of friendships (comma-separated pairs, e.g., 1,2 or Jimboy,Felmar): ");
        for (int i = 0; i < numFriendships; i++) {
            String[] friendshipStrs = scanner.nextLine().split(",");
            String user1 = friendshipStrs[0].trim();
            String user2 = friendshipStrs[1].trim();
            friendships.add(new Pair<>(user1, user2));
        }

        
        System.out.print("Enter the target user: ");
        String targetUser = scanner.nextLine().trim();

        
        SocialNetwork network = new SocialNetwork(users, friendships);

        
        List<String> recommendations = network.getFriendRecommendations(targetUser);

        
        System.out.println("Recommendations for user " + targetUser + ": " + recommendations);
    }
}
    
