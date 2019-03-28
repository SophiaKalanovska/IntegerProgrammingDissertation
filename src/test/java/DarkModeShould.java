import org.testng.annotations.Test;

public class DarkModeShould {

    @Test
    public void activateDarkMode(){
        Container container = new Container();
        container.getLayoutGUI().changeView(true);
    }


    @Test
    public void disactivateDarkMode(){
        Container container = new Container();
        container.getLayoutGUI().changeView(false);
    }

}
