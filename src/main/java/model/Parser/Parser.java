package model.Parser;

import model.Inequalities.Inequality;

public class Parser {

    protected final ParseMember parseMember = new ParseMember(this);
    protected final ParserWithOneDecisionVariable parserWithOneDecisionVariable = new ParserWithOneDecisionVariable(this);
    protected final ParserWithTwoDecisionVariables parserWithTwoDecisionVariables = new ParserWithTwoDecisionVariables(this);
    protected String src;
    protected String srcOriginal;
    protected int pos;
    protected boolean hasZeroLeft;
    protected boolean hasZeroRight;
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
            if(hasZeroRight){
                inequality.getSecondDecisionVariable().changeSignVariable();
            }
            if(hasZeroLeft){
                inequality.getFirstDecisionVariable().changeSignVariable();
            }
            if (sign.equals(">") || sign.equals(">=")){
                inequality.getFirstDecisionVariable().changeSignVariable();
                inequality.getSecondDecisionVariable().changeSignVariable();
            }
            if (pos == srcOriginal.length()) {
                inequality.setExpression(srcOriginal);
                return inequality;
            }
        } catch (Exceptions.ExceptionNotATerm exceptionNotATerm) {
            try {
                parserWithOneDecisionVariable.clear();
                parserWithOneDecisionVariable.parse_inequality();
                inequality.setExpression(srcOriginal);
                return inequality;
            } catch (Exceptions.ExceptionNotATerm exceptionNotATerm1) {
                throw new Exception(" Source does not match the grammar. ");
            }
        }
        return null;
    }
}
