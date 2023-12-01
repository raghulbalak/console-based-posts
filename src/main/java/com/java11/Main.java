package com.java11;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, User> users = new HashMap<>();
    static Map<String, Post> posts = new LinkedHashMap<>();

    static class Post {
        String userName;
        String description;
        List<String> likes;
        List<String> comments;
        Date postedTime;

        public String getUserName() {
            return userName;
        }

        public Post(String userName, String description, List<String> likes, List<String> comments, Date postedTime) {
            this.userName = userName;
            this.description = description;
            this.likes = likes;
            this.comments = comments;
            this.postedTime = postedTime;
        }

        public String getDescription() {
            return description;
        }

        public List<String> getLikes() {
            return likes;
        }

        public List<String> getComments() {
            return comments;
        }

        public Date getPostedTime() {
            return postedTime;
        }
    }

    static class User {
        private String userName;
        private String userEmail;
        private String password;

        User(String userName, String userEmail, String password) {
            this.userName = userName;
            this.userEmail = userEmail;
            this.password = password;
        }

        public String getUserName() { return this.userName; }

        public String getUserEmail() { return this.userEmail; }

        public String getPassword() { return this.password; }

    }

    public static void main(String[] args) {
        System.out.println("testValue");
        int homePageInput = signingOptions();
        signingCases(homePageInput);
//        while(args)

    }

    public static int signingOptions() {
        System.out.println("Please choose from the below options:\n1.Sign up\n2.Sign in\n");
        List<Integer> validChoices = Arrays.asList(1,2);
        int choice;
        do{
            System.out.print("Please choose value from the listed options:");
            choice = scanner.nextInt();
        } while (!validChoices.contains(choice));
        return choice;
    }

    public static void signingCases(int choice) {
        switch (choice) {
            case 1:
                boolean validInput = true;
                do {
                    if (!validInput)
                        System.out.println("Please enter valid userName/email/password");
                    System.out.println("Enter the username:");
                    String userName = scanner.next();
                    System.out.println("Enter the user-email:");
                    String userEmail = scanner.next();
                    validInput = userEmail.matches(".*@.*");
                    System.out.println("Enter the password:");
                    String password = scanner.next();
                    users.put(userName, new User(userName, userEmail, password));
                    System.out.println("users: " + users.toString());
                } while (!validInput);
                choice = signingOptions();
                signingCases(choice);
                break;
            case 2:
                boolean signInFlag = false;
                do {
                    System.out.println("Enter the user name:");
                    String userName = scanner.next();
                    System.out.println("Enter the user password:");
                    String password = scanner.next();
                    if (users.containsKey(userName)) {
                        User user = users.get(userName);
                        if (password.equalsIgnoreCase(user.getPassword())) {
                            homePageOptions();
                        } else {
                            System.out.println("Entered username/password is wrong.");
                            signInFlag = true;
                        }

                    }
                } while(signInFlag);

        }
    }

    public static int homePageOptions() {
        System.out.println("Choose the action to be performed:\n1.View all posts \n2.Post a tweet \n3.Back to main menu ");
        int choice = getAChoice();
        List<Integer> validChoices = Arrays.asList(1,2,3);
        while(!validChoices.contains(choice)) { System.out.println("Please enter a valid choice:");
            choice = scanner.nextInt();
        }
        switch (choice) {
            case 1:
            case 2:
                break;
            case 3:
                choice = signingOptions();
                signingCases(choice);
                break;
        }
        return 0;
    }

    public static int getAChoice() {
        int choice;
        do {
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("Please enter a number:");
                choice = 0;
            } catch (NoSuchElementException exception) {
                choice = 0;
            }
        } while(choice == 0);
        return choice;
    }
}