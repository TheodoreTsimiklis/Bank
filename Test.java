/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

/**
 *
 * @author Theodore Tsimiklis
 */
public class Test {
    public static void main(String[] args) {
        Bank.addUsers("Theo", "1234");
        
        Atm a1 = new Atm();
        a1.pipeline();
        System.out.println(Bank.getUsers().get(0).getHistory());
    }
}
