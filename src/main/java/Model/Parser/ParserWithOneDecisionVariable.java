package Model.Parser;

import Model.Parser.Exceptions.ExceptionNotATerm;
import Model.Parser.Exceptions.ExceptionNotNumber;

public class ParserWithOneDecisionVariable {

    private final Parser parser;

    public ParserWithOneDecisionVariable(Parser parser) {
        this.parser = parser;
    }

    public void parse_inequality() throws Exception, ExceptionNotATerm {
        parser.toParse = "";
        double boundry = 0;
        try {
            parser.term1 = parser.parseMember.parse_term("first");
            parser.parseMember.parse_sign();
            try {
                boundry = parser.parseMember.parse_number();
            } catch (ExceptionNotNumber exceptionNotNumber) {
                System.out.println("not number");
            }
            if (parser.sign.equals("<") || parser.sign.equals("<=")){
                parser.inequality.getFirstDecisionVariable().setUpperBound(boundry/parser.inequality.getFirstDecisionVariable().getWeight());
            }else{
                parser.inequality.getFirstDecisionVariable().setLowerBound(boundry/parser.inequality.getFirstDecisionVariable().getWeight());
            }
        } catch (ExceptionNotATerm exceptionNotATerm) {
            try {
                parser.parseMember.parse_number();
            } catch (ExceptionNotNumber exceptionNotNumber) {
                System.out.println("not number");
            }
            parser.parseMember.parse_sign();
            parser.term1 = parser.parseMember.parse_term("first");
            if (parser.sign.equals("<") || parser.sign.equals("<=")){
                parser.inequality.getFirstDecisionVariable().setLowerBound(boundry/parser.inequality.getFirstDecisionVariable().getWeight());
            }else{
                parser.inequality.getFirstDecisionVariable().setUpperBound(boundry/parser.inequality.getFirstDecisionVariable().getWeight());
            }
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
