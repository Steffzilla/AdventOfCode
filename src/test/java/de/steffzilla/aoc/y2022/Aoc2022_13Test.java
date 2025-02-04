package de.steffzilla.aoc.y2022;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Aoc2022_13Test {

    @Test
    public void testReadList() {
        List list = new ArrayList();
        Aoc2022_13.readList(list, "1,1,3,1,1");
        assertEquals("[1,1,3,1,1]", list.toString().replaceAll("\\s+",""));

        list = new ArrayList();
        Aoc2022_13.readList(list, "[[]]");
        assertEquals("[[[]]]", list.toString().replaceAll("\\s+",""));

        list = new ArrayList();
        Aoc2022_13.readList(list, "1,[2,[3,[4,[5,6,7]]]],8,9");
        assertEquals("[1,[2,[3,[4,[5,6,7]]]],8,9]", list.toString().replaceAll("\\s+",""));

        list = new ArrayList();
        Aoc2022_13.readList(list, "10,[]");
        assertEquals("[10,[]]", list.toString().replaceAll("\\s+",""));

        list = new ArrayList();
        Aoc2022_13.readList(list, "[[5,1,[],[8,1,3],6],[[7]]],[10,[]],[6],[[],[[0,6,4,10,5],[2,2,9],[4,4],[2,10,4,10,8]],7,7],[[5],5,[[6],[8,2],[5],[]],[9,3,2,[9,4,8,6,8]]]");
        assertEquals("[[[5,1,[],[8,1,3],6],[[7]]],[10,[]],[6],[[],[[0,6,4,10,5],[2,2,9],[4,4],[2,10,4,10,8]],7,7],[[5],5,[[6],[8,2],[5],[]],[9,3,2,[9,4,8,6,8]]]]", list.toString().replaceAll("\\s+",""));
    }

    @Test
    public void testCheckRightOrder() {
        assertEquals(Aoc2022_13.State.CORRECT, Aoc2022_13.checkRightOrder(Arrays.asList(1), Arrays.asList(2)));
        assertEquals(Aoc2022_13.State.INCORRECT, Aoc2022_13.checkRightOrder(Arrays.asList(2), Arrays.asList(1)));
        assertEquals(Aoc2022_13.State.UNKNOWN, Aoc2022_13.checkRightOrder(Arrays.asList(1), Arrays.asList(1)));
        assertEquals(Aoc2022_13.State.UNKNOWN, Aoc2022_13.checkRightOrder(Arrays.asList(1,1,3,1,1), Arrays.asList(1,1,3,1,1)));

        assertEquals(Aoc2022_13.State.UNKNOWN, Aoc2022_13.checkRightOrder(
                Arrays.asList(Arrays.asList(1,1,3,1,1),5),
                        Arrays.asList(Arrays.asList(1,1,3,1,1),5)));
        assertEquals(Aoc2022_13.State.UNKNOWN, Aoc2022_13.checkRightOrder(
                Arrays.asList(Arrays.asList(1,1,3,1,1), 5),
                Arrays.asList(Arrays.asList(1,1,3,1,1), Arrays.asList(5))));

        assertEquals(Aoc2022_13.State.CORRECT, Aoc2022_13.checkRightOrder(Arrays.asList(1,1,3,1,1), Arrays.asList(1,1,5,1,1)));
        assertEquals(Aoc2022_13.State.CORRECT, Aoc2022_13.checkRightOrder(Arrays.asList(1,1,3,1,1), Arrays.asList(1,1,5)));
        assertEquals(Aoc2022_13.State.CORRECT, Aoc2022_13.checkRightOrder(Collections.EMPTY_LIST, Arrays.asList(1,1,5)));
        assertEquals(Aoc2022_13.State.INCORRECT, Aoc2022_13.checkRightOrder(Arrays.asList(1,1,5,1,1), Arrays.asList(1,1,3,1,1)));
        assertEquals(Aoc2022_13.State.INCORRECT, Aoc2022_13.checkRightOrder(Arrays.asList(1,1,5), Arrays.asList(1,1,3,1,1)));
        assertEquals(Aoc2022_13.State.INCORRECT, Aoc2022_13.checkRightOrder(Arrays.asList(7,7,7,7), Arrays.asList(7,7,7)));
        assertEquals(Aoc2022_13.State.CORRECT, Aoc2022_13.checkRightOrder(Arrays.asList(7,7,7), Arrays.asList(7,7,7,7)));

        assertEquals(Aoc2022_13.State.INCORRECT, Aoc2022_13.checkRightOrder(Arrays.asList(1,1,5), Collections.EMPTY_LIST));
        assertEquals(Aoc2022_13.State.INCORRECT, Aoc2022_13.checkRightOrder(Arrays.asList(9), Arrays.asList(Arrays.asList(1,1,5))));

        assertEquals(Aoc2022_13.State.CORRECT, Aoc2022_13.checkRightOrder(
                Arrays.asList(Arrays.asList(0,0,0)),
                Arrays.asList(2)));
        assertEquals(Aoc2022_13.State.CORRECT, Aoc2022_13.checkRightOrder(
                Arrays.asList(Arrays.asList(1), Arrays.asList(2,3,4)),
                Arrays.asList(Arrays.asList(1), 4)));
        assertEquals(Aoc2022_13.State.INCORRECT, Aoc2022_13.checkRightOrder(Arrays.asList(9), Arrays.asList(Arrays.asList(8,7,6))));

        assertEquals(Aoc2022_13.State.INCORRECT, Aoc2022_13.checkRightOrder(
                Arrays.asList(Arrays.asList(Arrays.asList())),
                Arrays.asList(Arrays.asList())));

        assertEquals(Aoc2022_13.State.CORRECT, Aoc2022_13.checkRightOrder(
                Arrays.asList(Arrays.asList()),
                Arrays.asList(Arrays.asList(Arrays.asList()))));

        assertEquals(Aoc2022_13.State.INCORRECT, Aoc2022_13.checkRightOrder(
                Arrays.asList(Arrays.asList(3)),
                Arrays.asList(Arrays.asList(Arrays.asList()))));

        assertEquals(Aoc2022_13.State.UNKNOWN, Aoc2022_13.checkRightOrder(
                Arrays.asList(Arrays.asList(3)),
                Arrays.asList(Arrays.asList(Arrays.asList(3)))));

        assertEquals(Aoc2022_13.State.CORRECT, Aoc2022_13.checkRightOrder(
                Arrays.asList(Arrays.asList(4), 3),
                Arrays.asList(Arrays.asList(5), 2)));

        assertEquals(Aoc2022_13.State.CORRECT, Aoc2022_13.checkRightOrder(
                Arrays.asList(Arrays.asList(Arrays.asList(1), 1)),
                Arrays.asList(Arrays.asList(1,1,1))));
        /*
        ([[1],[2,3,4]],[[1],4]),'smaller')
([1,1,1],[1, 1]),'bigger')
([[1],1],[1,1,1]),'smaller')
([1,1],[1,1,1]),'smaller')
([9],[8, 7, 6]),'bigger')
([],[[]]),'smaller')
([[[]]],[[]]),'bigger')
([3],[[]]),'bigger')
([[[3]]],[[3]]),'equal')
([1,1,1],[1,1,1]),'equal')
        * */
    }

}
