package model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * This class represents a portofolio of Projects
 *
 */
public class InequalitiesList extends Observable implements java.io.Serializable {


    private ArrayList<Inequality> inequalitiesContainer;
//private HashMap<String, Projects> projectMap;


    /**
     * Creates a InequalitiesList object
     *
     */
    public InequalitiesList()
    {
        inequalitiesContainer = new ArrayList<Inequality>();
        //projectMap = new HashMap<String, Projects>();
    }

    /**
     * Creates a InequalitiesList object that is to be populated with the data supplied
     *
     * @param data the ArrayList of Projects that will constitute the newly created InequalitiesList object
     */
    public InequalitiesList(ArrayList<Inequality> data)
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
    public void addInequality(Inequality x)
    {
        //projectMap.put(x.toString(), x);
        this.inequalitiesContainer.add(x);
        setChanged();
        notifyObservers();
    }


    /**
     * Deletes a project that has the same name as the one supplied
     *
     * @param name the name of the projects that has to be deleted
     */
    public void deleteInequality(String name)
    {

        for (int i = 0 ; i < inequalitiesContainer.size() ; i++)
        {
            if (((inequalitiesContainer.get(i)).getExpreission()).equals(name))
            {
                inequalitiesContainer.remove(i);
            }
        }

        setChanged();
        notifyObservers();
    }

    /**
     * Changes the name of a project to the new one
     *
     * @param oldInequality the current name of the project
     * @param newInequality the new name for the project
     */
    public void changeInequality(String oldInequality, String newInequality)
    {
        for (int i = 0; i <inequalitiesContainer.size() ; i++)
        {
            if (((inequalitiesContainer.get(i)).getExpreission().equals(oldInequality)))
            {
                (inequalitiesContainer.get(i)).changeExpression(newInequality);
            }
        }

        setChanged();
        notifyObservers();
    }


    /**
     * Returns the wallet as an ArrayList of Projects
     *
     * @return an ArrayList of projects that are contained in this wallet
     */
    public ArrayList<Inequality> getProjectWallet()
    {
        return inequalitiesContainer;
    }


    public void storeProject(ArrayList<Inequality> data)
    {
        this.inequalitiesContainer = data;

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