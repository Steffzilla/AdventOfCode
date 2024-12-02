package de.steffzilla.aoc.y2023;

import de.steffzilla.aoc.AocUtils;

import java.util.*;

public class Aoc2023_07 {

    private static final String DAY = "07";
    private static final String YEAR = "2023";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//"+USERNAME+"//Downloads//AoC"+YEAR+"//";
    public static final String FILENAME = "input"+YEAR+"_"+DAY+".txt";
    //public static final String FILENAME = "sample"+YEAR+"_"+DAY+".txt";
    public static final String PATH = BASEDIR + FILENAME;

    private static boolean part2;

    enum RESULT_TYPE {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIRS,
        THREE_OF_A_KIND,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        FIVE_OF_A_KIND
    }

    enum CARD_TYPE {
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE
    }

    enum CARD_TYPE_PART2 {
        JOKER,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        QUEEN,
        KING,
        ACE
    }

    static class CardHand implements Comparable<CardHand> {
        String adjustedHand;
        String orgHand;
        int bid;
        RESULT_TYPE resultType;

        public CardHand(String orgHand, String adjustedHand, int bid) {
            this.adjustedHand = adjustedHand;
            this.orgHand = orgHand;
            this.bid = bid;
            resultType = calculateType();
        }

        public CardHand(String hand, int bid) {
            this.adjustedHand = hand;
            this.orgHand = hand;
            this.bid = bid;
            resultType = calculateType();
        }

        @Override
        public String toString() {
            return "CardHand{" +
                    "orgHand='" + orgHand + '\'' +
                    ", adjustedHand='" + adjustedHand + '\'' +
                    ", bid=" + bid +
                    ", resultType=" + resultType +
                    '}';
        }

        private RESULT_TYPE calculateType() {
            HashMap<Character, Integer> map = new HashMap<>();
            List<Character> characters = adjustedHand.chars().mapToObj(e -> (char) e).toList();
            for (Character c : characters) {
                if(map.containsKey(c)) {
                    Integer i = map.get(c);
                    map.put(c, i+1);
                } else {
                    map.put(c, 1);
                }
            }
            if(map.size()==5) {
                return RESULT_TYPE.HIGH_CARD;
            } else if(map.size()==4) {
                return RESULT_TYPE.ONE_PAIR;
            } else if (map.size()==3) {
                ArrayList<Integer> numbers = new ArrayList<>(map.values());
                numbers.sort(Collections.reverseOrder());
                if (numbers.get(0)==3) {
                    return RESULT_TYPE.THREE_OF_A_KIND;
                } else return RESULT_TYPE.TWO_PAIRS;
            } else if (map.size()==2) {
                ArrayList<Integer> numbers = new ArrayList<>(map.values());
                numbers.sort(Collections.reverseOrder());
                if (numbers.get(0)==4) {
                    return RESULT_TYPE.FOUR_OF_A_KIND;
                } else return RESULT_TYPE.FULL_HOUSE;
            } else if (map.size()==1) {
                return RESULT_TYPE.FIVE_OF_A_KIND;
            } else return null;
        }

        @Override
        public int compareTo(CardHand o) {
            int result = this.resultType.compareTo(o.resultType);
            if(result==0) {
                for (int i = 0; i < this.orgHand.length(); i++) {
                    String thisLetter = this.orgHand.substring(i, i+1);
                    String otherLetter = o.orgHand.substring(i, i+1);
                    int innerResult;
                    if(part2) {
                        CARD_TYPE_PART2 thisCardType = getTypePart2(thisLetter);
                        CARD_TYPE_PART2 otherCardType = getTypePart2(otherLetter);
                        innerResult = thisCardType.compareTo(otherCardType);
                    } else {
                        CARD_TYPE thisCardType = getType(thisLetter);
                        CARD_TYPE otherCardType = getType(otherLetter);
                        innerResult = thisCardType.compareTo(otherCardType);
                    }

                    if(innerResult != 0) {
                        return innerResult;
                    }
                }
            }
            return result;
        }

        // FIXME in enum CARD_TYPE verlegen?
        private CARD_TYPE getType(String s) {
            return switch (s) {
                case "A" -> CARD_TYPE.ACE;
                case "K" -> CARD_TYPE.KING;
                case "Q" -> CARD_TYPE.QUEEN;
                case "J" -> CARD_TYPE.JACK;
                case "T" -> CARD_TYPE.TEN;
                case "9" -> CARD_TYPE.NINE;
                case "8" -> CARD_TYPE.EIGHT;
                case "7" -> CARD_TYPE.SEVEN;
                case "6" -> CARD_TYPE.SIX;
                case "5" -> CARD_TYPE.FIVE;
                case "4" -> CARD_TYPE.FOUR;
                case "3" -> CARD_TYPE.THREE;
                case "2" -> CARD_TYPE.TWO;
                default -> throw new IllegalStateException("Should not occur: "+s);
            };
        }

        private CARD_TYPE_PART2 getTypePart2(String s) {
            return switch (s) {
                case "A" -> CARD_TYPE_PART2.ACE;
                case "K" -> CARD_TYPE_PART2.KING;
                case "Q" -> CARD_TYPE_PART2.QUEEN;
                case "J" -> CARD_TYPE_PART2.JOKER;
                case "T" -> CARD_TYPE_PART2.TEN;
                case "9" -> CARD_TYPE_PART2.NINE;
                case "8" -> CARD_TYPE_PART2.EIGHT;
                case "7" -> CARD_TYPE_PART2.SEVEN;
                case "6" -> CARD_TYPE_PART2.SIX;
                case "5" -> CARD_TYPE_PART2.FIVE;
                case "4" -> CARD_TYPE_PART2.FOUR;
                case "3" -> CARD_TYPE_PART2.THREE;
                case "2" -> CARD_TYPE_PART2.TWO;
                default -> throw new IllegalStateException("Should not occur: "+s);
            };
        }
    }

    public static void main(String[] args) {
        System.out.println(DAY+".12."+YEAR);
        List<String> inputLines = AocUtils.getStringList(PATH);

        //part1(inputLines);
        part2(inputLines);
    }

    private static TreeSet<CardHand> readData(List<String> inputLines, boolean part2) {
        TreeSet<CardHand> list = new TreeSet<>();
        for (String line : inputLines) {
            String[] parts = line.split(" ");
            CardHand cardHand;
            if (part2) {
                cardHand = findBestHand(parts);
            } else {
                cardHand = new CardHand(parts[0], Integer.parseInt(parts[1]));
            }
            list.add(cardHand);
        }
        return list;
    }

    private static CardHand findBestHand(String[] parts) {
        TreeSet<CardHand> set = new TreeSet<>();
        String orgHand = parts[0];
        if(orgHand.contains("J")) {
            System.out.println(orgHand);
            set.add(new CardHand(orgHand, orgHand.replace('J','2'), Integer.parseInt(parts[1])));
            set.add(new CardHand(orgHand, orgHand.replace('J','3'), Integer.parseInt(parts[1])));
            set.add(new CardHand(orgHand, orgHand.replace('J','4'), Integer.parseInt(parts[1])));
            set.add(new CardHand(orgHand, orgHand.replace('J','5'), Integer.parseInt(parts[1])));
            set.add(new CardHand(orgHand, orgHand.replace('J','6'), Integer.parseInt(parts[1])));
            set.add(new CardHand(orgHand, orgHand.replace('J','7'), Integer.parseInt(parts[1])));
            set.add(new CardHand(orgHand, orgHand.replace('J','8'), Integer.parseInt(parts[1])));
            set.add(new CardHand(orgHand, orgHand.replace('J','9'), Integer.parseInt(parts[1])));
            set.add(new CardHand(orgHand, orgHand.replace('J','T'), Integer.parseInt(parts[1])));
            set.add(new CardHand(orgHand, orgHand.replace('J','Q'), Integer.parseInt(parts[1])));
            set.add(new CardHand(orgHand, orgHand.replace('J','K'), Integer.parseInt(parts[1])));
            set.add(new CardHand(orgHand, orgHand.replace('J','A'), Integer.parseInt(parts[1])));
            System.out.println(set.last().adjustedHand);
            return set.last();
        } else {
            return new CardHand(orgHand, Integer.parseInt(parts[1]));
        }
    }

    private static void part1(List<String> inputLines){
        part2 = false;
        TreeSet<CardHand> cardHands = readData(inputLines, false);
        int rank = 1;
        long result = 0L;
        for (CardHand hand : cardHands) {
            result+= (long) hand.bid * rank;
            //System.out.println(result+"|"+hand.adjustedHand +"|"+hand.bid+"|"+rank+"|"+(hand.bid*rank));
            rank++;
        }
        System.out.println("\nPart 1 > Result: " + result);
    }

    private static void part2(List<String> inputLines) {
        part2 = true;
        TreeSet<CardHand> cardHands = readData(inputLines, true);
        int rank = 1;
        long result = 0L;
        for (CardHand hand : cardHands) {
            result+= (long) hand.bid * rank;
            System.out.println(result+"|"+hand.orgHand+"|"+hand.adjustedHand +"|"+hand.bid+"|"+rank+"|"+(hand.bid*rank));
            rank++;
        }
        System.out.println("\nPart 2 > Result: " + result);
    }

}