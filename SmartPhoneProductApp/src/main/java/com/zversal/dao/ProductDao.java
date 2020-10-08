package com.zversal.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import com.zversal.beans.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static com.zversal.application.MainApp.*;

public class ProductDao {

	// DBConnection dbconnection = MainApp.dbconnection;
	// public static Map<String,String> productCache =
	// newTreeMap<String,String>();
	private static ConcurrentHashMap<String, String> productCache = new ConcurrentHashMap<String, String>();

	public boolean authentication(String username, String password) {
		boolean authenticate = false;
		if (username.equals("admin") && password.equals("password")) {
			authenticate = true;
		}
		return authenticate;
	}

	public String addProduct(Product product) {
		try (Connection con = dbconnection.connection();
				PreparedStatement ps = con.prepareStatement("insert into products values(?,?,?,?)");) {
			ps.setString(1, product.getId());
			ps.setString(2, product.getName());
			ps.setInt(3, product.getQuantity());
			ps.setString(4, product.getInAvailable());
			System.out.println(ps.executeUpdate());
			productCache.put(product.getId(), product.getName());
			System.out.println(ProductDao.productCache);
			return "Product added";
		} catch (SQLException e) {
			return "error.....check if id already exist or check input again...";
		}
	}

	public String editProduct(Product pd) {
		try (Connection con = dbconnection.connection();
				PreparedStatement ps = con
						.prepareStatement("update products set Name=?,Quantity=?,isAlive=? where Id=?");) {
			ps.setString(1, pd.getName());
			ps.setInt(2, pd.getQuantity());
			ps.setString(3, pd.getInAvailable());
			ps.setString(4, pd.getId());
			System.out.println(ps.executeUpdate());
			productCache.replace(pd.getId(), pd.getName());
			System.out.println(ProductDao.productCache);
			return "Product updated";
		} catch (SQLException e) {
			return "No such id exist to update";
		}
	}

	public Product viewProduct(String id) {
		Product product = new Product();
		try (Connection con = dbconnection.connection();
				PreparedStatement preparedstatement = con.prepareStatement("select *from products where Id=?");) {
			preparedstatement.setString(1, id);
			try (ResultSet rs = preparedstatement.executeQuery()) {
				if (rs.next()) {
					product.setId(rs.getString(1));
					product.setName(rs.getString(2));
					product.setQuantity(rs.getInt(3));
					product.setInAvailable(rs.getString(4));
				}
			}
		} catch (SQLException e) {

		}
		return product;
	}

	public ArrayList<Product> listProduct() {
		ArrayList<Product> listproduct = new ArrayList<Product>();
		try (Connection con = dbconnection.connection();
				PreparedStatement ps = con.prepareStatement("select *from products");
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getString(1));
				product.setName(rs.getString(2));
				product.setQuantity(rs.getInt(3));
				product.setInAvailable(rs.getString(4));
				listproduct.add(product);
			}
		} catch (SQLException e) {
		}
		return listproduct;
	}

	public ArrayList<Product> nameProduct(String name) {
		ArrayList<Product> productlist = new ArrayList<Product>();
		try (Connection con = dbconnection.connection();
				PreparedStatement ps = con.prepareStatement("select *from products where Name=?");) {
			ps.setString(1, name);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getString(1));
					product.setName(rs.getString(2));
					product.setQuantity(rs.getInt(3));
					product.setInAvailable(rs.getString(4));
					productlist.add(product);
				}
			}
		} catch (SQLException e) {
		}
		return productlist;
	}

	public String deleteProduct(String id) {
		try (Connection con = dbconnection.connection();
				PreparedStatement ps = con.prepareStatement("delete from products where Id=?");) {
			ps.setString(1, id);
			System.out.println(ps.executeUpdate());
			productCache.remove(id);
			System.out.println(ProductDao.productCache);
			return "data deleted successfully";
		} catch (SQLException e) {
			return "some error occured";
		}
	}
}
