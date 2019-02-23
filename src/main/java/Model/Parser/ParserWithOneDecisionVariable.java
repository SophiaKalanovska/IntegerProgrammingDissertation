package Model.Parser;

public class ParserWithOneDecisionVariable {

    private final Parser parser;

    public ParserWithOneDecisionVariable(Parser parser) {
        this.parser = parser;
    }

    public void parse_inequality() throws Exception, Exceptions.ExceptionNotATerm {
        parser.toParse = "";
        int boundry = 0;
        try {
            parser.term1 = parser.parseMember.parse_term("first");
            System.out.println("successfully parsed term" );
            parser.parseMember.parse_sign();
            try {
                boundry = parser.parseMember.parse_number();
            } catch (Exceptions.ExceptionNotNumber exceptionNotNumber) {
                System.out.println("not number");
            }
            if (parser.sign.equals("<") || parser.sign.equals("<=")){
                parser.inequality.getFirstDecisionVariable().setUpperBound(boundry);
            }else{
                parser.inequality.getFirstDecisionVariable().setLowerBound(boundry);
            }
            System.out.println("success in the form of Nx< k");
        } catch (Exceptions.ExceptionNotATerm exceptionNotATerm) {
            try {
                parser.parseMember.parse_number();
            } catch (Exceptions.ExceptionNotNumber exceptionNotNumber) {
                System.out.println("not number");
            }
            parser.parseMember.parse_sign();
            parser.term1 = parser.parseMember.parse_term("first");
            if (parser.sign.equals("<") || parser.sign.equals("<=")){
                parser.inequality.getFirstDecisionVariable().setLowerBound(boundry);
            }else{
                parser.inequality.getFirstDecisionVariable().setUpperBound(boundry);
            }
            System.out.println("success in the form of k < Nx");
        } catch (Exception e) {
            System.out.println("some other exception");
        }

    }

    public void clear() {
        parser.src = parser.srcOriginal;
        parser.pos = 0 ;
        parser.toParse = "";
    }
}
