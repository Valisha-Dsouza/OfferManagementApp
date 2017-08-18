package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.entity.Offer;
import com.repository.MySqlJdbcRepository;

public class OfferDao {
	Logger offerDaoLogger = Logger.getLogger(OfferDao.class);
	public void create() throws SQLException, ClassNotFoundException {
		try (Connection connection = MySqlJdbcRepository.getConnection();
				Statement statement = connection.createStatement()) {
			statement.execute(
					"create table offers ( code varchar(20), startDate timestamp, endDate date , discount float , type_of_discount varchar(5), primary key(code))");
		}
	}

	public void insert(Offer offers) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO offers (code,startDate,endDate,discount,type_of_discount) VALUES (?,?,?,?,?)";
		try (Connection connection = MySqlJdbcRepository.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, offers.getCode());
			preparedStatement.setDate(2, new java.sql.Date(offers.getStartTime().getTime()));
			preparedStatement.setDate(3, new java.sql.Date(offers.getEndTime().getTime()));
			preparedStatement.setFloat(4, offers.getDiscount());
			preparedStatement.setString(5, offers.getDiscountType());
			preparedStatement.executeUpdate();
			System.out.println("Offer inserted successfully");
		}
	}

	public Offer selectById(String code) throws SQLException, ClassNotFoundException {
		Offer offers = null;
		String sql = "SELECT * FROM offers WHERE code = ?";
		try (Connection connection = MySqlJdbcRepository.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, code);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					offers = new Offer(resultSet.getString("code"), resultSet.getDate("startDate"),
							resultSet.getDate("endDate"), resultSet.getFloat("discount"),
							resultSet.getString("type_of_discount"));
				}
			}
		}
		return offers;
	}

	public List<Offer> selectAll() throws SQLException, ClassNotFoundException {
		List<Offer> offers = new ArrayList<Offer>();
		String sql = "SELECT * FROM offers";
		try (Connection connection = MySqlJdbcRepository.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {
			while (resultSet.next()) {
				offers.add(new Offer(resultSet.getString("code"), resultSet.getDate("startDate"),
						resultSet.getDate("endDate"), resultSet.getFloat("discount"),
						resultSet.getString("type_of_discount")));
			}
		}
		return offers;
	}

	public void delete(String code) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM offers WHERE code = ?";
		try (Connection connection = MySqlJdbcRepository.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, code);
			preparedStatement.executeUpdate();
			System.out.println("Offer deleted successfully");
		}
	}

	public void update(Offer offers) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE offers SET startDate = ?,endDate=?,discount=?,type_of_discount=? WHERE code = ?";
		try (Connection connection = MySqlJdbcRepository.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(5, offers.getCode());
			preparedStatement.setDate(1, new java.sql.Date(offers.getStartTime().getTime()));
			preparedStatement.setDate(2, new java.sql.Date(offers.getEndTime().getTime()));
			preparedStatement.setFloat(3, offers.getDiscount());
			preparedStatement.setString(4, offers.getDiscountType());
			preparedStatement.executeUpdate();
			System.out.println("Offer updated successfully");
		}
	}
}
