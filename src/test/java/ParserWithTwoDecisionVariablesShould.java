import Model.Inequalities.Inequality;
import Model.Parser.Parser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParserWithTwoDecisionVariablesShould {
    @Test
    public void parse_3xLessThanY_AndReturn_XAsFirstDecisionVariable() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("3x<y");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getFirstDecisionVariableValue(),"x");
    }

    @Test
    public void parse_3xLessThanY_AndReturn_YAsSecondDecisionVariable() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("3x<y");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getSecondDecisionVariableValue(),"y");
    }

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
        Assert.assertEquals(inequality.getFirstDecisionVariable().getWeight(),3.0);
    }

    @Test
    public void parse_Minus3xLessThanOrEqualsY_AndReturn_MinusSignForX() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("-3x<=y");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getFirstDecisionVariable().getSign(),"-");
    }

    @Test
    public void parse_3xLessThanOrEqualsY_AndReturn_1AsWeightForY() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("3x<=y");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getSecondDecisionVariable().getWeight(),1.0);
    }

    @Test
    public void parse_3xLessThanOrEqualsMinusY_AndReturn_MinusOneForY() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("3x<=-y");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getSecondDecisionVariable().getWeight(),-1.0);
    }

    @Test
    public void parse_3xPlusYLessThan0_AndReturn_XAsFirstDecisionVariable() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("3x + y < 0");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getFirstDecisionVariableValue(),"x");
    }

    @Test
    public void parse_3xPlusYLessThan0_AndReturn_YAsSecondDecisionVariable() throws Exception {
        Parser testParser = new Parser();
        testParser.setString("3x + y < 0");
        Inequality inequality = testParser.parse();
        Assert.assertEquals(inequality.getSecondDecisionVariableValue(),"y");
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


}
