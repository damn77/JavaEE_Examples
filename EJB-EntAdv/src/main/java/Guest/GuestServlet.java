package Guest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignIn")
public class GuestServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	static String PAGE_HEADER = "<html><head><title>EJB-EntAdv</title></head><body>";

    static String PAGE_CONTENT = "<h1>EJB - Entity Beans - Guestbook with Multiple Tables</h1>"
    		+ "<p> This guestbook allows guest to visit multiple times and be recognised as the same.<br />Guests name is primary unique key." 
    		+ "</p>"
    		+ "<form>"
            + "Name: <input type=\"text\" name=\"name\" /><br />"
            + "<input type=\"submit\" value=\"Submit\" /><br />"
            + "</form>";

    static String PAGE_FOOTER = "</body></html>";

    @EJB
    GuestDao dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String responseText;
        String name = req.getParameter("name");

            responseText = dao.addVisit(name);

        writer.println(PAGE_HEADER);
        writer.println(PAGE_CONTENT);
        writer.println("<p>" + responseText + "</p>");
        writer.println(PAGE_FOOTER);

        writer.close();
    }

}
