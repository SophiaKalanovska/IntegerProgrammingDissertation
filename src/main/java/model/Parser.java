package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser{

  private String src;
  private String srcOriginal;
  private int pos;
    private Inequality inequality;
    private String toParse;
    private String term1;
    private String term2;

    public Parser(String src) {
        this.srcOriginal = src.replaceAll("\\s+", "");
        this.toParse = "";
        this.src = src.replaceAll("\\s+", "");
        this.pos = 0 ;
  }


  public Inequality parse() throws Exception{
      inequality = new Inequality();
      parse_expression();
    if (pos == srcOriginal.length()){
        inequality.setExpreission(srcOriginal);
        return inequality;
    }else{
       throw new Exception( " Source does not match the grammar." );
    }
  }

     public String tokenize(String regex) throws Exception{

         Pattern reg = Pattern.compile("^("+regex+")");
         String s = "";
         if (!toParse.equals("")) {
             s = toParse;

         }
         s = src.trim();

         while (!s.equals(""))
         {
           boolean match = false;
             Matcher m = reg.matcher(s);
             if (m.find())
             {
               match = true;
               String tok = m.group().trim();

               src = m.replaceFirst("").trim();
               System.out.println(s);
               pos += tok.length();
               System.out.println(tok);
               return tok;

             }

          if (!match) throw new Exception("Unexpected character in input: "+s);
         }
         return null;
       }
//
//

    public int parse_weight(String expression) throws NoWeightException {
        try {

            toParse = expression;
            String term = tokenize("[0-9]+");
            return Integer.parseInt(term);
        } catch (Exception e) {
            throw new NoWeightException(" No weight");

        }
    }

    public String parse_UnknownVariable(String expression) throws Exception {
        try {

            toParse = expression;
            String term = tokenize("[a-zA-Z][a-zA-Z0-9_]*");
            toParse = "";
            return term;
        } catch (Exception e) {
            throw new Exception("");

        }
    }

    public String parse_term(String order) throws ExceptionNotATerm {
        String term = null;
        try {
            term = tokenize("[+|-]*[0-9]*[a-zA-Z][a-zA-Z0-9_]*");
        } catch (Exception e) {
            throw new ExceptionNotATerm(" Expecting pattern term ");
        }

        if (term != null) {
            try {
                if (order.equals("first")) {
                    inequality.setFirstdWeight(parse_weight(term));
                } else {
                    inequality.setSecondWeight(parse_weight(term));
                }
            } catch (NoWeightException e) {
                System.out.println("no weight");
            }
            try {
                inequality.setFirstUnknownVariable(parse_UnknownVariable(term));
            } catch (Exception e) {
                throw new ExceptionNotATerm(" Expecting pattern term ");
            }
        }
        return term;

    }


    public void parse_expression() throws Exception {

        parse_OneSide();
//        inequality.setSign(tokenize(" <|>|<=|>="));
//        parse_OneSide();
    }

    private void parse_OneSide() {

        try {
            term1 = parse_term("first");
        } catch (ExceptionNotATerm e) {
            System.out.println("First term is not valid");
            try {
                parse_maybeNull();
            } catch (ExceptionNotNull exceptionNotNull) {
                System.out.println("the expression may have only one term, if not is not valid");
            }
            System.out.println("successfully parsed null");
            try {
                inequality.setSign(tokenize(" <|>|<=|>="));
            } catch (Exception e1) {
                System.out.println("invalid");
            }
            System.out.println("successfully parsed sign");
            parse_OneSide();

        }
        System.out.println("successfully parsed first term");
        try {
            term2 = parse_term("second");
        } catch (ExceptionNotATerm e) {
            try {
                inequality.setSign(tokenize(" <|>|<=|>="));
            } catch (Exception e1) {
                System.out.println("invalid");
            }
            try {
                term2 = parse_term("second");
            } catch (ExceptionNotATerm e1) {
                System.out.println("invalid");
            }
        }
        System.out.println("successfully parsed second term");
    }

    private int parse_maybeNull() throws ExceptionNotNull {
        try {

            String term = tokenize("0");
            return Integer.parseInt(term);
        } catch (Exception e) {
            throw new ExceptionNotNull("something else");
        }
    }


//
//  public ArrayList<String> parse_inequality() throws Exception{
//    // int save_pos = pos;
//    try {
//        ArrayList<String> getInequality = new ArrayList<String>();
//        String expression = parse_expression();
//        String sign = tokenize( "<" );
//        System.out.println(sign);
//        String inequality = parse_expression();
//        getInequality.add(expression);
//        getInequality.add(sign);
//        getInequality.add(inequality);
//        return getInequality;
//    }catch (Exception e){
//      throw new Exception ( " Expecting pattern ELEM at position {0} " );
//
//    }
//  }

    public void parseDecisionVariables() {
        try {

//        ArrayList<String> getInequality = new ArrayList<String>();
//        String expression = parse_expression();
//        String sign = tokenize( "<" );
//        System.out.println(sign);
//        String inequality = parse_expression();
//        getInequality.add(expression);
//        getInequality.add(sign);
//        getInequality.add(inequality);
//        return getInequality;
        } catch (Exception e) {
//            throw new Exception(" Expecting pattern ELEM at position {0} ");
        }
    }


    private class NoWeightException extends Throwable {
        public NoWeightException(String s) {
        }
    }

    private class ExceptionNotNull extends Throwable {
        public ExceptionNotNull(String something_else) {
        }
    }

    private class ExceptionNotATerm extends Throwable {
        public ExceptionNotATerm(String s) {
        }
    }
}
