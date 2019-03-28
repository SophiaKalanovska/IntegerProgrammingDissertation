package SCC;

import Model.SCC.SCCClusterList;
import org.testng.Assert;
import org.testng.annotations.Test;



public class SCCClusterListShould {


    @Test
    public void generateSCCClusterList_ThatIsEmpty(){
        SCCClusterList testClusterList = new SCCClusterList();
        Assert.assertEquals(testClusterList.getProjectWallet().size(),0);
    }

    @Test
    public void addClusterToClusterListShouldAddACluster(){
        SCCClusterList testClusterList = new SCCClusterList();
        Assert.assertEquals(testClusterList.getProjectWallet().size(),1);
    }

    @Test
    public void evaluteCluster(){
        SCCClusterList testClusterList = new SCCClusterList();
        Assert.assertEquals(testClusterList.getProjectWallet().size(),1);
    }

    @Test
    public void lambdaMinus(){
        SCCClusterList testClusterList = new SCCClusterList();
        Assert.assertEquals(testClusterList.getProjectWallet().size(),1);
    }

    @Test
    public void lambdaPlus(){
        SCCClusterList testClusterList = new SCCClusterList();
        Assert.assertEquals(testClusterList.getProjectWallet().size(),1);
    }

    @Test
    public void evaluateLambdas(){
        SCCClusterList testClusterList = new SCCClusterList();
        Assert.assertEquals(testClusterList.getProjectWallet().size(),1);
    }

    @Test
    public void isSolvable(){
        SCCClusterList testClusterList = new SCCClusterList();
        Assert.assertEquals(testClusterList.getProjectWallet().size(),1);
    }
}

