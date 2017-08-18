package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.entity.Product;
import com.repository.MySqlJdbcRepository;

public class ProductDao {

	public void create() throws SQLException, ClassNotFoundException {
		try (Connection connection = MySqlJdbcRepository.getConnection();
				Statement statement = connection.createStatement()) {
			statement.execute("CREATE TABLE IF NOT EXISTS product (code varchar(60),"
					+ "name varchar(55), stock int,createdOn timestamp, primary key(code)");
		}
	}

	public void insert(Product product) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO product (code,name,stock,createdOn) VALUES (?,?,?,?)";
		try (Connection connection = MySqlJdbcRepository.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, product.getCode());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setInt(3, product.getStock());
			preparedStatement.setDate(4, new java.sql.Date(product.getCreatedOn().getTime()));
			preparedStatement.executeUpdate();
			System.out.println("Product inserted successfully");
		}
	}

	public Product selectById(String code) throws SQLException, ClassNotFoundException {
		Product product = null;
		String sql = "SELECT * FROM product WHERE code = ?";
		try (Connection connection = MySqlJdbcRepository.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, code);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					product = new Product(resultSet.getString("code"), resultSet.getString("name"),
							resultSet.getInt("stock"), resultSet.getDate("createdOn"));
				}
			}
		}
		return product;
	}

	public List<Product> selectAll() throws SQLException, ClassNotFoundException {
		List<Product> products = new ArrayList<Product>();
		String sql = "SELECT * FROM product";
		try (Connection connection = MySqlJdbcRepository.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);) {
			while (resultSet.next()) {
				products.add(new Product(resultSet.getString("code"), resultSet.getString("name"),
						resultSet.getInt("stock"), resultSet.getDate("createdOn")));
			}
		}
		return products;
	}

	public void delete(String code) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM product WHERE code = ?";
		try (Connection connection = MySqlJdbcRepository.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, code);
			preparedStatement.executeUpdate();
			System.out.println("Product Deleted succesfully");
		}
	}

	public void update(Product product) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE product SET name = ?,stock=?,createdOn=? WHERE code = ?";
		try (Connection connection = MySqlJdbcRepository.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, product.getName());
			preparedStatement.setInt(2, product.getStock());
			preparedStatement.setDate(3, new java.sql.Date(product.getCreatedOn().getTime()));
			preparedStatement.setString(4, product.getCode());
			preparedStatement.executeUpdate();
			System.out.println("Product updated successfully");
		}
	}
}
