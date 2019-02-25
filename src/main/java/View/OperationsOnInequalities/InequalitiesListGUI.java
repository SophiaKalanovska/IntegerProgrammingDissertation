package View.OperationsOnInequalities;

import Controller.InequalitiesListController;
import Model.Inequalities.Inequality;
import Model.Inequalities.InequalitiesList;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.Observer;

/**
 * Home is the class where the Home panel is built implements Observer
 */
public class InequalitiesListGUI extends JPanel implements Observer {

    private InequalitiesList info;
    private InequalitiesList observer;
    private JButton delete;
    private JButton deleteAll;
    private JButton deleteGraph;
    private JButton evaluateAll;
    private DefaultListModel listModel;
    private JList projectsList;

    /**
     * Constructs a new Home panel
     *
     */
    public InequalitiesListGUI() {

        this.listModel = new DefaultListModel();
        this.projectsList = new JList(listModel);
        JScrollPane scroll = new JScrollPane(projectsList);
        scroll.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(153, 218, 250)));
        Border border = BorderFactory.createLineBorder( new Color(153, 218, 250),1,  true);
        delete = new JButton("Delete Inequality");
        delete.setName("delete");
        delete.setPreferredSize(new Dimension(250, 25));
        delete.setBorder(border);
        evaluateAll = new JButton("Evaluate Graph");
        evaluateAll.setName("evaluate");
        evaluateAll.setPreferredSize(new Dimension(250, 25));
        evaluateAll.setBorder(border);
        projectsList.setName("projectsList");
        deleteAll = new JButton("Delete All Inequalities");
        deleteAll.setName("deleteAll");
        deleteAll.setPreferredSize(new Dimension(250, 25));
        deleteAll.setBorder(border);
        deleteGraph = new JButton("Delete Graph");
        deleteGraph.setName("deleteGraph");
        deleteGraph.setPreferredSize(new Dimension(250, 25));
        deleteGraph.setBorder(border);

        JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deletePanel.add(delete);
        deletePanel.setOpaque(false);

        JPanel deleteAllPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deleteAllPanel.add(deleteAll);
        deleteAllPanel.setOpaque(false);

        JPanel deleteGraphPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deleteGraphPanel.add(deleteGraph);
        deleteGraphPanel.setOpaque(false);

        JPanel evaluateAllPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        evaluateAllPanel.add(evaluateAll);
        evaluateAllPanel.setOpaque(false);

        JPanel operations = new JPanel(new GridLayout(4,1));
        operations.add(deletePanel);
        operations.add(deleteAllPanel);
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
        System.out.println(" ManualInequalitiesGUI mouselistener added");
        delete.addMouseListener(controller);
        evaluateAll.addMouseListener(controller);
        projectsList.addMouseListener(controller);
        deleteAll.addMouseListener(controller);
        deleteGraph.addMouseListener(controller);
    }

    public Inequality getListSelectedValue(){
        return (Inequality) projectsList.getSelectedValue();
    }

    private void UpdateJList(ArrayList<Inequality> in){
        listModel.clear();
        for(Inequality i : in){
            listModel.addElement(i);
        }
        projectsList.setModel(listModel);

    }

    @Override
    public void update(Observable obs, Object obj) {
        observer = (InequalitiesList) obs;
        UpdateJList(observer.getProjectWallet());
        repaint();
        revalidate();

//        JList wordList = getWordListScroller();
//        int lastIndex = wordList.getModel().getSize() - 1;
//        if (lastIndex >= 0) {
//            wordList.ensureIndexIsVisible(lastIndex);
//        }
    }

    public void changeView(boolean dark){
        if (dark){
            projectsList.setBackground(Color.BLACK);
            projectsList.setForeground(Color.WHITE);

            delete.setBackground(Color.BLACK);
            delete.setOpaque(true);
            delete.setForeground(Color.WHITE);

            deleteAll.setBackground(Color.BLACK);
            deleteAll.setOpaque(true);
            deleteAll.setForeground(Color.WHITE);

            evaluateAll.setBackground(Color.BLACK);
            evaluateAll.setOpaque(true);
            evaluateAll.setForeground(Color.WHITE);

            deleteGraph.setBackground(Color.BLACK);
            deleteGraph.setOpaque(true);
            deleteGraph.setForeground(Color.WHITE);
        }else{
            projectsList.setBackground(Color.WHITE);
            projectsList.setForeground(Color.BLACK);

            delete.setBackground(Color.WHITE);
            delete.setOpaque(true);
            delete.setForeground(Color.BLACK);

            deleteAll.setBackground(Color.WHITE);
            deleteAll.setOpaque(true);
            deleteAll.setForeground(Color.BLACK);

            evaluateAll.setBackground(Color.WHITE);
            evaluateAll.setOpaque(true);
            evaluateAll.setForeground(Color.BLACK);

            deleteGraph.setBackground(Color.WHITE);
            deleteGraph.setOpaque(true);
            deleteGraph.setForeground(Color.BLACK);
        }
    }
}
