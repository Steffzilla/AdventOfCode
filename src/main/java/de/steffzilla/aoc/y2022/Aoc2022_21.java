package de.steffzilla.aoc.y2022;

import de.steffzilla.competitive.Utils;
import de.steffzilla.competitive.Pair;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2022_21 {

    private static final String DAY = "21";
    private static final String YEAR = "2022";
    private static final String USERNAME = System.getProperty("user.name");
    public static final String BASEDIR = "C://Users//" + USERNAME + "//Downloads//AoC" + YEAR + "//";
    public static final String FILENAME = "input" + YEAR + "_" + DAY + ".txt";
    //public static final String FILENAME = "sample" + YEAR + "_" + DAY + ".txt";
    public static final String PATH = BASEDIR + FILENAME;
    public static final String ROOT = "root";
    public static final String HUMAN = "humn";

    public static void main(String[] args) {
        System.out.println(DAY + ".12." + YEAR);
        List<String> inputLines = Utils.getStringList(PATH);

        solve(inputLines);
    }

    static Pair<String, String> solve(List<String> inputLines) {
        return new Pair<>(part1(inputLines), part2(inputLines));
    }

    private static HashMap<String, MonkeyTask> getMonkeyTasks(List<String> inputLines) {
        HashMap<String, MonkeyTask> monkeyTasks = new HashMap<>();
        Pattern pattern = Pattern.compile("^(\\w*) ([+\\-*/]) (\\w*)");
        for (String line : inputLines) {
            String[] split = line.split(": ");
            String monkeysName = split[0];
            String taskDescription = split[1];
            MonkeyTask monkeyTask;
            if (taskDescription.matches("^-?\\d+$")) {
                long monkeysNumber = Long.parseLong(taskDescription);
                //System.out.println(monkeysName + " -> " + monkeysNumber);
                monkeyTask =
                        new MonkeyTask(Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(monkeysNumber));
            } else {
                Matcher matcher = pattern.matcher(taskDescription);
                matcher.find();
                //System.out.println("|" + matcher.group(1) + "|" + matcher.group(2) + "|" + matcher.group(3));
                monkeyTask =
                        new MonkeyTask(Optional.of(matcher.group(1)), Optional.of(matcher.group(3)), Optional.of(matcher.group(2)), Optional.empty());
            }
            monkeyTasks.put(monkeysName, monkeyTask);
        }
        return monkeyTasks;
    }

    private static String part1(List<String> inputLines) {
        HashMap<String, MonkeyTask> monkeyTasks = getMonkeyTasks(inputLines);
        MonkeyTask root = processTree(monkeyTasks, ROOT, false);
        System.out.println("\nPart 1 > Result: " + root.number.get());
        return String.valueOf(root.number.get());
    }

    private static MonkeyTask processTree(HashMap<String, MonkeyTask> monkeyTasks, String rootName, boolean ignoreHumn) {
        MonkeyTask root = null;
        boolean hasChanged = true;
        while (hasChanged) {
            hasChanged = false;
            for (String monkeysName : monkeyTasks.keySet()) {
                if (ignoreHumn && monkeysName.equals(HUMAN)) {
                    continue;
                }
                MonkeyTask task = monkeyTasks.get(monkeysName);
                MonkeyTask updatedTask = processTask(monkeyTasks, task);
                if (!task.equals(updatedTask)) {
                    monkeyTasks.replace(monkeysName, updatedTask);
                    hasChanged = true;
                    if (rootName.equals(monkeysName)) {
                        root = updatedTask;
                    }
                }
            }
        }
        return root;
    }

    private static MonkeyTask processTask(HashMap<String, MonkeyTask> monkeyTasks, MonkeyTask task) {
        if (task.number.isEmpty()) {
            MonkeyTask monkey1Task = monkeyTasks.get(task.part1.orElseThrow(()
                    -> new IllegalStateException("Part 1 should not be empty: " + task)));
            MonkeyTask monkey2Task = monkeyTasks.get(task.part2.orElseThrow(()
                    -> new IllegalStateException("Part 2 should not be empty: " + task)));
            String operator = task.operator.orElseThrow(()
                    -> new IllegalStateException("Operator should not be empty: " + task));
            if (monkey1Task.number.isPresent() && monkey2Task.number.isPresent()) {
                Long value1 = monkey1Task.number.get();
                Long value2 = monkey2Task.number.get();
                Long result = switch (operator) {
                    case "+" -> value1 + value2;
                    case "-" -> value1 - value2;
                    case "*" -> value1 * value2;
                    case "/" -> value1 / value2;
                    default -> throw new IllegalStateException("Default should not occur! " + task);
                };
                return new MonkeyTask(Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(result));
            }
        }
        return task;
    }


    private static String part2(List<String> inputLines) {
        return "TODO";
    }


    private static HashSet<String> getMonkeysOfSubtree(String start, HashMap<String, MonkeyTask> monkeyTasks) {
        Deque<String> stack = new LinkedList<>();
        stack.push(start);
        HashSet<String> tasks = new HashSet<>();
        while (!stack.isEmpty()) {
            String next = stack.poll();
            tasks.add(next);
            MonkeyTask monkeyTask = monkeyTasks.get(next);
            if (monkeyTask.number.isEmpty()) {
                stack.push(monkeyTask.part1.orElseThrow(() -> new IllegalStateException("Part 1 should not be empty: " + monkeyTask)));
                stack.push(monkeyTask.part2.orElseThrow(() -> new IllegalStateException("Part 2 should not be empty: " + monkeyTask)));
            }
        }

        return tasks;
    }

    private record MonkeyTask(Optional<String> part1, Optional<String> part2, Optional<String> operator,
                              Optional<Long> number) {
    }

}