package ng.tegritech;

import ng.tegritech.enums.Banks;
import ng.tegritech.utils.Nuban;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println(Nuban.isAccountNumberValid("0120999214", "058"));
        for (Banks banks : Nuban.getBankFromAccountNumber("0782647673")) {
            System.out.println(banks.toString());
        }
    }
}
