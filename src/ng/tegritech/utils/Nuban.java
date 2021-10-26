package ng.tegritech.utils;

import com.sun.istack.internal.NotNull;
import ng.tegritech.enums.Banks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Nuban {
    private static final String SEED = "373373373373";
    private static final char[] SEED_ARRAY = SEED.toCharArray();
    private static final int NUBAN_LENGTH = 10;
    private static final int SERIAL_NUMBER_LENGTH = 9;

    /**
     * Function to generate NUBAN check digit
     * which is required for validation
     *
     * @param serialNumber
     * @param bankCode
     * @return int (the Nuban check digit)
     */
    public static int generateCheckDigit(String serialNumber, String bankCode) {
        if (serialNumber.length() > SERIAL_NUMBER_LENGTH) {
            throw new IllegalArgumentException(String.format("Serial number should be at most %d -digits long", SERIAL_NUMBER_LENGTH));
        }

        serialNumber = StringUtils.padLeftZeros(serialNumber, SERIAL_NUMBER_LENGTH);

        String cipher = bankCode + serialNumber;

        int sum = 0;

        char[] cipherArray = cipher.toCharArray();

        // Step 1. Calculate A*3+B*7+C*3+D*3+E*7+F*3+G*3+H*7+I*3+J*3+K*7+L*3
        for (int i = 0; i < cipherArray.length; i++) {
            sum += Character.getNumericValue(cipherArray[i]) * Character.getNumericValue(SEED_ARRAY[i]);
        }

        // Step 2: Calculate Modulo 10 of your result i.e. the remainder after dividing by 10
        sum %= 10;

        // Step 3. Subtract your result from 10 to get the Check Digit
        int checkDigit = 10 - sum;

        // Step 4. If your result is 10, then use 0 as your check digit
        checkDigit = checkDigit == 10 ? 0 : checkDigit;

        return checkDigit;
    }

    /**
     * Algorithm source: @link https://www.cbn.gov.ng/OUT/2011/CIRCULARS/BSPD/NUBAN%20PROPOSALS%20V%200%204-%2003%2009%202010.PDF
     * The approved NUBAN format ABC-DEFGHIJKL-M where
     * ABC is the 3-digit bank code assigned by the CBN
     * DEFGHIJKL is the NUBAN Account serial number
     * M is the NUBAN Check Digit, required for account number validation
     *
     * @param accountNumber
     * @param bankCode
     * @return true if accountNumber is valid otherwise false
     */
    public static boolean isAccountNumberValid(@NotNull String accountNumber, @NotNull String bankCode) {
        if (accountNumber.length() != NUBAN_LENGTH) {
            return false;
        }

        String serialNumber = accountNumber.substring(0, 9);
        int checkDigit = generateCheckDigit(serialNumber, bankCode);

        return checkDigit == Integer.parseInt(accountNumber.substring(9));
    }

    /**
     * Guess possible Bank of a NUBAN using CBN's algorithm
     * @param accountNumber
     * @return List of possible Bank(s) of the accountNumber
     */
    public static List<Banks> getBankFromAccountNumber(String accountNumber) {
        List<Banks> banks = new ArrayList<>();
        for (Banks b : Banks.values()) {
            if (isAccountNumberValid(accountNumber, b.getBankCode())) {
                banks.add(b);
            }
        }
        banks.sort(Comparator.comparing(Banks::getBankName));
        return banks;
    }
}
