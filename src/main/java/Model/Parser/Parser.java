package Model.Parser;

import Model.Inequalities.DecisionVariable;
import Model.Inequalities.Inequality;
import Model.Parser.Exceptions.ExceptionNotATerm;

public class Parser {
    final ParseMember parseMember = new ParseMember(this);
    private final ParserWithOneDecisionVariable parserWithOneDecisionVariable = new ParserWithOneDecisionVariable(this);
    private final ParserWithTwoDecisionVariables parserWithTwoDecisionVariables = new ParserWithTwoDecisionVariables(this);
    String src;
    String srcOriginal;
    int pos;
    boolean hasZeroLeft;
    boolean hasZeroRight;
    protected Inequality inequality;
    String toParse;
    String term1;
    String term2;
    String sign;


    /**
     * this method sets the string that is about to be parsed
     * and resets the parsing pointers to 0;
     */
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

    /**
     * first tries to parse an inequality with two decision variables by
     * calling parseInequality() method in ParserWithTwoDecisionVariables.
     * If theparseInequality()method completes without throwing anexceptionNotATermException,
     * the inequality object must be translated into k/n x <= y
     * In this case where ParserWithTwoDecisionVariables is unsuccessful
     * the method tries to runparseInequality()method in theParserWithOneDecisionVariableclass.
     */
     public Inequality parse() throws Exception {
        try {
            parserWithTwoDecisionVariables.parseInequality();
            if (term1 == null || term2 == null || sign == null) {
                throw new Exception("Something is null");
            }
            if(hasZeroRight){
                inequality.getRightDecisionVariable().changeSignVariable();
            }

            if(hasZeroLeft){
                inequality.getLeftDecisionVariable().changeSignVariable();
            }

            if (sign.equals(">") || sign.equals(">=")){
                DecisionVariable first = inequality.getLeftDecisionVariable();
                inequality.setLeftDecisionVariable(inequality.getRightDecisionVariable());
                inequality.setRightDecisionVariable(first);
            }
            if ( inequality.getRightDecisionVariable().getWeight() <= 0) {
                inequality.getLeftDecisionVariable().setUpperBound(0);
            }
                inequality.getLeftDecisionVariable().setWeight(inequality.getLeftDecisionVariable().getWeight() / inequality.getRightDecisionVariable().getWeight());
            if (sign.equals(">") || sign.equals("<")){
                throw new Exception(" The inequality sign must be <= or >= . Otherwise the inequality is not in the form.");
            }
            if (pos == srcOriginal.length()) {
                inequality.setExpression(srcOriginal);
                return inequality;
            }
        } catch (ExceptionNotATerm exceptionNotATerm) {
            try {
                parserWithOneDecisionVariable.clear();
                parserWithOneDecisionVariable.parseInequality();

                inequality.setExpression(srcOriginal);
                return inequality;
            } catch (ExceptionNotATerm exceptionNotATerm1) {
                throw new Exception(" Source does not match the grammar. ");
            }
        }
        return null;
    }
}
