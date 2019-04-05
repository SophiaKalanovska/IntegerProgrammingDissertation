package Model.Parser;

import Model.Inequalities.DecisionVariable;
import Model.Inequalities.Inequality;
import Model.Parser.Exceptions.ExceptionNotATerm;

public class Parser {
    protected final ParseMember parseMember = new ParseMember(this);
    private final ParserWithOneDecisionVariable parserWithOneDecisionVariable = new ParserWithOneDecisionVariable(this);
    private final ParserWithTwoDecisionVariables parserWithTwoDecisionVariables = new ParserWithTwoDecisionVariables(this);
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

    public void setString(String toParse){
        term1 = null;
        term2 = null;
        hasZeroLeft = false;
        hasZeroRight = false;
        this.pos = 0;
        inequality = new Inequality();
        this.toParse = "";
        this.src = toParse.replaceAll("\\s+", "");
        this.srcOriginal = toParse.replaceAll("\\s+", "");
    }

    public Inequality parse() throws Exception {
        try {
            parserWithTwoDecisionVariables.parse_inequality();
            if (term1 == null || term2 == null || sign == null) {
                throw new Exception("Something is null");
            }
            if(hasZeroRight){
                inequality.getSecondDecisionVariable().changeSignVariable();
            }

            if(hasZeroLeft){
                inequality.getFirstDecisionVariable().changeSignVariable();
            }

            if (sign.equals(">") || sign.equals(">=")){
                DecisionVariable first = inequality.getFirstDecisionVariable();
                inequality.setFirstDecisionVariable(inequality.getSecondDecisionVariable());
                inequality.setSecondDecisionVariable(first);
            }

            inequality.getFirstDecisionVariable().setWeight(inequality.getFirstDecisionVariable().getWeight() / inequality.getSecondDecisionVariable().getWeight());

            if (pos == srcOriginal.length()) {
                inequality.setExpression(srcOriginal);
                return inequality;
            }
        } catch (ExceptionNotATerm exceptionNotATerm) {
            try {
                parserWithOneDecisionVariable.clear();
                parserWithOneDecisionVariable.parse_inequality();

                inequality.setExpression(srcOriginal);
                return inequality;
            } catch (ExceptionNotATerm exceptionNotATerm1) {
                throw new Exception(" Source does not match the grammar. ");
            }
        }
        return null;
    }
}
