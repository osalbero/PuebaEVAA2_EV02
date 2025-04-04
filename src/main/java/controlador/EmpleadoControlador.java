/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Empleado;
import modelo.dao.EmpleadoDAO;

public class EmpleadoControlador extends HttpServlet {

    private EmpleadoDAO empDao = new EmpleadoDAO();
    private final String pagListar = "/vista/listar.jsp";
    private final String pagNuevo = "/vista/nuevo.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");

        if (accion == null || accion.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'accion' es requerido.");
            return;
        }

        switch (accion) {
            case "listar":
                listar(request, response);
                break;
            case "nuevo":
                nuevo(request, response);
                break;
            case "guardar":
                guardar(request, response);
                break;
            case "editar":
                editar(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Empleado obj = empDao.buscarPorId(id);
        int result = empDao.eliminar(id);

        if (result > 0) {
            // Verificar si el empleado no es null para evitar errores
            String nombreEmpleado = (obj != null) ? obj.getNombre() + " " + obj.getApellido() : "Desconocido";
            request.getSession().setAttribute("success", "Empleado " + nombreEmpleado + " eliminado!");
        } else {
            request.getSession().setAttribute("error", "No se pudo eliminar empleado");
        }
        response.sendRedirect("EmpleadoControlador?accion=listar");
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Empleado obj = empDao.buscarPorId(id);

        if (obj != null) {
            request.setAttribute("empleados", obj);
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        } else {
            request.getSession().setAttribute("error", "No se encontro empleado con ID" + id);
            response.sendRedirect("EmpleadoControlador?accion=listar");
        }
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Empleado obj = new Empleado();
        obj.setId(Integer.parseInt(request.getParameter("id")));
        obj.setNombre(request.getParameter("nombre"));
        obj.setApellido(request.getParameter("apellido"));
        obj.setTelefono(request.getParameter("telefono"));

        int result;

        if (obj.getId() == 0) {
            result = empDao.registrar(obj);
        } else {
            result = empDao.editar(obj);
        }

        if (result > 0) {
            request.getSession().setAttribute("success", "Datos Guardados!");
            response.sendRedirect("EmpleadoControlador?accion=listar");
        } else {
            request.getSession().setAttribute("error", "No se pudo guardar datos");
            request.setAttribute("empleados", obj);
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        }
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("empleados", new Empleado());
        request.getRequestDispatcher(pagNuevo).forward(request, response);
    }

    protected void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("empleados", empDao.ListarTodos());
        request.getRequestDispatcher(pagListar).forward(request, response);
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
