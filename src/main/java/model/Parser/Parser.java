package model.Parser;

import model.Inequality;

public class Parser {

    protected final ParseMember parseMember = new ParseMember(this);
    protected final ParserWithOneDecisionVariable parserWithOneDecisionVariable = new ParserWithOneDecisionVariable(this);
    protected final ParserWithTwoDecisionVariables parserWithTwoDecisionVariables = new ParserWithTwoDecisionVariables(this);
    protected String src;
    protected String srcOriginal;
    protected int pos;
    protected Inequality inequality;
    protected String toParse;
    protected String term1;
    protected String term2;
    protected String sign;

    public Parser(String src) {
        this.pos = 0;
        inequality = new Inequality();
        this.toParse = "";
        this.src = src.replaceAll("\\s+", "");
        this.srcOriginal = src.replaceAll("\\s+", "");
    }

    public Inequality parse() throws Exception {
        try {
            parserWithTwoDecisionVariables.parse_inequality();
            if (term1 == null || term2 == null || sign == null) {
                throw new Exception(" Something is null ");
            }
            if (pos == srcOriginal.length()) {
                inequality.setExpreission(srcOriginal);
                return inequality;
            }
        } catch (Exceptions.ExceptionNotATerm exceptionNotATerm) {
            try {
                parserWithOneDecisionVariable.parse_inequality();
            } catch (Exceptions.ExceptionNotATerm exceptionNotATerm1) {
                throw new Exception(" Source does not match the grammar. ");
            }
        }
        return null;
    }
}
