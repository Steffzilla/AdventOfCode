package de.steffzilla.aoc.y2020;

import de.steffzilla.competitive.AocUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PassData {

    private boolean birthYear = false;
    private boolean issueYear = false;
    private boolean expirationYear = false;
    private boolean height = false;
    private boolean hairColor = false;
    private boolean eyeColor = false;
    private boolean passportId = false;
    private boolean countryId = false;

    private boolean checked = false;

    void addFieldValuePart1(String input) {
        System.out.println(input);
        String[] strings = input.split(":");
        switch (strings[0]) {
            case "byr" : birthYear = true; break;
            case "iyr" : issueYear = true; break;
            case "eyr" : expirationYear = true; break;
            case "hgt" : height = true; break;
            case "hcl" : hairColor = true; break;
            case "ecl" : eyeColor = true; break;
            case "pid" : passportId = true; break;
            case "cid" : countryId = true; break;
        }
        /*byr (Birth Year)
        iyr (Issue Year)
        eyr (Expiration Year)
        hgt (Height)
        hcl (Hair Color)
        ecl (Eye Color)
        pid (Passport ID)
        cid (Country ID) */
    }

    void addFieldValuePart2(String input) {
        System.out.println(input);
        String[] strings = input.split(":");
        switch (strings[0]) {
            case "byr" :
                birthYear = checkYear(strings[1], 1920, 2002);
                break;
            case "iyr" :
                issueYear = checkYear(strings[1], 2010, 2020);
                break;
            case "eyr" :
                expirationYear = checkYear(strings[1], 2020, 2030);
                break;
            case "hgt" :
                height = checkHeight(strings[1]);
                break;
            case "hcl" :
                hairColor = checkHexColor(strings[1]);
                break;
            case "ecl" :
                eyeColor = checkEyeColor(strings[1]);
                break;
            case "pid" :
                //pid (Passport ID) - a nine-digit number, including leading zeroes.
                passportId = checkPid(strings[1]);
                break;
            case "cid" : countryId = true; break;
        }
        /*byr (Birth Year)
        iyr (Issue Year)
        eyr (Expiration Year)
        hgt (Height)
        hcl (Hair Color)
        ecl (Eye Color)
        pid (Passport ID)
        cid (Country ID) */
    }

    private boolean checkHeight(String height) {
        boolean result = false;
        Pattern pattern = Pattern.compile("^\\d\\din$");
        Matcher matcher = pattern.matcher(height);
        if (matcher.find()) {
            int iHeight = Integer.parseInt(height.substring(0, 2));
            System.out.println(iHeight);
            if (iHeight >= 59 && iHeight <= 76) {
                System.out.println("Height VALID:" + height);
                result = true;
            }
        } else {
            pattern = Pattern.compile("^\\d\\d\\dcm$");
            matcher = pattern.matcher(height);
            if (matcher.find()) {
                int iHeight = Integer.parseInt(height.substring(0, 3));
                System.out.println(iHeight);
                if (iHeight >= 150 && iHeight <= 193) {
                    System.out.println("Height VALID:" + height);
                    result = true;
                }
            }
        }
        if (!result) {
            System.out.println("Height INVALID:" + height);
        }
        return result;
    }

    private boolean checkEyeColor(String color) {
        //amb blu brn gry grn hzl oth
        //System.out.println("------------------>"+color);
        boolean result = false;
        switch (color) {
            case "amb":
            case "blu":
            case "brn":
            case "gry":
            case "grn":
            case "hzl":
            case "oth":
                result = true;
                break;
        }
        System.out.println("---------->"+color+"|"+result);
        return result;
    }

    private boolean checkHexColor(String color) {
        boolean result = false;
        //Pattern pattern = Pattern.compile("^#[0-9a-f]{6}");
        Pattern pattern = Pattern.compile(AocUtils.HEX_COLOR_PATTERN_6CHARS);
        Matcher matcher = pattern.matcher(color);
        if (matcher.find()) {
            System.out.println("Color VALID:"+color);
            result = true;
        } else {
            System.out.println("Color invalid:"+color);
        }
        return result;
    }

    private boolean checkPid(String pid) {
        boolean result = false;
        Pattern pattern = Pattern.compile("^\\d{9}$");
        Matcher matcher = pattern.matcher(pid);
        if (matcher.find()) {
            System.out.println("PID VALID:"+pid);
            result = true;
        } else {
            System.out.println("PID invalid:"+pid);
        }
        return result;
    }

    private boolean checkYear(String bYear, int minYear, int maxYear) {
        boolean result = false;
        Pattern birthYearPattern = Pattern.compile("\\d{4}");
        Matcher matcher = birthYearPattern.matcher(bYear);
        if (matcher.find()) {
            int year = Integer.parseInt(matcher.group());
            result = (year >= minYear && year <= maxYear);
        }
        System.out.println(bYear + "|" +minYear + "|" + maxYear + "-->" +result);
        return result;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean checkData() {
        if (checked) {
            throw new RuntimeException("IllegalState");
        }
        checked = true;
        System.out.println("checking...");
        if (birthYear && issueYear && expirationYear && height && hairColor && eyeColor && passportId) {
            return true;
        } else {
            return false;
        }
    }


}
