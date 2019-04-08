package View.OperationsOnInequalities;

import Controller.Listeners.InequalitiesListController;
import Model.Inequalities.Inequality;
import Model.Inequalities.InequalitiesList;
import java.awt.*;
import java.util.Observable;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.Observer;

/**
 * Home is the class where the Home panel is built implements Observer
 */
public class InequalitiesListGUI extends JPanel implements Observer {

    private InequalitiesList observer;
    private JButton delete;
    private JButton deleteGraph;
    private JButton evaluateAll;
    private DefaultListModel listModel;
    private JList inequalitiesList;

    /**
     * Constructs a new Home panel
     *
     */
    public InequalitiesListGUI() {

        this.listModel = new DefaultListModel();
        this.inequalitiesList = new JList(listModel);
        JScrollPane scroll = new JScrollPane(inequalitiesList);
        scroll.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(153, 218, 250)));
        Border border = BorderFactory.createLineBorder( new Color(153, 218, 250),1,  true);
        delete = new JButton("Delete Inequality");
        delete.setName("delete");
        delete.setPreferredSize(new Dimension(250, 25));
        delete.setBorder(border);
        evaluateAll = new JButton("Evaluate Inequalities");
        evaluateAll.setName("evaluate");
        evaluateAll.setPreferredSize(new Dimension(250, 25));
        evaluateAll.setBorder(border);
        inequalitiesList.setName("projectsList");
        deleteGraph = new JButton("Delete All Inequalities");
        deleteGraph.setName("deleteGraph");
        deleteGraph.setPreferredSize(new Dimension(250, 25));
        deleteGraph.setBorder(border);

        JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deletePanel.add(delete);
        deletePanel.setOpaque(false);


        JPanel deleteGraphPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deleteGraphPanel.add(deleteGraph);
        deleteGraphPanel.setOpaque(false);

        JPanel evaluateAllPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        evaluateAllPanel.add(evaluateAll);
        evaluateAllPanel.setOpaque(false);

        JPanel operations = new JPanel(new GridLayout(3,1));
        operations.add(deletePanel);
        operations.add(deleteGraphPanel);
        operations.add(evaluateAllPanel);
        operations.setOpaque(false);

        JPanel listAndActions= new JPanel(new GridLayout(1,2));
        listAndActions.add(scroll);
        listAndActions.add(operations);
        listAndActions.setOpaque(false);

        this.setLayout(new BorderLayout());
        this.add(listAndActions, BorderLayout.CENTER);
        this.setOpaque(false);
    }

    public void addMouseListener(InequalitiesListController controller){
        delete.addMouseListener(controller);
        evaluateAll.addMouseListener(controller);
        inequalitiesList.addMouseListener(controller);
        deleteGraph.addMouseListener(controller);
    }

    public Inequality getListSelectedValue(){
        return (Inequality) inequalitiesList.getSelectedValue();
    }

    private void UpdateJList(final Inequality in) {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    listModel.addElement(in);
                    inequalitiesList.setModel(listModel);
                    inequalitiesList.repaint();
                    inequalitiesList.ensureIndexIsVisible(listModel.size() - 1);
                }
            });
        }else{
            listModel.addElement(in);
            inequalitiesList.setModel(listModel);
            inequalitiesList.repaint();
            inequalitiesList.ensureIndexIsVisible(listModel.size() - 1);
        }
    }

    @Override
    public void update(Observable obs, Object obj) {
        observer = (InequalitiesList) obs;
        Inequality lastAdded = observer.getLastAdded();
        Inequality lastDeleted = observer.getLastDeleted();
        boolean deleteAllInequalities = observer.shouldDeleteAllInequalities();
        if(lastAdded!=null) {
            UpdateJList(observer.getLastAdded());
        }
        if (lastDeleted != null){
            listModel.removeElement(lastDeleted);
        }
        if (deleteAllInequalities){
            listModel.clear();
        }

        repaint();
        revalidate();
    }

    /**
     * Changes the color scheme to dark mode and back
     */
    public void changeView(boolean dark){
        if (dark){
            inequalitiesList.setBackground(Color.BLACK);
            inequalitiesList.setForeground(Color.WHITE);

            delete.setBackground(Color.BLACK);
            delete.setOpaque(true);
            delete.setForeground(Color.WHITE);

            evaluateAll.setBackground(Color.BLACK);
            evaluateAll.setOpaque(true);
            evaluateAll.setForeground(Color.WHITE);

            deleteGraph.setBackground(Color.BLACK);
            deleteGraph.setOpaque(true);
            deleteGraph.setForeground(Color.WHITE);
        }else{
            inequalitiesList.setBackground(Color.WHITE);
            inequalitiesList.setForeground(Color.BLACK);

            delete.setBackground(Color.WHITE);
            delete.setOpaque(true);
            delete.setForeground(Color.BLACK);

            evaluateAll.setBackground(Color.WHITE);
            evaluateAll.setOpaque(true);
            evaluateAll.setForeground(Color.BLACK);

            deleteGraph.setBackground(Color.WHITE);
            deleteGraph.setOpaque(true);
            deleteGraph.setForeground(Color.BLACK);
        }
    }

}
