package service.Impl;

import model.Product;
import service.ProductService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    public ProductServiceImpl() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo2006?useSSL=false", "root", "123456");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void add(Product product) throws SQLException {

    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product");) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = Integer.parseInt(rs.getString("price"));
                int quantity = rs.getInt("quantity");
                products.add(new Product(id, name, price, quantity));
            }
        } catch (SQLException e) {
        }
        return products;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product where name like ?");) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                name = rs.getString("name");
                int price = Integer.parseInt(rs.getString("price"));
                int quantity = rs.getInt("quantity");
                products.add(new Product(id, name, price, quantity));
            }
        } catch (SQLException e) {
        }
        return products;
    }

    @Override
    public List<Product> findAllOrderByAge() {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        return false;
    }

    @Override
    public List<Product> findByPrice(int start, int end) {
        List<Product> products = new ArrayList<>();
        String query = "select * from product where price between ? and ?;";
        try (Connection conn = getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, String.valueOf(start));
            preparedStatement.setString(2, String.valueOf(end));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameFind = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                products.add(new Product(id, nameFind, price, quantity));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}

