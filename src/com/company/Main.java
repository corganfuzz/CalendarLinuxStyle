package com.company;

import java.util.Scanner;

public class Main {

    static Object[] getInputs() {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the full year: ");
        int year = input.nextInt();

        System.out.print("Enter month as a number 1 and 12: ");
        int month = input.nextInt();

        return new Object[]{year, month};
    }

    static void printMonth(int year, int month) {
        printMonthTitle(year, month);
        printMonthBody(year, month);
    }

    static void printMonthTitle(int year, int month) {
        System.out.println("      " + getMonthName(month) + " " + year);
        System.out.println("----------------------------------");
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
    }

    static void printMonthBody(int year, int month) {
        int startDay = getStartDay(year, month);
        int numberOfDaysInMonth = getNumberOfDaysInMonth(year, month);

        // pad space before the first day

        int i = 0;
        for (i = 0; i < startDay; i++) {
            System.out.print("    ");
        }

        for (i = 1; i <= numberOfDaysInMonth; i++) {
            System.out.printf("%4d", i);

            if ((i + startDay) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    static String getMonthName(int month) {
        return switch (month) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "";
        };
    }

    static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    static int getNumberOfDaysInMonth(int year, int month) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> isLeapYear(year) ? 29 : 28;
            default -> 0;
        };
    }

    static int getTotalNumberOfDays(int year, int month) {
        int total = 0;

        //get total days from 1800 to 1/1/year
        for (int i = 1800; i < year; i++) {
            if (isLeapYear(i)) {
                total = total + 366;
            } else {
                total = total + 365;
            }
        }

        // add days from jab to the month prior to the calendar month
        for (int i = 1; i < month; i++) {
            total = total + getNumberOfDaysInMonth(year, i);
        }
        return total;
    }

    static int getStartDay(int year, int month) {
        final int START_DAY_FOR_JAN_1_1800 = 3;
        int totalNumberOfDays = getTotalNumberOfDays(year, month);
        return (totalNumberOfDays + START_DAY_FOR_JAN_1_1800) % 7;
    }

    public static void main(String[] args) {
        Object[] dateInputs = getInputs();
        int year = (Integer) dateInputs[0];
        int month = (Integer) dateInputs[1];
        printMonth(year, month);
    }
}

























