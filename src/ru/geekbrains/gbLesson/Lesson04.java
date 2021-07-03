package ru.geekbrains.gbLesson;

import java.util.Random;
import java.util.Scanner;

public class Lesson04 {


    static final int SIZE = 5;
    static final int DOTS_TO_WIN = 4;

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;

    static final Scanner sc = new Scanner(System.in);
    static final Random random = new Random();


    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Вы победили!!!");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Комьютер победил. Умные они...");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
        }
    }


    public static void humanTurn() {
        int y;
        int x;

        do {
            System.out.println("input X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));

        map[y][x] = DOT_X;
    }

    public static void aiTurn() {
        int y;
        int x;

        do {
            y = random.nextInt(SIZE);
            x = random.nextInt(SIZE);
        } while (!isCellValid(y, x));

        map[y][x] = DOT_O;
    }

    public static boolean isCellValid(int y, int x) {
        if (y < 0 || x < 0 || y >= SIZE || x >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%d ", i + 1);
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%d ", i + 1);
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%c ", map[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

//        boolean vert, hors; // по вертикали, по горизонтали
//        for (int i = 0; i < 4; i++) {
//            vert = true; //будем считать что изначально тру
//            hors = true; //будем считать что изначально тру
//            for (int j = 0; j < 4; j++) {
//                vert &= (map[i][j] == c);// если оба тру, то тру, сначала проверяем каждую строку
//                hors &= (map[j][i] == c);// аналогично проверяем каждый столбец
//            }
//            if (vert || hors) return true; //если одно из условий истинно, то истина
//        }
//
//        boolean right, left;
//        right = true; //диагональ слева направо
//        left = true;  //диагональ справа налево
//        for (int i = 0; i < 4; i++) {
//            right &= (map[i][i] == c); //если правда, то логическое умножение на true = true
//            left &= (map[4 - i - 1][i] == c); //аналогично
//        }
//
//        if (right || left) return true; // пишет что то можно упростить
//
//        return false;
//    }
//
//
//
//
//            if (map[0][0] == c && map[0][1] == c && map[0][2] == c) { return true; }
//            if (map[1][0] == c && map[1][1] == c && map[1][2] == c) { return true; }
//            if (map[2][0] == c && map[2][1] == c && map[2][2] == c) { return true; }
//
//            if (map[0][0] == c && map[1][0] == c && map[2][0] == c) { return true; }
//            if (map[0][1] == c && map[1][1] == c && map[2][1] == c) { return true; }
//            if (map[0][2] == c && map[1][2] == c && map[2][2] == c) { return true; }
//
//            if (map[0][0] == c && map[1][1] == c && map[2][2] == c) { return true; }
//            if (map[0][2] == c && map[1][1] == c && map[2][0] == c) { return true; }


    // Домашнее задание
    // переписываем проверку победы по циклам условиям - задание №2, №3 - для поля 5, ячеек для победы - 4

    public static boolean checkWin(char c) { // проверяем для размера поля SIZE, Ячеек - DOTS_TO_WIN
        for (int stl=0; stl<SIZE-DOTS_TO_WIN+1; stl++) { // для столбца
            for (int str=0; str<SIZE-DOTS_TO_WIN+1; str++) { //для строки
                if (checkDiag(c, stl, str) || checkLine(c, stl, str)) return true;
            }
        }
        return false;
    }

    public static boolean checkDiag(char с, int sdvigX, int sdvigY) { // проверка диагоналей
        boolean right, left;
        right = true;
        left = true;
        for (int i=0; i<DOTS_TO_WIN; i++) {
            right &= (map[i+sdvigX][i+sdvigY] == с); // если правда, то логическое умножение на true = true
            left &= (map[DOTS_TO_WIN-i-1+sdvigX][i+sdvigY] == с); //если правда, то логическое умножение на true = true
        }
        if (right || left) return true;
        return false;
    }

    public static boolean checkLine(char c, int sdvigX, int sdvigY) { // проверяем горизонтали и вертикали
        boolean cols, rows;
        for (int stl=sdvigX; stl<DOTS_TO_WIN+sdvigX; stl++) {
            cols = true;
            rows = true;
            for (int str=sdvigY; str<DOTS_TO_WIN+sdvigY; str++) {
                cols &= (map[stl][str] == c);
                rows &= (map[str][stl] == c);
            }
            if (cols || rows) return true;
        }
        return false;
    }
}









