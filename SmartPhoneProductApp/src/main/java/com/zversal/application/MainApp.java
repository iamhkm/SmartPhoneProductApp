package com.zversal.application;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.internalServerError;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import java.util.Map;
import java.util.TreeMap;
import com.google.gson.Gson;
import com.zversal.connection.DBConnection;
import com.zversal.controller.ProductController;

public class MainApp {
	public static Gson gson = new Gson();
	public static DBConnection dbconnection = new DBConnection();
	
	public static void main(String[] args) {
		port(8080);
		internalServerError((req, res) -> {
			res.type("application/json");
			res.status(500);
			Map<String, String> map = new TreeMap<String, String>();
			map.put("msg:", "bad request");
			return gson.toJson(map);
		});

		get("/hello", ProductController.test, gson::toJson);
		post("/login", ProductController.login, gson::toJson);
		post("/logout", ProductController.logout, gson::toJson);

		after("/*", (request, response) -> {
			response.type("application/json");
		});

		path("/product", () -> {

			before("/*", (request, response) -> {
				boolean authenticated = false;
				if (request.session().attributes().size() > 0) {
					authenticated = true;
				}

				if (!authenticated) {
					Map<String, String> map = new TreeMap<String, String>();
					map.put("msg:", "you need to login first");
					response.type("application/json");
					halt(401, gson.toJson(map));
				}
			});

			after("/*", (request, response) -> {
				response.type("application/json");
			});

			get("/id/:id", ProductController.getById, gson::toJson);
			get("/name/:name", ProductController.getByName, gson::toJson);
			get("/all", ProductController.getAll, gson::toJson);
			delete("/delete/:id", ProductController.deleteProduct, gson::toJson);
			post("/add", ProductController.addProduct, gson::toJson);
			put("/update", ProductController.updateProduct, gson::toJson);

		});
	}
}
