package Model.Inequalities;
import java.util.ArrayList;
import java.util.Observable;
/**
 * This class represents a portofolio of Inequalities
 */
public class InequalitiesList extends Observable implements java.io.Serializable {
    private ArrayList<Inequality> inequalitiesContainer;
    private Inequality lastAdded;
    private Inequality lastDeleted;
    private boolean deleteAll;

    /**
     * Creates a InequalitiesList object
     */
    public InequalitiesList() {
        inequalitiesContainer = new ArrayList<>();
        this.lastAdded = null;
        this.lastDeleted = null;
    }

    /**
     * Adds an inequality to the container
     */
    public void addInequality(Inequality x) {
        this.inequalitiesContainer.add(x);
        lastAdded = x;
        setChanged();
        notifyObservers();
        lastAdded = null;
    }

    /**
     * Deletes an inequality that has the same sting id
     */
    public void deleteInequality(Inequality x) {
        lastDeleted = x;
        inequalitiesContainer.remove(x);
        setChanged();
        notifyObservers();
        lastDeleted = null;
    }

    /**
     * Deletes all inequalities from the container
     */
    public void deleteAllInequalities() {
        deleteAll = true;
        inequalitiesContainer.removeAll(inequalitiesContainer);
        tryUpdate();
        deleteAll = false;
    }


    /**
     * Returns a parameter indicating whether the last
     * change in the list was deletion or addition
     */
    public Inequality getLastAdded() {
        return lastAdded;
    }

    public Inequality getLastDeleted() { return lastDeleted; }

    public boolean shouldDeleteAllInequalities(){return deleteAll; }

    public ArrayList<Inequality> getInequalitiesContainer() { return inequalitiesContainer;}


    /**
     * Sends signal to the observers to update the View
     */
    public void tryUpdate() {
        setChanged();
        notifyObservers();
    }

}
