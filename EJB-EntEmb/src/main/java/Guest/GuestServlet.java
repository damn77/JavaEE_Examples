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

	static String PAGE_HEADER = "<html><head><title>EJB-EntEmb</title></head><body>";

    static String PAGE_CONTENT = "<h1>EJB - Entity Beans - Guestbook with Embedded Bean</h1>"
    		+ "<p> This guestbook can store guests name in single embbeded object but first and last name are separate and in different columns in table." 
    		+ "</p>"
    		+ "<form>"
            + "First Name: <input type=\"text\" name=\"fname\" /><br />"
            + "Last Name: <input type=\"text\" name=\"lname\" /><br />"
            + "<input type=\"submit\" value=\"Submit\" /><br />"
            + "</form>";

    static String PAGE_FOOTER = "</body></html>";

    @EJB
    GuestDao dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String responseText;
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");

            responseText = dao.addGuest(fname , lname);

        writer.println(PAGE_HEADER);
        writer.println(PAGE_CONTENT);
        writer.println("<p>" + responseText + "</p>");
        writer.println(PAGE_FOOTER);

        writer.close();
    }

}
