import com.lesnoy.application.controller.BookController;
import org.summer.context.IntensiveContext;

public class Main {

    public static void main(String[] args) {
        IntensiveContext context = new IntensiveContext("com.lesnoy.application");
        BookController controller = context.getObject(BookController.class);
        System.out.println(controller);
        System.out.println(controller.getBookService());
    }
}
