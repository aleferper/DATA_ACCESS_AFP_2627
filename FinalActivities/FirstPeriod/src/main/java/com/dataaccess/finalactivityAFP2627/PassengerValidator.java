package com.dataaccess.finalactivityAFP2627;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Checks the data of the passengers before inserting them.
 */
public class PassengerValidator {

    // One letter followed by 7 digits (e.g. A1234568)
    private static final String PASSPORT_REGEX = "[A-Z][0-9]{7}";
    // 9 to 11 digits, optional '+' at the beginning (max 12 characters)
    private static final String PHONE_REGEX = "\\+?[0-9]{9,11}";

    public static List<String> validate(List<Passenger> passengers) {
        List<String> errors = new ArrayList<>();
        Set<String> passports = new HashSet<>();

        for (int i = 0; i < passengers.size(); i++) {
            Passenger p = passengers.get(i);
            String who = "Passenger #" + (i + 1);

            // Passport
            if (p.getPassportNo() == null) {
                errors.add(who + ": passportno is mandatory.");
            } else {
                if (!p.getPassportNo().matches(PASSPORT_REGEX)) {
                    errors.add(who + ": passport '" + p.getPassportNo()
                            + "' has an invalid format (one letter followed by 7 digits).");
                }
                if (passports.contains(p.getPassportNo())) {
                    errors.add(who + ": passport '" + p.getPassportNo() + "' is duplicated in the file.");
                } else {
                    passports.add(p.getPassportNo());
                }
            }

            // First name
            if (p.getFirstName() == null) {
                errors.add(who + ": firstname is mandatory.");
            } else if (p.getFirstName().length() > 20) {
                errors.add(who + ": firstname is too long (max 20 characters).");
            }

            // Last name
            if (p.getLastName() == null) {
                errors.add(who + ": lastname is mandatory.");
            } else if (p.getLastName().length() > 20) {
                errors.add(who + ": lastname is too long (max 20 characters).");
            }

            // Address (optional)
            if (p.getAddress() != null && p.getAddress().length() > 100) {
                errors.add(who + ": address is too long (max 100 characters).");
            }

            // Phone (optional)
            if (p.getPhone() != null && !p.getPhone().matches(PHONE_REGEX)) {
                errors.add(who + ": phone '" + p.getPhone() + "' has an invalid format (9 to 11 digits).");
            }
        }
        return errors;
    }
}
