package model;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class Parser{

  private String src;
  private String srcOriginal;
  private int pos;

  public Parser(String src){
    this.srcOriginal = src;
    this.src = src;
    this.pos = 0 ;
  }


  public ArrayList<String> parse() throws Exception{
    ArrayList<String> start = parse_inequality();

    if (pos == srcOriginal.length()){
      System.out.println(start);
      return start;
    }else{
       throw new Exception( " Source does not match the grammar . " );
    }
  }



     public String tokenize(String regex) throws Exception{

         Pattern reg = Pattern.compile("^("+regex+")");
         String s = src.trim();
         System.out.println("the word");
         System.out.println(s);

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


       public String parse_term() throws Exception{
         try {
            System.out.println("before tokenize");
            // "[a-zA-Z][a-zA-Z0-9_]*"
            String term =  tokenize("[0-9]*[a-zA-Z][a-zA-Z0-9_]*");
            return term;
         }catch (Exception e){
            throw new Exception ( " Expecting pattern term " );

          }
       }



       public String parse_expression()throws Exception{
           try {
         String expression = parse_term();
         return  expression ;
       }catch (Exception e){
         throw new Exception ( " Expecting pattern ELEM at position {0} " );

       }
       }



  public ArrayList<String> parse_inequality() throws Exception{
    // int save_pos = pos;
    try {
        ArrayList<String> getInequality = new ArrayList<String>();
        String expression = parse_expression();
        String sign = tokenize( "<" );
        System.out.println(sign);
        String inequality = parse_expression();
        getInequality.add(expression);
        getInequality.add(sign);
        getInequality.add(inequality);
        return getInequality;
    }catch (Exception e){
      throw new Exception ( " Expecting pattern ELEM at position {0} " );

    }
  }

}
