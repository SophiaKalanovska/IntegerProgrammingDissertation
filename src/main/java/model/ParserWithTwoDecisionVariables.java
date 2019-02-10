package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserWithTwoDecisionVariables {

    private String src;
    private String srcOriginal;
    private int pos;
    private Inequality inequality;
    private String toParse;
    private String term1;
    private String term2;

    public ParserWithTwoDecisionVariables(String src) {
        this.srcOriginal = src.replaceAll("\\s+", "");
        this.toParse = "";
        this.src = src.replaceAll("\\s+", "");
        this.pos = 0;
        inequality = new Inequality();
    }


    public Inequality parse() throws Exception {
        try {
            parse_inequality();
        } catch (ExceptionNotATerm exceptionNotATerm) {
            throw new Exception(" Source does not match the grammar. ");
        }
        String sign = inequality.getSign();
        if (term1 == null || term2 == null || sign == null) {
            throw new Exception(" Something is null ");
        }
        if (pos == srcOriginal.length()) {
            inequality.setExpreission(srcOriginal);
            return inequality;
        } else {
            throw new Exception(" Source does not match the grammar. ");
        }
    }

    private String tokenize(String regex) throws Exception {

        Pattern reg = Pattern.compile("^(" + regex + ")");
        String s;
        if (!toParse.equals("")) {
            s = toParse;
        } else {
            s = src.trim();
        }
        while (!s.equals("")) {
            boolean match = false;
            Matcher m = reg.matcher(s);
            if (m.find()) {
                match = true;
                String tok = m.group().trim();
                if (!toParse.equals("")) {
                    toParse = m.replaceFirst("").trim();
                } else {
                    src = m.replaceFirst("").trim();
                    pos += tok.length();
                }
                System.out.println(tok);
                return tok;
            }
            if (!match) throw new Exception("Unexpected character in input: " + s);
        }
        return null;
    }


    private void parse_weight(String expression, String order) throws NoWeightException {

        try {

            toParse = expression;
            signOfExpression(order);
            String term = tokenize("[1-9]+");
            if (order.equals("first")) {
               inequality.getFirstDecisionVariable().setWeight(Integer.parseInt(term));;
            }else{
                inequality.getSecondDecisionVariable().setWeight(Integer.parseInt(term));;
            }
        } catch (Exception e) {
            throw new NoWeightException(" No weight");
        }
    }

    private void signOfExpression(String order) {


        String sign= null;

        try {
            sign = tokenize("[+|-]");
            if (order.equals("first")) {
                if (sign.equals("-")){
                    inequality.getFirstDecisionVariable().setSign("-");
                }else{
                    inequality.getFirstDecisionVariable().setSign("+");
                }
            }else{
                if (sign.equals("-")){
                    inequality.getSecondDecisionVariable().setSign("-");
                }else{
                    inequality.getSecondDecisionVariable().setSign("+");
                }
            }
        } catch (Exception e) {
            System.out.println("no sign");
        }


    }

    private void parse_UnknownVariable(String order) throws Exception {
        try {

            String term = tokenize("[a-zA-Z][a-zA-Z0-9_]*");
            toParse = "";
            if (order.equals("first")) {
                inequality.getFirstDecisionVariable().setVariable(term);
            }else{
                inequality.getSecondDecisionVariable().setVariable(term);
            }
        } catch (Exception e) {
            throw new Exception("");

        }
    }

    private String parse_term(String order) throws ExceptionNotATerm, Exception {
        String term;

        try {
            term = tokenize("[+|-]*[1-9]*[a-zA-Z][a-zA-Z0-9_]*");

        } catch (Exception e) {
            throw new ExceptionNotATerm(" Expecting pattern term " + order);
        }

        if (term != null) {
                try {
                    parse_weight(term, order);
                    parse_UnknownVariable(order);
                } catch (NoWeightException e) {
                    System.out.println("no weight");
                    parse_UnknownVariable(order);
                }

        }
        return term;
    }


    private void parse_inequality() throws ExceptionNotATerm, Exception {
        try {
            term1 = parse_term("first");
            try {
                term2 = parse_term("second");
                inequality.setSign(tokenize("<|>|<=|>="));
                try {
                    parse_null();
                } catch (ExceptionNotZero exceptionNotNull) {
                    System.out.println("not zero");
                }
            } catch (ExceptionNotATerm e) {
                one_term_signexpected();
            }
        } catch (ExceptionNotATerm e) {
            System.out.println("First term is not valid");
            try {
                parse_null();
            } catch (ExceptionNotZero exceptionNotNull) {
                System.out.println("the expression may have only one term, if not is not valid");
            }
            inequality.setSign(tokenize("<|>|<=|>="));
            term1 = parse_term("first");
            term2 = parse_term("second");
        }
    }

    private void one_term_signexpected() throws Exception, ExceptionNotATerm {
        inequality.setSign(tokenize("<|>|<=|>="));
        term2 = parse_term("second");
    }

    private void parse_null() throws ExceptionNotZero {
        try {
            tokenize("0");
        } catch (Exception e) {
            throw new ExceptionNotZero("something else");
        }
    }

    private class NoWeightException extends Throwable {
         NoWeightException(String s) {
        }
    }

    private class ExceptionNotZero extends Throwable {
         ExceptionNotZero(String something_else) {
        }
    }

    private class ExceptionNotATerm extends Throwable {
        ExceptionNotATerm(String s) {
        }
    }

}
