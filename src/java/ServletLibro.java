/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Clases.Libro;
import Clases.ClassLibro;
import Clases.ConexionBaseDeDatos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Edwing
 */
@WebServlet(urlPatterns = {"/ServletLibro"})
public class ServletLibro extends HttpServlet {
    Libro libro;
    ClassLibro registroLibro;
    Libro[] librosRegistrados;
    StringBuffer objetoRespuesta = new StringBuffer();
    
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
        response.setContentType("text/html;charset=UTF-8");
       try ( PrintWriter respuesta = response.getWriter()) {            
           
           registroLibro=new ClassLibro();
           String control = request.getParameter("control");
           
           if(control.toUpperCase().equals("GUARDAR")){
               libro = new Libro(
                Integer.parseInt(request.getParameter("codigo")),
                request.getParameter("nombre"),
                request.getParameter("pasta"),
                request.getParameter("editorial"),
                request.getParameter("year"),
                Integer.parseInt(request.getParameter("opcion")));                
                registroLibro.guardarLibro2(libro);//almacenarlo en BD                 
           }else if(control.toUpperCase().equals("ELIMINAR")){
               int codigoEliminar= Integer.parseInt(request.getParameter("codigo"));
               registroLibro.eliminarLibro(codigoEliminar);
           }
                        
            
            //registroAlumno.guardarAlumno(alumno);//almacenarlo en el array
            //alumnosRegistrados= registroAlumno.getAlumnos();// consultar alumnos en el array                       
                    
           registroLibro.getLibro2(objetoRespuesta);//consultar alumnos en la BD
           respuesta.write(objetoRespuesta.toString());  
           
           
            for (int i = 0; i < librosRegistrados.length; i++){
                    //if(!librosRegistrados[i].getCodigo().isEmpty()){
                        if(librosRegistrados[i].getCodigo()>0){
                       out.println("<tr><td>" + librosRegistrados[i].getCodigo()+ "</td>");
                       out.println("<td>" + librosRegistrados[i].getNombre() + "</td>");
                       out.println("<td>" + librosRegistrados[i].getPasta()+ "</td>");                  
                       out.println("<td>" + librosRegistrados[i].getEditorial()+ "</td>");
                       out.println("<td>" + librosRegistrados[i].getYear()+ "</td>");
                       out.println("<td>"
                               + "<button type=\"button\" class=\"btn btn-warning\"></i>Editar</button> "
                               + "<button type=\"button\" class=\"btn btn-danger\">Eliminar</button>"
                               + "</td></tr>");
                    }
                }
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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
