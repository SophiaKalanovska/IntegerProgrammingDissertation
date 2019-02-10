package model.Parser;

import model.Inequality;

public class ParserWithTwoDecisionVariables {

    private final ParseMember parseMember = new ParseMember(this);
    protected String src;
    protected String srcOriginal;
    protected int pos;
    protected Inequality inequality;
    protected String toParse;
    protected String term1;
    protected String term2;
    protected String sign;

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
        } catch (Exceptions.ExceptionNotATerm exceptionNotATerm) {
            throw new Exception(" Source does not match the grammar. ");
        }

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

    private void parse_inequality() throws Exceptions.ExceptionNotATerm, Exception {
        try {
            term1 = parseMember.parse_term("first");
            try {
                term2 = parseMember.parse_term("second");
                parseMember.parse_sign();
                try {
                    parseMember.parse_null();
                } catch (Exceptions.ExceptionNotZero exceptionNotNull) {
                    System.out.println("not zero");
                }
            } catch (Exceptions.ExceptionNotATerm e) {
                one_term_signexpected();
            }
        } catch (Exceptions.ExceptionNotATerm e) {
            System.out.println("First term is not valid");
            try {
                parseMember.parse_null();
            } catch (Exceptions.ExceptionNotZero exceptionNotNull) {
                System.out.println("the expression may have only one term, if not is not valid");
            }
            parseMember.parse_sign();
            term1 = parseMember.parse_term("first");
            term2 = parseMember.parse_term("second");
        }
    }

    private void one_term_signexpected() throws Exception, Exceptions.ExceptionNotATerm {
        parseMember.parse_sign();
        term2 = parseMember.parse_term("second");
    }

    public String getSign() {
        return sign;
    }


}
