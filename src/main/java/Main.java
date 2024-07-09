import com.lesnoy.application.service.BookService;
import org.summer.context.IntensiveContext;

public class Main {

    public static void main(String[] args) {
        IntensiveContext context = new IntensiveContext("com.lesnoy.application");
        context.getObject(BookService.class);
    }
}
