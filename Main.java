package ru.geekbrains.gbLesson;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random random = new Random();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        guessWord();


    }

    public static void guessWord() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot",
                "avocado", "broccoli", "carrot", "cherry", "garlic", "grape",
                "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive",
                "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        int secret = random.nextInt(words.length);
        System.out.println(words[secret]);
        System.out.println("input guessWord. ");

        while (true) {
            System.out.println("input word ");
            String answer = sc.nextLine();

            if (answer.equals(words[secret])) {
                System.out.println("yes");
                break;
            } else {
                for (int i = 0; i < 15; i++) {
                    if (i < words[secret].length() && i < answer.length()
                            && words[secret].charAt(i) == answer.charAt(i)) {
                    } else {
                        System.out.print("#");
                    }
                }
            }
        }
        System.out.println("You guess, secret word " + words[secret]);
    }
}
