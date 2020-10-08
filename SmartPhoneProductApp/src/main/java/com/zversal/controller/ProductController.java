package com.zversal.controller;

import static spark.Spark.halt;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import com.zversal.beans.Product;
import com.zversal.dao.ProductDao;
import spark.Request;
import spark.Response;
import spark.Route;
import static com.zversal.application.MainApp.*;

public class ProductController {

	private static ProductDao productDao = new ProductDao();
	public static Route test = (Request req, Response res) -> {
		res.status(100);
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		map.put("msg:", "Hello World");
		return map;
	};

	public static Route login = (Request req, Response res) -> {
		String username = req.queryParams("username");
		String password = req.queryParams("pass");
		Map<String, String> map = new TreeMap<String, String>();
		// using session for session management
		if (productDao.authentication(username, password)) {
			req.session().attribute(username, password);
			map.put("msg:", "login success");
		}

		else {
			res.type("application/json");
			map.put("msg:", "Invalid Credential");
			halt(401, gson.toJson(map));
		}
		/*
		 * using cookies for session management
		 * response.cookie(username,password);
		 */

		return map;
	};

	public static Route logout = (Request req, Response res) -> {
		String username = req.queryParams("username");
		Map<String, String> map = new TreeMap<String, String>();
		// using session for logout
		req.session().removeAttribute(username);
		res.status(200);
		map.put("msg:", "logout successfull");
		/*
		 * using cookies for logout response.removeCookie(username);
		 */
		return map;
	};

	public static Route getById = (Request req, Response res) -> {
		Map<String, Object> map = new TreeMap<String, Object>();
		res.status(200);
		map.put("data:", productDao.viewProduct(req.params(":id")));
		return map;
	};

	public static Route getByName = (Request req, Response res) -> {
		Map<String, Object> map = new TreeMap<String, Object>();
		res.status(200);
		map.put("data:", productDao.nameProduct(req.params(":name")));
		return map;
	};

	public static Route getAll = (Request req, Response res) -> {
		Map<String, Object> map = new TreeMap<String, Object>();
		res.status(200);
		map.put("data:", productDao.listProduct());
		return map;
	};

	public static Route deleteProduct = (Request req, Response res) -> {
		Map<String, Object> map = new TreeMap<String, Object>();
		res.status(200);
		map.put("data:", productDao.deleteProduct(req.params(":id")));
		return map;
	};

	public static Route addProduct = (Request req, Response res) -> {
		Map<String, Object> map = new TreeMap<String, Object>();
		Product user = gson.fromJson(req.body(), Product.class);

		/*
		 * Product pd1 = new Product(); pd1.setId(req.queryParams("id"));
		 * pd1.setName(req.queryParams("name"));
		 * pd1.setQuantity(Integer.parseInt(req.queryParams("quantity")));
		 * pd1.setInAvailable(req.queryParams("isLive"));
		 */
		res.status(200);
		// map.put("msg:", pd.addProduct(pd1));
		map.put("msg:", productDao.addProduct(user));
		return map;
	};

	public static Route updateProduct = (Request req, Response res) -> {
		Map<String, Object> map = new TreeMap<String, Object>();
		Product user = gson.fromJson(req.body(), Product.class);

		/*
		 * Product pd1 = new Product(); pd1.setId(req.queryParams("id"));
		 * pd1.setName(req.queryParams("name"));
		 * pd1.setQuantity(Integer.parseInt(req.queryParams("quantity")));
		 * pd1.setInAvailable(req.queryParams("isLive"));
		 */
		res.status(200);
		map.put("msg:", productDao.editProduct(user));
		return map;
	};
}
