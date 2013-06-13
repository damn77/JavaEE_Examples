package cmt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.MoviesTest;

@WebServlet("/CMT")
public class TransactionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static String PAGE_HEADER = "<html><head><title>CMT</title></head><body>";

    static String PAGE_CONTENT = "<h1>Container Managed Transactions</h1>"
            + "<p>Pick test transaction attribute and whether method will be run inside or outside of transaction.</p>"
    		+ "<form>"
    		+"<input checked type=\"radio\" name=\"attribute\" value=\"M\">Mandatory"
    		+"<input type=\"radio\" name=\"attribute\" value=\"S\">Supported"
    		+"<input type=\"radio\" name=\"attribute\" value=\"R\">Required<br>"
    		+"<input checked type=\"radio\" name=\"transaction\" value=\"T\">Inside of Transaction"
    		+"<input type=\"radio\" name=\"transaction\" value=\"F\">Outside of Transaction<br>"
            + "<input type=\"submit\" value=\"Submit\" /><br />"
            + "</form>";

    static String PAGE_FOOTER = "</body></html>";
    
    @Inject
    MoviesTest test;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String responseText = "Aspon to funguje";
        String attr = req.getParameter("attribute");
        String trans = req.getParameter("transaction");

        if (trans!=null)
	        if (trans.equals("T"))
		        try {
					responseText = test.testWithTransaction(attr.toCharArray()[0]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					responseText = "Exception in test with transaction!";
				}
	        else 
	        	try {
					responseText = test.testWithoutTransaction(attr.toCharArray()[0]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					responseText = "Exception in test without transaction!";
				}
        
        writer.println(PAGE_HEADER);
        writer.println(PAGE_CONTENT);
        writer.println("<p>" + responseText + "</p>");
        writer.println(PAGE_FOOTER);

        writer.close();
    }

}
