package com.socket.ltd;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class SocketServlet
 */
public class SocketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SocketServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String quantityStr = request.getParameter("quantity");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        
        int quantity = parseQuantityToInt(response, quantityStr);
	
        double pricePerSocket = switch (type) {
	        case "A" -> 10;
	        case "B" -> 20;
	        case "C" -> 30;
	        default -> 0;
        };
        
        double totalPrice = pricePerSocket * quantity;
        
        displayQuoteDetails(response, type, pricePerSocket, name, email, quantity, totalPrice);
	}
	
	private void displayQuoteDetails(HttpServletResponse response, String type, double pricePerSocket, String name, String email, int quantity, double totalPrice) throws IOException {
		response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>Quote Details</h2>");
        response.getWriter().println("Name: " + name + "<br>");
        response.getWriter().println("Email: " + email + "<br>");
        response.getWriter().println("Socket Type: " + type + "<br>");
        response.getWriter().println("Socket Price: $" + pricePerSocket + "<br>");
        response.getWriter().println("Quantity: " + quantity + "<br>");
        response.getWriter().println("Total Price: $" + totalPrice);
        response.getWriter().println("</body></html>");
	}

	private int parseQuantityToInt(HttpServletResponse response, String quantityStr) throws IOException {
		int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid quantity. Please enter a number.");
        }
		return quantity;
	}

}
