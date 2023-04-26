package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class shoppingcart 
{
    public static void main( String[] args ) throws IOException{
        System.out.println( "Welcome to your cart!" );
        Scanner scanner = new Scanner(System.in);
        String dir = "";
        ArrayList<String> users = new ArrayList<>();
        ArrayList<String> cart = new ArrayList<>();
        String currentUser = "";

        if (args.length == 0) {
            dir = "db";
            File dirPath = new File(dir);
            dirPath.mkdir(); 
            } else {
            dir = args[0];
            File dirPath = new File(dir);
            dirPath.mkdir(); 
        }  

        String input = "";

        while (!input.equals("quit")) {
            System.out.println("> ");
            input = scanner.nextLine();
            
            if (input.startsWith("login")) {
                String username = input.substring(6);
                currentUser = username;
                File userFile = new File(dir + File.separator + username + ".txt");
                if (!userFile.exists()) {
                    userFile.createNewFile();
                    users.add(username);
                    if (userFile.length() == 0) {
                        FileWriter iniWriter = new FileWriter(userFile);
                        iniWriter.write(dir + "\\" + username + ".txt");
                        iniWriter.close();
                    }
                } else {
                    FileReader fr = new FileReader(userFile);
                    BufferedReader br = new BufferedReader(fr);
                    String fileLine = "";
                    if (userFile.length() == 0) {
                        FileWriter iniWriter = new FileWriter(userFile);
                        iniWriter.write(dir + File.separator + username + ".txt");
                        iniWriter.close();
                    }
                    if ((fileLine = br.readLine()) != null) {
                        cart.add(fileLine);
                    }
                    br.close();
                    fr.close();
                }
            }

                if (input.startsWith("add")) {
                    String currentScanString = "";
                    String inputFixed = input.replace(',', ' ');
                    Scanner inputScanner = new Scanner(inputFixed.substring(4));

                    while(inputScanner.hasNext()) {
                        currentScanString = inputScanner.next();
                        cart.add(currentScanString);    
                }
            }

                if (input.equals("users")) {
                    for (String user : users) {
                        System.out.println(user);
                    }
                }

                if (input.equals("save")) {
                    if (currentUser == null) {
                        System.out.println("Please login!");
                    } else {
                        FileWriter fw = new FileWriter(dir + File.separator + currentUser + ".txt");
                        PrintWriter pw = new PrintWriter(fw);
                        for (String item : cart) {
                            pw.write(item + "\n");
                        }
                        cart.clear();
                        pw.close();
                        fw.close();
                    }
                }
            }
            currentUser = null;
            scanner.close();
    }
}
