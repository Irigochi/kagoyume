/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kouta
 */
public class Cart extends HttpServlet {

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
        //セッションスタート
        HttpSession session = request.getSession();
        
        try{
            request.setCharacterEncoding("UTF-8");
            
            //ログインしていなければログインページに遷移                     
            if(session.getAttribute("login") == null){
                session.setAttribute("cartLogin", 0);
                response.sendRedirect("Login");
                
            }else{               
                UserDataDTO udd = (UserDataDTO)session.getAttribute("udd");
                
                if(request.getParameter("cartDelete") != null){
                    session.removeAttribute(udd.getStrID());
                }
                       
                //セッションでカート情報を保持しているか確認し、保持していた時の処理
                if(session.getAttribute(udd.getStrID()) != null){
                    ArrayList<String> cart = (ArrayList)session.getAttribute(udd.getStrID());
                
                    //ログイン前にカートに商品を追加していればそれを反映
                    if(session.getAttribute("0") != null){
                        ArrayList<String> guestCart = (ArrayList)session.getAttribute("0");
                        for(int i = 0; i < guestCart.size(); i++){
                            cart.add(guestCart.get(i));
                        }
                    }
                
                    //カートが空でないことを確認
                    if(cart.size() > 0){
                        CartDataBeans cdb = new CartDataBeans();
                        for(int i = 0; i < cart.size(); i++){
                            cdb = cdb.getItemByCode(cart.get(i));
                        }
                        session.setAttribute("cdb", cdb);
                        session.removeAttribute("0");
                        request.getRequestDispatcher("/cart.jsp").forward(request, response);
                    
                    //カートが空の時の処理    
                    }else{                   
                        request.setAttribute("cartEmpty", 0);
                        request.getRequestDispatcher("/cart.jsp").forward(request, response);
                    }
                
                //セッションにカートの情報がない時の処理    
                }else{
                    //ログイン前にカートに商品を追加していればそれを反映
                    if(session.getAttribute("0") != null){
                        ArrayList<String> cart = (ArrayList)session.getAttribute("0");
                        if(cart.size() > 0){
                            CartDataBeans cdb = new CartDataBeans();
                            for(int i = 0; i < cart.size(); i++){
                                cdb = cdb.getItemByCode(cart.get(i));
                            }
                            session.setAttribute("cdb", cdb);
                            session.setAttribute(udd.getStrID(), cart);
                            session.removeAttribute("0");
                            request.getRequestDispatcher("/cart.jsp").forward(request, response);
                        }
                    }else{
                    request.setAttribute("cartEmpty", 0);
                    request.getRequestDispatcher("/cart.jsp").forward(request, response);
                    }
                }
            }
        }catch(Exception e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
