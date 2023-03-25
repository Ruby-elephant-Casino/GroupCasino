package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.io.*;
import java.util.HashMap;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccountManager` stores, manages, and retrieves `ArcadeAccount` objects
 * it is advised that every instruction in this class is logged
 */
public class CasinoAccountManager {
    // all consoles
    private final IOConsole console = new IOConsole(AnsiColor.BLUE);
    private final IOConsole errorConsole = new IOConsole(AnsiColor.RED);
    private final IOConsole successConsole = new IOConsole(AnsiColor.YELLOW);

    private HashMap<String,CasinoAccount> accountMap;

    // constructor
    public CasinoAccountManager(){
        accountMap = new HashMap<>();
        getAllAccounts();
    }


    // getter and setter
    public HashMap<String, CasinoAccount> getAccountMap() {
        return accountMap;
    }

    public void setAccountMap(HashMap<String, CasinoAccount> accountMap) {
        this.accountMap = accountMap;
    }

    // File IO to keep all accounts saved and able to access when program runs or exits
    public void getAllAccounts(){
        BufferedReader bufferedReader;
        try{
            bufferedReader = new BufferedReader(new FileReader("accountDB.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] items = line.split(",");
                CasinoAccount account = new CasinoAccount(items[0], items[1], Double.parseDouble(items[2]));
                accountMap.put(items[0],account);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File 'accountDB.txt' is not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveAllAccounts(){
        BufferedWriter bufferedWriter;
        try{
            bufferedWriter = new BufferedWriter(new FileWriter("accountDB.txt"));
            for(CasinoAccount account : accountMap.values()){
                bufferedWriter.write(account.getName()+","+account.getPassword()+","+account.getBalance());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File 'accountDB.txt' is not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // get account
    public CasinoAccount getAccount(String accountName, String accountPassword) {
        return accountMap.get(accountName);
//        String currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
//        String currentClassName = getClass().getName();
//        String errorMessage = "Method with name [ %s ], defined in class with name [ %s ] has  not yet been implemented";
//        throw new RuntimeException(String.format(errorMessage, currentMethodName, currentClassName));
    }

    public CasinoAccount createAccount(String accountName, String accountPassword) {
        return new CasinoAccount(accountName,accountPassword);
    }

    public String askForAccountName(){
        while(true) {
            String accountName = console.getStringInput("Enter your account name:");
            if (checkAccountName(accountName)) { // if name already exist
                errorConsole.println("Account name already exists! Please choose another one.");
            } else {
                return accountName;
            }
        }
    }

    public String askForPassword(){
        return console.getStringInput("Enter your password:");
    }

    public boolean checkAccountName(String accountName){
        // return true if account name already exist
        return accountMap.containsKey(accountName);
    }
    public boolean checkAccount(String accountName, String accountPassword){
        // return true account and password match given params
        return accountMap.get(accountName).getPassword().equals(accountPassword);
    }
    public void registerAccount(CasinoAccount casinoAccount) {
        accountMap.put(casinoAccount.getName(),casinoAccount);
    }

}
