package Parser;

import Model.Inequalities.Inequality;
import Model.Parser.Parser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParserWithTwoDecisionVariablesShould {

    @Test
    public void parse_3xLessThanOrEqualsY_AndReturn_XAsFirstDecisionVariable() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("3x<=y");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getFirstDecisionVariableValue(),"x");
    }

    @Test
    public void parse_3xLessThanOrEqualsY_AndReturn_YAsSecondDecisionVariable() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("3x<=y");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getSecondDecisionVariableValue(),"y");
    }

    @Test
    public void parse_3xLessThanOrEqualsY_AndReturn_3AsWeightForX() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("3x<=y");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getLeftDecisionVariable().getWeight(),3.0);
    }

    @Test
    public void parse_Minus3xLessThanOrEqualsY_AndReturn_MinusSignForX() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("-3x<=y");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getLeftDecisionVariable().getWeight(),-3.0);
    }

    @Test
    public void parse_3xLessThanOrEqualsY_AndReturn_1AsWeightForY() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("3x<=y");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getRightDecisionVariable().getWeight(),1.0);
    }

    @Test
    public void parse_3xLessThanOrEqualsMinusY_AndReturn_MinusOneForY() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("3x<=-y");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getRightDecisionVariable().getWeight(),-1.0);
    }

    @Test
    public void parse_3xPlusYLessThanOrEquals0_AndReturn_XAsFirstDecisionVariable() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("3x + y <= 0");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getFirstDecisionVariableValue(),"x");
    }

    @Test
    public void parse_3xPlusYLessThanOrEquals0_AndReturn_YAsSecondDecisionVariable() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("3x + y <= 0");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getSecondDecisionVariableValue(),"y");
    }


    @Test
    public void parse_3xGreaterThanOrEqualsY_AndReturn_YAsFirstDecisionVariable() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("3x>=y");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getFirstDecisionVariableValue(),"y");
    }

    @Test
    public void parse_3xGreaterThanOrEqualsY_AndReturn_XAsSecondDecisionVariable() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("3x>=y");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getSecondDecisionVariableValue(),"x");
    }

    @Test(expectedExceptions = java.lang.Exception.class)
    public void parse_3xLessThanNull_ShouldThrowError() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("3x<=");
        testParser.parse();
    }

    @Test
    public void parse_InvalidExpressionFirstTerm(){
        Parser testParser = new Parser();
        testParser.setString("3<=y");
        try {
            testParser.parse();
        } catch (Exception e) {
            Assert.assertEquals( e, " Source does not match the grammar. ");
        }

    }
    @Test
    public void parse_InvalidExpressionSecondTerm(){
        Parser testParser = new Parser();
        testParser.setString("3x<=4");
        try {
            testParser.parse();
        } catch (Exception e) {
            Assert.assertEquals( e, " Source does not match the grammar. ");
        }
    }
}
