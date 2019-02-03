package model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * This class represents a portofolio of Projects
 *
 */
public class InequalitiesList extends Observable implements java.io.Serializable {


    private ArrayList<String> projectContainer;
//private HashMap<String, Projects> projectMap;


    /**
     * Creates a InequalitiesList object
     *
     */
    public InequalitiesList()
    {
        projectContainer = new ArrayList<String>();
        //projectMap = new HashMap<String, Projects>();
    }

    /**
     * Creates a InequalitiesList object that is to be populated with the data supplied
     *
     * @param data the ArrayList of Projects that will constitute the newly created InequalitiesList object
     */
    public InequalitiesList(ArrayList<String> data)
    {
        storeProject(data);
        setChanged();
        notifyObservers();
    }

    /**
     * Adds a project to the wallet
     *
     * @param x the project that is to be added to the wallet
     */
    public void addProject(Inequality x)
    {
        //projectMap.put(x.toString(), x);
        this.projectContainer.add(x.getexpreission());
        setChanged();
        notifyObservers();
    }


    /**
     * Deletes a project that has the same name as the one supplied
     *
     * @param name the name of the projects that has to be deleted
     */
//    public void deleteProject(String name)
//    {
//
//        for (int i = 0 ; i < projectContainer.size() ; i++)
//        {
//            if (((projectContainer.get(i)).getName()).equals(name))
//            {
//                projectContainer.remove(i);
//            }
//        }
//
//        setChanged();
//        notifyObservers();
//    }

    /**
     * Changes the name of a project to the new one
     *
     * @param old the current name of the project
     * @param newProject the new name for the project
     */
//    public void change(String old, String newProject)
//    {
//        for (int i = 0; i <projectContainer.size() ; i++)
//        {
//            if (((projectContainer.get(i)).getName()).equals(old))
//            {
//                (projectContainer.get(i)).setName(newProject);
//            }
//        }
//
//        setChanged();
//        notifyObservers();
//    }


    /**
     * Returns the wallet as an ArrayList of Projects
     *
     * @return an ArrayList of projects that are contained in this wallet
     */
    public ArrayList<String> getProjectWallet()
    {


        return projectContainer;

    }


    public void storeProject(ArrayList<String> data)
    {
        this.projectContainer = data;

    }

    /**
     * Sends signal to the observers to update the view
     *
     */
    public void tryUpdate(){
        setChanged();
        notifyObservers();
    }



}
