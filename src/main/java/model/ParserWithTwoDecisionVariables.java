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
        this.pos = 0 ;
    }


    public Inequality parse() throws Exception {
        inequality = new Inequality();
        parse_OneSide();

        if (pos == srcOriginal.length()) {
            inequality.setExpreission(srcOriginal);
            return inequality;
        } else {
            throw new Exception(" Source does not match the grammar . ");
        }
    }

    public String tokenize(String regex) throws Exception {

        Pattern reg = Pattern.compile("^(" + regex + ")");
        String s = "";
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


    public int parse_weight(String expression) throws NoWeightException {
        try {

            toParse = expression;
            String term = tokenize("[+|-]*[1-9]+");
            return Integer.parseInt(term);
        } catch (Exception e) {
            toParse = "";
            throw new NoWeightException(" No weight");
        }
    }

    public String parse_UnknownVariable() throws Exception {
        try {

            String term = tokenize("[a-zA-Z][a-zA-Z0-9_]*");
            toParse = "";
            return term;
        } catch (Exception e) {
            throw new Exception("");

        }
    }

    public String parse_term(String order) throws ExceptionNotATerm {
        String term = null;
        try {
            term = tokenize("[+|-]*[1-9]*[a-zA-Z][a-zA-Z0-9_]*");
        } catch (Exception e) {
            throw new ExceptionNotATerm(" Expecting pattern term ");
        }

        if (term != null) {
            if (order.equals("first")) {
                try {
                    inequality.setFirstdWeight(parse_weight(term));
                    try {
                        inequality.setFirstUnknownVariable(parse_UnknownVariable());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (NoWeightException e) {
                    System.out.println("no weight");
                    inequality.setFirstUnknownVariable(term);
                }
            } else {
                try {
                    inequality.setSecondWeight(parse_weight(term));
                    try {
                        inequality.setSecondUnknownVariable(parse_UnknownVariable());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (NoWeightException e) {
                    System.out.println("no weight");
                    inequality.setSecondUnknownVariable(term);
                }
            }
        }
        return term;

    }


    private void parse_OneSide() {

        try {
            term1 = parse_term("first");
            System.out.println("successfully parsed first term");
            try {
                term2 = parse_term("second");
                try {
                    inequality.setSign(tokenize("<|>|<=|>="));
                } catch (Exception e) {
                    System.out.println("not valid");
                }
                System.out.println("successfully parsed sign");
                try {
                    parse_maybeNull();
                } catch (ExceptionNotNull exceptionNotNull) {
                    System.out.println("not null");
                }

            } catch (ExceptionNotATerm e) {
                try {
                    inequality.setSign(tokenize("<|>|<=|>="));
                } catch (Exception e1) {
                    System.out.println("invalid");
                }
                System.out.println("successfully parsed sign");
                try {
                    term2 = parse_term("second");
                } catch (ExceptionNotATerm e1) {
                    System.out.println("invalid");
                }
            }
            System.out.println("successfully parsed second term");

        } catch (ExceptionNotATerm e) {
            System.out.println("First term is not valid");
            try {
                parse_maybeNull();
            } catch (ExceptionNotNull exceptionNotNull) {
                System.out.println("the expression may have only one term, if not is not valid");
            }
            System.out.println("successfully parsed null");
            try {
                inequality.setSign(tokenize("<|>|<=|>="));
            } catch (Exception e1) {
                System.out.println("invalid");
            }
            System.out.println("successfully parsed sign");
//            parse_OneSide();

        }


    }

    private int parse_maybeNull() throws ExceptionNotNull {
        try {

            String term = tokenize("0");
            return Integer.parseInt(term);
        } catch (Exception e) {
            throw new ExceptionNotNull("something else");
        }
    }


    private class NoWeightException extends Throwable {
        public NoWeightException(String s) {
        }
    }

    private class ExceptionNotNull extends Throwable {
        public ExceptionNotNull(String something_else) {
        }
    }

    private class ExceptionNotATerm extends Throwable {
        public ExceptionNotATerm(String s) {
        }
    }
}
