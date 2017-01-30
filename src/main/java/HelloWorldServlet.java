import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "HellowWorldServlet", urlPatterns = {"/hello"})
public class HelloWorldServlet extends HttpServlet {
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse res
    ) throws IOException {

            res.setContentType("text/html"); //default

        PrintWriter writer = res.getWriter();

        String name = req.getParameter("name");
        if (name != null) {
            writer.println("<h1>Hello " + name + "</h1>");
        } else {
            writer.println("<h1>Hello World</h1>");
        }

    }
}
