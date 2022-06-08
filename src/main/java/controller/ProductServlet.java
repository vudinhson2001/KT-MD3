package controller;

import model.Product;
import service.Impl.ProductServiceImpl;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Servlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                showListProduct(request, response);
        }

    }
    private void showListProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
        List<Product> productList = productService.findAll();
        request.setAttribute("products", productList);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "search":
                search(request,response);
                break;
            case "searchPrice":
                searchPrice(request,response);
                break;
        }
    }

    private void searchPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
        int start = Integer.parseInt(request.getParameter("start"));
        int end = Integer.parseInt(request.getParameter("end"));
        List<Product> products = productService.findByPrice(start, end);
        request.setAttribute("products", products);
        requestDispatcher.forward(request, response);
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
        String name = request.getParameter("name");
        List<Product> products = productService.findByName(name);
        request.setAttribute("products", products);
        requestDispatcher.forward(request, response);
    }

}
