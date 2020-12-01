/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.util.ArrayList;

/**
 *
 * @author Theodore Tsimiklis
 */
public class Bank {

    private static ArrayList<User> users = new ArrayList<>();

    public static void addUsers (String userName, String password) {
        users.add(new User(userName, password));
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        Bank.users = users;
    }
 
    
}
