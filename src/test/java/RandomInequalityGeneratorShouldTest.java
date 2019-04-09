
import Model.Inequalities.DecisionVariable;
import Model.Inequalities.Inequality;
import Model.Inequalities.RandomInequalities.RandomInequalitiesGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class RandomInequalityGeneratorShouldTest {
    @Test
    public void generateDecisionVariable_And_ReturnIt(){
        RandomInequalitiesGenerator testGenerator = new RandomInequalitiesGenerator();
        DecisionVariable testVariable = testGenerator.generateDecisionVariable();
        Assert.assertNotEquals(testVariable,null);
    }

    @Test
    public void generateDecisionVariable_With_StringId(){
        RandomInequalitiesGenerator testGenerator = new RandomInequalitiesGenerator();
        DecisionVariable testVariable = testGenerator.generateDecisionVariable();
        Assert.assertNotEquals(testVariable.toString(),null);
    }


    @Test
    public void generateInequality_GivenDecisionVariables(){
        RandomInequalitiesGenerator testGenerator = new RandomInequalitiesGenerator();
        ArrayList<DecisionVariable> testDecisionVariables = new ArrayList<>();
        for(int i = 0; i < 49; i ++){
            testDecisionVariables.add(testGenerator.generateDecisionVariable());
        }
        Inequality testInequality = testGenerator.generateInequality(testDecisionVariables);
        Assert.assertNotEquals(testInequality,null);
    }

    @Test
    public void generateInequalityForNode(){
        RandomInequalitiesGenerator testGenerator = new RandomInequalitiesGenerator();
        DecisionVariable testVariable = testGenerator.generateDecisionVariable();
        Inequality testInequality = testGenerator.generateInequalityForNode(testVariable);
        Assert.assertNotEquals(testInequality,null);
    }

    @Test
    public void generateInequalityForSingleNode_String(){
        RandomInequalitiesGenerator testGenerator = new RandomInequalitiesGenerator();
        DecisionVariable testVariable = testGenerator.generateDecisionVariable();
        Inequality testInequality = testGenerator.generateInequalityForNode(testVariable);
        Assert.assertNotEquals(testInequality.getFirstDecisionVariableValue(),null);
    }


}
