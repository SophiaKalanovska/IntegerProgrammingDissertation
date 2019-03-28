package Inequalities;

import Model.Inequalities.InequalitiesList;
import Model.Inequalities.Inequality;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InequalitiesListShould {

    @Test
    public void generateInequalityList_ThatIsEmpty(){
        InequalitiesList testInequalityList = new InequalitiesList();
        Assert.assertEquals(testInequalityList.getInequalitiesContainer().size(),0);
    }

    @Test
    public void generateInequalityList_AndGetLastAddedShouldBeNull(){
        InequalitiesList testInequalityList = new InequalitiesList();
        Assert.assertEquals(testInequalityList.getLastAdded(),null);
    }

    @Test
    public void generateInequalityList_AndGetLastDeletedShouldBeNull(){
        InequalitiesList testInequalityList = new InequalitiesList();
        Assert.assertEquals(testInequalityList.getLastDeleted(),null);
    }

    @Test
    public void generateInequalityList_AndshouldDeleteAllInequalitiesShouldBeFalse(){
        InequalitiesList testInequalityList = new InequalitiesList();
        Assert.assertEquals(testInequalityList.shouldDeleteAllInequalities(),false);
    }

    @Test
    public void addInequalityToTheList(){
        InequalitiesList testInequalityList = new InequalitiesList();
        Inequality inequality = new Inequality();
        testInequalityList.addInequality(inequality);
        Assert.assertEquals(testInequalityList.getInequalitiesContainer().size(),1);
    }

    @Test
    public void deleteInequalityFromTheList(){
        InequalitiesList testInequalityList = new InequalitiesList();
        Inequality inequality = new Inequality();
        testInequalityList.addInequality(inequality);
        Assert.assertEquals(testInequalityList.getInequalitiesContainer().size(),1);
        testInequalityList.deleteInequality(inequality);
        Assert.assertEquals(testInequalityList.getInequalitiesContainer().size(),0);
    }

    @Test
    public void addMultipleInequalitiesAndDeleteThemAll(){
        InequalitiesList testInequalityList = new InequalitiesList();
        Inequality inequality = new Inequality();
        Inequality inequality2 = new Inequality();
        testInequalityList.addInequality(inequality);
        testInequalityList.addInequality(inequality2);
        Assert.assertEquals(testInequalityList.getInequalitiesContainer().size(),2);
        testInequalityList.deleteAllInequalities();
        Assert.assertEquals(testInequalityList.getInequalitiesContainer().size(),0);
    }
}

