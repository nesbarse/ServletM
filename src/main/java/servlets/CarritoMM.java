/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author a022595832b
 */
public class CarritoMM extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        HttpSession Session = null;
        try (PrintWriter out = response.getWriter()) {
            
            Map<String, Integer> carr = new HashMap<String, Integer>();
            

            if ("login".equalsIgnoreCase(request.getParameter("op"))) {
                if ("hola".equalsIgnoreCase(request.getParameter("password"))) {
                    Session = request.getSession();
                    String usuario = request.getParameter("login");
                    Session.setAttribute("UserName", usuario);
                    request.getSession().setAttribute("carr", carr);
                }
            }

            if ("logout".equalsIgnoreCase(request.getParameter("op"))) {
                Session = request.getSession();
                Session.invalidate();
                out.print("{\"status\":200,\"message\":\"Sesion cerrada\"}");
            }

            if ("list".equalsIgnoreCase(request.getParameter("op"))) {
                Map<String, Integer> carr2 = (Map)request.getSession().getAttribute("carr");
                out.print("{");
                String res = "";
                for (String name : carr2.keySet()) {

                    String key = name.toString();
                    String value = carr2.get(name).toString();
                    res +="\"" + key + "\":" + value + ",";

                }
                res = res.substring(0, res.length()-1);
                out.print(res);
                out.print("}");
            }

            if ("add".equalsIgnoreCase(request.getParameter("op"))) {
                String nombre = request.getParameter("nombre");
                Integer amount = Integer.parseInt(request.getParameter("amount")); 
                
                Map<String, Integer> carr2 = (Map)request.getSession().getAttribute("carr");
                carr2.put(nombre, amount);
                request.getSession().setAttribute("carr", carr2);                
                
                if (!carr2.isEmpty()) {
                    out.print("{\"status\":200,\"message\":\"Item added\"}");
                } else {
                    out.print("{\"status\":500,\"message\":\"error\"}");
                }

            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
