package de.steffzilla.aoc.y2020;

import de.steffzilla.aoc.AocUtils;

import java.util.HashMap;
import java.util.List;

public class Aoc2020_5 {

    private static final int NUMBER_OF_ROW_CHARS = 7;
    private static final int NUMBER_OF_COL_CHARS = 3;

    private static final String DAY = "05";
    private static final String YEAR = "2020";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static String PATH = BASEDIR + FILENAME;

    public static void main(String[] args) {
        int maxSeatNumber = 0;
        List<String> seatCodes = AocUtils.getStringList(PATH);
        //getSeatNumber("FBFBBFFRLR");
        HashMap<Integer,String> occupiedSeats = new HashMap<>();
        for (String seatCode: seatCodes) {
            int seatNumber = getSeatNumber(seatCode);
            occupiedSeats.put(seatNumber, seatCode);
            if(seatNumber > maxSeatNumber) {
                maxSeatNumber = seatNumber;
            }
        }
        // Part II
        for (int seatNo = 2; seatNo < maxSeatNumber; seatNo++) {
            String code1 = occupiedSeats.get(seatNo-1);
            String code2 = occupiedSeats.get(seatNo);
            String code3 = occupiedSeats.get(seatNo+1);
            if (code1 != null && code2 == null && code3!= null) {
                System.out.println("----------> seatNo:"+seatNo);
            }
        }
        
        System.out.println("\nmax seat no:"+ maxSeatNumber);
    }

    private static int getSeatNumber(String seatCode) {

        int row = getRowOrCol(seatCode, 0, 0 , (int) Math.pow(2, NUMBER_OF_ROW_CHARS) - 1, 'F', 'B');
        int column = getRowOrCol(seatCode, NUMBER_OF_ROW_CHARS, 0 , (int) Math.pow(2, NUMBER_OF_COL_CHARS) - 1, 'L', 'R');
        System.out.println("----->COL:"+column);
        int seatNumber = row * 8 + column;
        System.out.println("SeatCode: "+seatCode+" | seatNumber: "+seatNumber);
        return seatNumber;
    }

    private static int getRowOrCol(String seatCode, int pos, int minRow, int maxRow, char lower, char higher) {
        if (lower == 'L') System.out.println(pos+"|"+minRow+"|"+maxRow);
        if ((lower == 'F' && (pos == NUMBER_OF_ROW_CHARS)) ^ (lower == 'L' && (pos == NUMBER_OF_ROW_CHARS + NUMBER_OF_COL_CHARS))) {
            System.out.println(seatCode+"|"+pos+"|"+minRow+"|"+maxRow);
            return minRow;
        } else {
            char c = seatCode.charAt(pos);
            System.out.println(pos+":"+c);
            int x = (maxRow + 1 - minRow) / 2;
            if (c == lower) {
                return getRowOrCol(seatCode, ++pos, minRow, maxRow - x, lower, higher);
            }
            else if (c== higher){
                return getRowOrCol(seatCode, ++pos, minRow + x, maxRow, lower, higher);
            } else {
                throw new IllegalStateException(seatCode+"|"+pos+"|"+c);
            }

        }
    }

}
