package Model.Parser;

public class ParserWithTwoDecisionVariables {
    private final Parser parser;

    public ParserWithTwoDecisionVariables(Parser parser) {
        this.parser = parser;
    }

    public void parse_inequality() throws Exceptions.ExceptionNotATerm, Exception {
        try {
            parser.term1 = parser.parseMember.parse_term("first");
            try {
                parser.term2 = parser.parseMember.parse_term("second");
                parser.parseMember.parse_sign();
                try {
                    parser.parseMember.parse_null();
                    parser.hasZeroLeft = true;
                } catch (Exceptions.ExceptionNotZero exceptionNotNull) {
                    System.out.println("not zero");
                }
            } catch (Exceptions.ExceptionNotATerm e) {
                one_term_signexpected();
            }
        } catch (Exceptions.ExceptionNotATerm e) {
            System.out.println("First term is not valid");
            try {
                parser.parseMember.parse_null();
            } catch (Exceptions.ExceptionNotZero exceptionNotNull) {
                throw new Exceptions.ExceptionNotATerm("might be onlyOneVar");
            }
            parser.parseMember.parse_sign();
            parser.term1 = parser.parseMember.parse_term("first");
            parser.term2 = parser.parseMember.parse_term("second");
            parser.hasZeroRight= true;
        }
    }

    private void one_term_signexpected() throws Exception, Exceptions.ExceptionNotATerm {
        parser.parseMember.parse_sign();
        parser.term2 = parser.parseMember.parse_term("second");
    }
}
