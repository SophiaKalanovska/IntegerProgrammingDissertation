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
            parser.term1 = parser.parseMember.parseTerm("first");
            parser.parseMember.parseSign();
            try {
                boundry = parser.parseMember.parseNumber();
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
                parser.parseMember.parseNumber();
            } catch (ExceptionNotNumber exceptionNotNumber) {
                System.out.println("not number");
            }
            parser.parseMember.parseSign();
            parser.term1 = parser.parseMember.parseTerm("first");
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
