package Model.Parser;

import Model.Parser.Exceptions.ExceptionNotATerm;
import Model.Parser.Exceptions.ExceptionNotNumber;
import Model.Parser.Exceptions.ExceptionNotZero;
import Model.Parser.Exceptions.NoWeightException;

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

    private void parseWeight(String expression, String order) throws NoWeightException {
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
            throw new NoWeightException(" No weight");
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

    private void parseUnknownVariable(String order) throws Exception {
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

    String parseTerm(String order) throws ExceptionNotATerm, Exception {
        String term;
        try {
            term = tokenize("[+|-]*[1-9]*[a-zA-Z][a-zA-Z0-9_]*|[+|-]*[1-9]+[0-9]*[a-zA-Z][a-zA-Z0-9_]*|[+|-]*[0-9]+[\\.\\,][0-9]+[a-zA-Z][a-zA-Z0-9_]*");
        } catch (Exception e) {
            throw new ExceptionNotATerm(" Expecting pattern term " + order);
        }
        if (term != null) {
            try {
                parseWeight(term, order);
                parseUnknownVariable(order);
            } catch (NoWeightException e) {
                parseUnknownVariable(order);
            }
        }
        return term;
    }

    void parseSign() throws Exception {
        parser.sign = tokenize("<=|>=|<|>");
    }

    void parseNull() throws ExceptionNotZero {
        try {
            tokenize("0");
        } catch (Exception e) {
            throw new ExceptionNotZero("something else");
        }
    }

    public Double parseNumber() throws ExceptionNotNumber {
        try {
            return Double.parseDouble(tokenize("[0-9]+[\\.\\,][0-9]+|[0-9]+"));
        } catch (Exception e) {
            throw new ExceptionNotNumber("not a number");
        }
    }

}