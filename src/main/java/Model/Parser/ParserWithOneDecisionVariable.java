package Model.Parser;

import Model.Parser.Exceptions.ExceptionNotATerm;
import Model.Parser.Exceptions.ExceptionNotNumber;

public class ParserWithOneDecisionVariable {

    private final Parser parser;

    ParserWithOneDecisionVariable(Parser parser) {
        this.parser = parser;
    }

    void parseInequality() throws Exception, ExceptionNotATerm {
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
                parser.inequality.getLeftDecisionVariable().setUpperBound(boundry/parser.inequality.getLeftDecisionVariable().getWeight());
            }else{
                parser.inequality.getLeftDecisionVariable().setLowerBound(boundry/parser.inequality.getLeftDecisionVariable().getWeight());
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
                parser.inequality.getLeftDecisionVariable().setLowerBound(boundry/parser.inequality.getLeftDecisionVariable().getWeight());
            }else{
                parser.inequality.getLeftDecisionVariable().setUpperBound(boundry/parser.inequality.getLeftDecisionVariable().getWeight());
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
