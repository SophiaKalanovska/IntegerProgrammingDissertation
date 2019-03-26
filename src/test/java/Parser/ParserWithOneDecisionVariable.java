package Parser;

import Model.Inequalities.Inequality;
import Model.Parser.Parser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParserWithOneDecisionVariable {


    @Test
    public void parse_3xLessThan7_AndReturn_XAsFirstDecisionVariable() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("x<7");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getFirstDecisionVariableValue(),"x");
    }

    @Test
    public void parse_3xLessThan7_AndReturn_7AsAnUpperBound() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("x<7");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getFirstDecisionVariable().getUpperBound(),7.0);
    }

    @Test
    public void parse_3xLessThanOrEqual7_AndReturn_XAsFirstDecisionVariable() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("x<=7");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getFirstDecisionVariableValue(),"x");
    }

    @Test
    public void parse_3xLessThanOrEqual7_AndReturn_7AsAnUpperBound() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("x<=7");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getFirstDecisionVariable().getUpperBound(),7.0);
    }


    @Test
    public void parse_3xGreaterThan7_AndReturn_XAsFirstDecisionVariable() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("x>7");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getFirstDecisionVariableValue(),"x");
    }

    @Test
    public void parse_3xGreaterThan7_AndReturn_7AsAnLowerBound() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("x>7");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getFirstDecisionVariable().getLowerBound(),7.0);
    }

    @Test
    public void parse_3xGreaterThanOrEqual7_AndReturn_XAsFirstDecisionVariable() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("x>=7");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getFirstDecisionVariableValue(),"x");
    }

    @Test
    public void parse_3xGreaterThanOrEqual7_AndReturn_7AsAnLowerBound() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("x>=7");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getFirstDecisionVariable().getLowerBound(),7.0);
    }






//
//    @Test
//    public void parse_3xLessThanY_AndReturn_YAsSecondDecisionVariable() throws Exception {
//        Parser testParser = new Parser();
//        testParser.setString("3x<y");
//        Inequality inequality = testParser.parse();
//        Assert.assertEquals(inequality.getSecondDecisionVariableValue(),"y");
//    }

}
