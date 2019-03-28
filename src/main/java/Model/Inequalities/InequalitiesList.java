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
     * Adds a project to the wallet
     *
     * @param x the project that is to be added to the wallet
     */
    public void addInequality(Inequality x) {
        this.inequalitiesContainer.add(x);
        lastAdded = x;
        setChanged();
        notifyObservers();
        lastAdded = null;
    }

    /**
     * Deletes a project that has the same name as the one supplied
     *
     * @param x the name of the projects that has to be deleted
     */
    public void deleteInequality(Inequality x) {
        lastDeleted = x;
        inequalitiesContainer.remove(x);
        setChanged();
        notifyObservers();
        lastDeleted = null;
    }

    public void deleteAllInequalities() {
        deleteAll = true;
        inequalitiesContainer.removeAll(inequalitiesContainer);
        tryUpdate();
    }

    /**
     * Returns the wallet as an ArrayList of Projects
     *
     * @return an ArrayList of projects that are contained in this wallet
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
