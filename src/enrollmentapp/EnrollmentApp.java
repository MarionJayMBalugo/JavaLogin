/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enrollmentapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author 2ndyrGroupC
 */
public class EnrollmentApp {

    static int counter = 0;
    static int infoCount = 0;
    static int courCount = 0;

    public static boolean checkUser(String user, String password, String verifyPass) {
        boolean ans = false;
        for (int x = 0; x < user.length(); x++) {
            char y = user.charAt(x);
            if (Character.isDigit(y) || Character.isLetter(y) == false) {
                throw new NumberFormatException("opps! numbers are not allowed");
            }

        }

        if (password.length() <= 8) {
            throw new ArithmeticException("password must be more than 8 characters in length");
        } else {
            if (password.equals(verifyPass)) {
                ans = true;
                counter++;
            } else {
                System.out.println("password not match");
                ans = false;
            }
        }
        return ans;
    }

    public static boolean checkInfo(String fname, String lname, int age) {

        boolean ans = true;
        String name = fname + lname;
        for (int x = 0; x < name.length(); x++) {
            char y = name.charAt(x);

            if (Character.isDigit(y) || Character.isLetter(y) == false) {
                throw new NumberFormatException("opps! numbers are not allowed");
            }

        }
        if (age <= 18 || age >= 100) {
            ans = false;
            throw new ArithmeticException("you must be than 18 years old and younger than 100 yrs old");
        }
        return ans;

    }

    public static boolean checkCourse(String course, String sched, int unit) {
        boolean ans = true;
        if (course.isEmpty()) {
            ans = false;
            throw new NullPointerException("opp!no course?");
        }
        if (sched.isEmpty()) {
            ans = false;
            throw new NullPointerException("opps!no schedule?");
        }
        if (unit <= 0 || unit > 50) {
            ans = false;
            throw new ArithmeticException("invalid number of load");

        }
        courCount++;
        return ans;
    }

    public static int count() throws IOException {
        int lines = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\2ndyrGroupC\\Desktop\\Accounts.txt"));

            while (reader.readLine() != null) {
                lines++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("Error on connecting with file");
        } finally {
            System.out.println("initial number of lines in Account file " + lines);
            reader.close();
        }
        return lines;

    }

    public static int count1() throws IOException {
        int lines = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\2ndyrGroupC\\Desktop\\Personal_Info.txt"));

            while (reader.readLine() != null) {
                lines++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("Error on connecting with file");
        } finally {
            System.out.println("initial number of lines in Personal Information file " + lines);
            reader.close();
        }
        return lines;

    }

    public static int count2() throws IOException {
        int lines = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\2ndyrGroupC\\Desktop\\Courses.txt"));

            while (reader.readLine() != null) {
                lines++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("Error on connecting with file");
        } finally {
            System.out.println("initial number of lines in course file " + lines);
            reader.close();
        }
        return lines;

    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedWriter accFile = null;
        BufferedWriter courseFile = null;
        BufferedWriter infoFile = null;
        counter = count();
        infoCount = count1();
        courCount = count2();
        try {
            accFile = new BufferedWriter(new FileWriter("C:\\Users\\2ndyrGroupC\\Desktop\\Accounts.txt", true));
            infoFile = new BufferedWriter(new FileWriter("C:\\Users\\2ndyrGroupC\\Desktop\\Personal_Info.txt", true));
            courseFile = new BufferedWriter(new FileWriter("C:\\Users\\2ndyrGroupC\\Desktop\\Courses.txt", true));
            courseFile.append("");
        } catch (IOException e) {
            System.out.println("all opened connection terminated");
            System.out.println("error while connecting");
        }
        Scanner write = new Scanner(System.in);
        String userAns = "";
        String passAns = "";
        String userInp = null;
        String passInp = null;
        boolean a = false;
        int b = 1;
        while (b != 0) {
            while (a == false) {

                System.out.println("enter your username please");
                userAns = write.next();
                System.out.println("enter your password please");
                passAns = write.next();
                System.out.println("verify your password please");
                String passVerify = write.next();
                try {
                    accFile = new BufferedWriter(new FileWriter("C:\\Users\\2ndyrGroupC\\Desktop\\Accounts.txt", true));
                    a = checkUser(userAns, passAns, passVerify);
                    accFile.write(String.format("%d \t\t %s \t\t %s", counter, userAns, passAns));
                    accFile.newLine();
                    System.out.println("success.account verified");

                } catch (ArithmeticException | NullPointerException | NumberFormatException e) {
                    System.out.println(e);
                    a = false;
                } finally {
                    accFile.close();
                }

            }
            String fname = "";
            String lname = "";
            int age = 0;
            int match1 = 1;
            while (match1 != 0) {
                a = false;
                if (a == false) {
                    System.out.println("enter your Firstname please");
                    fname = write.next();
                    System.out.println("enter your Lastname please");
                    lname = write.next();
                    System.out.println("verify your age please");
                    age = write.nextInt();
                    try {
                        a = checkInfo(fname, lname, age);
                        infoCount++;
                        infoFile = new BufferedWriter(new FileWriter("C:\\Users\\2ndyrGroupC\\Desktop\\Personal_Info.txt", true));
                        infoFile.write(String.format("%d \t\t %d \t\t %s \t\t %s \t\t %d", infoCount, counter, fname, lname, age));
                        infoFile.newLine();

                        System.out.println("success.Information acceptable");

                    } catch (ArithmeticException | NullPointerException | NumberFormatException e) {
                        System.out.println(e);
                        a = false;
                    } finally {
                        infoFile.close();
                    }
                }
                System.out.println("do you wish to proceed?");
                match1 = write.nextInt();
            }

            String course = "";
            String sched = "";
            int unit = 0;
            match1 = 1;
            while (match1 != 0) {
                a = false;
                while (a == false) {
                    System.out.println("enter your  course please");
                    course = write.next();
                    System.out.println("enter your schedule please");
                    sched = write.next();
                    System.out.println("verify your unit please");
                    unit = write.nextInt();
                    try {
                        courseFile = new BufferedWriter(new FileWriter("C:\\Users\\2ndyrGroupC\\Desktop\\Courses.txt", true));
                        a = checkCourse(course, sched, unit);
                        courseFile.write(String.format("%d \t\t %d \t\t %s \t\t %d \t\t %s", courCount, counter, course, unit, sched));
                        courseFile.newLine();

                        System.out.println("success your enrolled");
                    } catch (ArithmeticException | NullPointerException e) {
                        System.out.println(e);
                        a = false;
                    } finally {
                        courseFile.close();
                    }

                }
                System.out.println("do you wish to proceed?");
                match1 = write.nextInt();
            }
            System.out.println("do you wish to end the process?");
            b = write.nextInt();
        }

    }
}
