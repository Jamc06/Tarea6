

import controller.CatalogController;
import view.ConsoleView;

public class principal {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        CatalogController controller = new CatalogController(view);
        controller.run();  // inicia el menú interactivo
    }
}