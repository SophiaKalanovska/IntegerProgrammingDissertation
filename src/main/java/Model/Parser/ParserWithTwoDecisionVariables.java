package Model.Parser;

import Model.Parser.Exceptions.ExceptionNotATerm;
import Model.Parser.Exceptions.ExceptionNotZero;

class ParserWithTwoDecisionVariables {
    private final Parser parser;

    ParserWithTwoDecisionVariables(Parser parser) {
        this.parser = parser;
    }

    /**
     * this method checks if the user input
     * can be converted to the general form
     * and if so returns the inequality object
     */
    void parseInequality() throws ExceptionNotATerm, Exception {
        try {
            parser.term1 = parser.parseMember.parseTerm("first");
            try {
                parser.term2 = parser.parseMember.parseTerm("second");
                parser.parseMember.parseSign();
                try {
                    parser.parseMember.parseNull();
                    parser.hasZeroLeft = true;
                } catch (ExceptionNotZero exceptionNotNull) {
                    System.out.println("not zero");
                }
            } catch (ExceptionNotATerm e) {
                one_term_signexpected();
            }
        } catch (ExceptionNotATerm e) {
            System.out.println("First term is not valid");
            try {
                parser.parseMember.parseNull();
            } catch (ExceptionNotZero exceptionNotNull) {
                throw new ExceptionNotATerm("might be onlyOneVar");
            }
            parser.parseMember.parseSign();
            parser.term1 = parser.parseMember.parseTerm("first");
            parser.term2 = parser.parseMember.parseTerm("second");
            parser.hasZeroRight= true;
        }
    }

    private void one_term_signexpected() throws Exception, ExceptionNotATerm {
        parser.parseMember.parseSign();
        try {
            parser.term2 = parser.parseMember.parseTerm("second");
        } catch (ExceptionNotATerm exceptionNotATerm) {
            throw new ExceptionNotATerm("might be onlyOneVar");
        }

    }
}
