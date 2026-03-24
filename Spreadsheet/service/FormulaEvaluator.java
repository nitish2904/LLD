package Spreadsheet.service;

import Spreadsheet.model.Cell;
import Spreadsheet.model.CellType;
import Spreadsheet.model.Sheet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Evaluates formulas like =A1+B2, =SUM(A1:A5), =A1*2
 */
public class FormulaEvaluator {

    private static final Pattern CELL_REF = Pattern.compile("[A-Z]\\d+");
    private static final Pattern SUM_PATTERN = Pattern.compile("SUM\\(([A-Z]\\d+):([A-Z]\\d+)\\)");
    private static final Pattern AVG_PATTERN = Pattern.compile("AVG\\(([A-Z]\\d+):([A-Z]\\d+)\\)");
    private static final Pattern MIN_PATTERN = Pattern.compile("MIN\\(([A-Z]\\d+):([A-Z]\\d+)\\)");
    private static final Pattern MAX_PATTERN = Pattern.compile("MAX\\(([A-Z]\\d+):([A-Z]\\d+)\\)");

    public Object evaluate(String formula, Sheet sheet) {
        formula = formula.substring(1).trim().toUpperCase(); // remove '='

        // SUM(A1:A5)
        Matcher sumMatcher = SUM_PATTERN.matcher(formula);
        if (sumMatcher.matches()) {
            return aggregateRange(sheet, sumMatcher.group(1), sumMatcher.group(2), "SUM");
        }

        // AVG(A1:A5)
        Matcher avgMatcher = AVG_PATTERN.matcher(formula);
        if (avgMatcher.matches()) {
            return aggregateRange(sheet, avgMatcher.group(1), avgMatcher.group(2), "AVG");
        }

        // MIN(A1:A5)
        Matcher minMatcher = MIN_PATTERN.matcher(formula);
        if (minMatcher.matches()) {
            return aggregateRange(sheet, minMatcher.group(1), minMatcher.group(2), "MIN");
        }

        // MAX(A1:A5)
        Matcher maxMatcher = MAX_PATTERN.matcher(formula);
        if (maxMatcher.matches()) {
            return aggregateRange(sheet, maxMatcher.group(1), maxMatcher.group(2), "MAX");
        }

        // Simple expression: replace cell refs with values, then evaluate
        String expression = replaceCellRefs(formula, sheet);
        try {
            return evalSimpleExpression(expression);
        } catch (Exception e) {
            return "#ERROR";
        }
    }

    private String replaceCellRefs(String formula, Sheet sheet) {
        Matcher matcher = CELL_REF.matcher(formula);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            Cell cell = sheet.getCell(matcher.group());
            double val = getCellNumericValue(cell);
            matcher.appendReplacement(result, String.valueOf(val));
        }
        matcher.appendTail(result);
        return result.toString();
    }

    private double getCellNumericValue(Cell cell) {
        if (cell == null) return 0;
        Object val = cell.getComputedValue();
        if (val instanceof Number n) return n.doubleValue();
        try { return Double.parseDouble(String.valueOf(val)); } catch (Exception e) { return 0; }
    }

    private double aggregateRange(Sheet sheet, String startRef, String endRef, String func) {
        Cell start = sheet.getCell(startRef);
        Cell end = sheet.getCell(endRef);
        if (start == null || end == null) return 0;

        int r1 = Math.min(start.getRow(), end.getRow());
        int r2 = Math.max(start.getRow(), end.getRow());
        int c1 = Math.min(start.getCol(), end.getCol());
        int c2 = Math.max(start.getCol(), end.getCol());

        double sum = 0, min = Double.MAX_VALUE, max = Double.MIN_VALUE;
        int count = 0;

        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                Cell cell = sheet.getCell(r, c);
                if (cell != null && cell.getType() != CellType.EMPTY) {
                    double val = getCellNumericValue(cell);
                    sum += val;
                    min = Math.min(min, val);
                    max = Math.max(max, val);
                    count++;
                }
            }
        }

        return switch (func) {
            case "SUM" -> sum;
            case "AVG" -> count > 0 ? sum / count : 0;
            case "MIN" -> min == Double.MAX_VALUE ? 0 : min;
            case "MAX" -> max == Double.MIN_VALUE ? 0 : max;
            default -> 0;
        };
    }

    /**
     * Evaluate simple arithmetic: supports +, -, *, / (left to right, no precedence).
     */
    private double evalSimpleExpression(String expr) {
        expr = expr.replaceAll("\\s+", "");
        // Split on operators, keeping them
        String[] tokens = expr.split("(?=[+\\-*/])|(?<=[+\\-*/])");
        double result = Double.parseDouble(tokens[0]);
        for (int i = 1; i < tokens.length - 1; i += 2) {
            String op = tokens[i];
            double next = Double.parseDouble(tokens[i + 1]);
            result = switch (op) {
                case "+" -> result + next;
                case "-" -> result - next;
                case "*" -> result * next;
                case "/" -> next != 0 ? result / next : 0;
                default -> result;
            };
        }
        return result;
    }
}
