package Model.Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseMember {
    private final Parser parser;

    public ParseMember(Parser parser) {
        this.parser = parser;
    }

    private String tokenize(String regex) throws Exception {

        Pattern reg = Pattern.compile("^(" + regex + ")");
        String s;
        if (!parser.toParse.equals("")) {
            s = parser.toParse;
        } else {
            s = parser.src.trim();
        }
        while (!s.equals("")) {
            boolean match = false;
            Matcher m = reg.matcher(s);
            if (m.find()) {
                match = true;
                String tok = m.group().trim();
                if (!parser.toParse.equals("")) {
                    parser.toParse = m.replaceFirst("").trim();
                } else {
                    parser.src = m.replaceFirst("").trim();
                    parser.pos = parser.pos + tok.length();
                }

                return tok;
            }
            if (!match) throw new Exception("Unexpected character in input: " + s);
        }
        return null;
    }

    private void parse_weight(String expression, String order) throws Exceptions.NoWeightException {
        try {
            parser.toParse = expression;
            signOfExpression(order);
            String term = tokenize("[1-9]+[0-9]*|[0-9]+[\\.\\,][0-9]+");
            if (order.equals("first")) {
                parser.inequality.getFirstDecisionVariable().setWeight(Double.parseDouble(term));
                ;
            } else {
                parser.inequality.getSecondDecisionVariable().setWeight(Double.parseDouble(term));
                ;
            }
        } catch (Exception e) {
            throw new Exceptions.NoWeightException(" No weight");
        }
    }

    private void signOfExpression(String order) {
        String sign = null;
        try {
            sign = tokenize("[+|-]");
            if (order.equals("first")) {
                if (sign.equals("-")) {
                    parser.inequality.getFirstDecisionVariable().setSign("-");
                } else {
                    parser.inequality.getFirstDecisionVariable().setSign("+");
                }
            } else {
                if (sign.equals("-")) {
                    parser.inequality.getSecondDecisionVariable().setSign("-");
                } else {
                    parser.inequality.getSecondDecisionVariable().setSign("+");
                }
            }
        } catch (Exception e) {
        }


    }

    private void parse_UnknownVariable(String order) throws Exception {
        try {

            String term = tokenize("[a-zA-Z][a-zA-Z0-9_]*");
            parser.toParse = "";
            if (order.equals("first")) {
                parser.inequality.getFirstDecisionVariable().setVariable(term);
            } else {
                parser.inequality.getSecondDecisionVariable().setVariable(term);
            }
        } catch (Exception e) {
            throw new Exception("");

        }
    }

    String parse_term(String order) throws Exceptions.ExceptionNotATerm, Exception {
        String term;

        try {

            term = tokenize("[+|-]*[1-9]*[a-zA-Z][a-zA-Z0-9_]*|[+|-]*[1-9]+[0-9]*[a-zA-Z][a-zA-Z0-9_]*|[+|-]*[0-9]+[\\.\\,][0-9]+[a-zA-Z][a-zA-Z0-9_]*");

        } catch (Exception e) {
            throw new Exceptions.ExceptionNotATerm(" Expecting pattern term " + order);
        }

        if (term != null) {
            try {
                parse_weight(term, order);
                parse_UnknownVariable(order);
            } catch (Exceptions.NoWeightException e) {
                parse_UnknownVariable(order);
            }

        }
        return term;
    }

    void parse_sign() throws Exception {
        parser.sign = tokenize("<=|>=|<|>");
    }

    void parse_null() throws Exceptions.ExceptionNotZero {
        try {
            tokenize("0");
        } catch (Exception e) {
            throw new Exceptions.ExceptionNotZero("something else");
        }
    }

    public Double parse_number() throws Exceptions.ExceptionNotNumber {
        try {
            return Double.parseDouble(tokenize("[0-9]+[\\.\\,][0-9]+|[0-9]+"));
        } catch (Exception e) {
            throw new Exceptions.ExceptionNotNumber("not a number");
        }
    }

}