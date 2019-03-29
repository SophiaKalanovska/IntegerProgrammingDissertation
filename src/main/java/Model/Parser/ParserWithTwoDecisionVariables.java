package Model.Parser;

import Model.Parser.Exceptions.ExceptionNotATerm;
import Model.Parser.Exceptions.ExceptionNotZero;

public class ParserWithTwoDecisionVariables {
    private final Parser parser;

    public ParserWithTwoDecisionVariables(Parser parser) {
        this.parser = parser;
    }

    public void parse_inequality() throws ExceptionNotATerm, Exception {
        try {
            parser.term1 = parser.parseMember.parse_term("first");
            try {
                parser.term2 = parser.parseMember.parse_term("second");
                parser.parseMember.parse_sign();
                try {
                    parser.parseMember.parse_null();
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
                parser.parseMember.parse_null();
            } catch (ExceptionNotZero exceptionNotNull) {
                throw new ExceptionNotATerm("might be onlyOneVar");
            }
            parser.parseMember.parse_sign();
            parser.term1 = parser.parseMember.parse_term("first");
            parser.term2 = parser.parseMember.parse_term("second");
            parser.hasZeroRight= true;
        }
    }

    private void one_term_signexpected() throws Exception, ExceptionNotATerm {
        parser.parseMember.parse_sign();
        try {
            parser.term2 = parser.parseMember.parse_term("second");
        } catch (ExceptionNotATerm exceptionNotATerm) {
            throw new ExceptionNotATerm("might be onlyOneVar");
        }

    }
}
