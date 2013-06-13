package bmt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BMT")
public class TransactionServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static String PAGE_HEADER = "<html><head><title>bmt</title></head><body>";

    static String PAGE_CONTENT = "<h1>Bean Managed Transactions</h1>"
            + "<p>Key \"R\" causes rollback after updating the pair<br /><br />Empty key will print out every Pair in database</p>"
    		+ "<form>"
            + "Key: <input type=\"text\" name=\"key\" /><br />"
            + "Value: <input type=\"text\" name=\"value\" /><br />"
            + "<input type=\"submit\" value=\"Submit\" /><br />"
            + "</form>";

    static String PAGE_FOOTER = "</body></html>";

    @Inject
    Transaction transaction;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String responseText;
        String key = req.getParameter("key");
        String value = req.getParameter("value");

        responseText = transaction.updateKeyValueDatabase(key, value);

        writer.println(PAGE_HEADER);
        writer.println(PAGE_CONTENT);
        writer.println("<p>" + responseText + "</p>");
        writer.println(PAGE_FOOTER);

        writer.close();
    }

}
