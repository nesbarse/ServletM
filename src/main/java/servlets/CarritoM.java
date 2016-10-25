/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pojos.Producto;
import pojos.RellenaListaProducto;

/**
 *
 * @author a022595832b
 */
public class CarritoM extends HttpServlet {

//    private Producto find(ArrayList<Producto> alProducto) {
//        Iterator<Producto> iterator = alProducto.iterator();
//        while (iterator.hasNext()) {
//            Producto oProducto = iterator.next();
//            return null;
//        }
//        return null;
//    }
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
        //------------------- Tipo de salida de datos --------------------//
        response.setContentType("application/json");
        //----------------------------------------------------------------//

        //------------------- Declaracion de sesion ----------------------//
        HttpSession Session = null;
        //----------------------------------------------------------------//

        try (PrintWriter out = response.getWriter()) {

            //------------------- Variables de sesion ----------------------//
            ArrayList<Producto> carrito = new ArrayList();
            ArrayList<Producto> existencias = new ArrayList();
            //--------------------------------------------------------------//

            //------------------- Login de la aplicacion ----------------------//
            if ("login".equalsIgnoreCase(request.getParameter("op"))) {
                if ("hola".equalsIgnoreCase(request.getParameter("password"))) {
                    Session = request.getSession();
                    String usuario = request.getParameter("login");
                    Session.setAttribute("UserName", usuario);
                    request.getSession().setAttribute("carrito", carrito);

                    //------------ Llamada al pojo de existencias--------------//
                    existencias = RellenaListaProducto.AProducto();
                    request.getSession().setAttribute("existencias", existencias);
//                    out.print(RellenaListaProducto.AProducto());
                    //---------------------------------------------------------//
                }
            }
            //-----------------------------------------------------------------//

            //------------------- Logout de la sesion ----------------------//
            if ("logout".equalsIgnoreCase(request.getParameter("op"))) {
                Session = request.getSession();
                Session.invalidate();
                out.print("{\"status\":200,\"message\":\"Sesion cerrada\"}");
            }
            //-------------------------------------------------------------//

            //------------------- Listado de productos ----------------------//
            if ("list".equalsIgnoreCase(request.getParameter("op"))) {
                if ("product".equalsIgnoreCase(request.getParameter("ob"))) {
                    Session = request.getSession();

//                    out.print(RellenaListaProducto.AProducto().toString());
//                    out.print("hooola");
                    ArrayList<Producto> existencias2 = (ArrayList) request.getSession().getAttribute("existencias");
//                    out.print(existencias2.toString());
                    Gson oGson = new GsonBuilder().create();
                    out.print(oGson.toJson(existencias2));
                }
            }
            //--------------------------------------------------------------//

            //------------------- Listado del carrito ----------------------//
            if ("list".equalsIgnoreCase(request.getParameter("op"))) {
                if ("cart".equalsIgnoreCase(request.getParameter("ob"))) {
                    ArrayList<Producto> carrito2 = (ArrayList) request.getSession().getAttribute("carrito");

                    Gson oGson = new GsonBuilder().create();
                    out.print(oGson.toJson(carrito2));
                }
            }
            //--------------------------------------------------------------//

            //------------------- Checkout del carrito ---------------------//
            if ("checkout".equalsIgnoreCase(request.getParameter("op"))) {
                Producto temp = new Producto();
                Producto temp2 = new Producto();
                ArrayList<Producto> existencias3 = (ArrayList) request.getSession().getAttribute("existencias");
                ArrayList<Producto> carrito3 = (ArrayList) request.getSession().getAttribute("carrito");

                for (int j = 0; j < carrito3.size(); j++) {
                    temp2 = carrito3.get(j);
                    for (int i = 0; i < existencias3.size(); i++) {
                        temp = existencias3.get(i);
                        if (temp2.getNombre().equalsIgnoreCase(temp.getNombre())) {
                            if (temp.getAmount() < temp2.getAmount()) {

                                out.print("No hay suficientes existencias");

                            } else {
                                Integer resta = temp.getAmount() - temp2.getAmount();
                                temp.setAmount(resta);
                            }
                        }
                    }
                }

                for (int k = 0; k < carrito3.size(); k++) {
                    carrito3.remove(k);
                }

                request.getSession().setAttribute("existencias", existencias3);
                request.getSession().setAttribute("carrito", carrito3);

            }
            //--------------------------------------------------------------//

            //--------------- Añadir un producto al carrito ----------------//
            if ("add".equalsIgnoreCase(request.getParameter("op"))) {
                if ("cart".equalsIgnoreCase(request.getParameter("ob"))) {
                    String nombre = request.getParameter("nombre");
                    Integer amount = Integer.parseInt(request.getParameter("amount"));
                    Producto oProducto = new Producto();

                    ArrayList<Producto> carrito2 = (ArrayList) request.getSession().getAttribute("carrito");

                    oProducto.setNombre(nombre);
                    oProducto.setAmount(amount);

                    carrito2.add(oProducto);

                    request.getSession().setAttribute("carrito", carrito2);

                    if (!carrito2.isEmpty()) {
                        out.print("{\"status\":200,\"message\":\"Item added\"}");
                    } else {
                        out.print("{\"status\":500,\"message\":\"error\"}");
                    }
                }
            }
            //----------------------------------------------------------------//

            //--------------- Eliminar objeto del carrito --------------------//
            if ("drop".equalsIgnoreCase(request.getParameter("op"))) {
                if ("cart".equalsIgnoreCase(request.getParameter("ob"))) {
                    String nombre = request.getParameter("nombre");
                    ArrayList<Producto> carrito3 = (ArrayList) request.getSession().getAttribute("carrito");
                    Producto oProducto = new Producto();

                    for (int i = 0; i < carrito3.size(); i++) {
                        oProducto = carrito3.get(i);
                        if (oProducto.getNombre().equalsIgnoreCase(nombre)) {
                            carrito3.remove(i);
                        }
                    }

                    request.getSession().setAttribute("carrito", carrito3);
                }
            }
            //----------------------------------------------------------------//

            //-------------------- Vaciar el carrito -------------------------//
            if ("empty".equalsIgnoreCase(request.getParameter("op"))) {
                if ("cart".equalsIgnoreCase(request.getParameter("ob"))) {
                    ArrayList<Producto> carrito3 = (ArrayList) request.getSession().getAttribute("carrito");

                    for (int k = 0; k < carrito3.size(); k++) {
                        carrito3.remove(k);
                    }
                    
                    request.getSession().setAttribute("carrito", carrito3);
                }
            }
            //----------------------------------------------------------------//
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
