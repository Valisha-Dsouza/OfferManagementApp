package com.service;

import java.sql.SQLException;
import java.util.Scanner;

import com.dao.OfferDao;
import com.dao.ProductDao;
import com.entity.Offer;
import com.entity.Product;

public class SalesService {

	ProductDao productDao = new ProductDao();
	OfferDao offerDao = new OfferDao();

	public void getFinalCost(String productCode) {
		String offerCode = "OFFER2";
		if (productCode.equalsIgnoreCase("PRODUCT1"))
			offerCode = "OFFER1";
		try {
			Product product = productDao.selectById(productCode);
			Offer offer = offerDao.selectById(offerCode);
			if (offer != null && offer.getStartTime().before(product.getCreatedOn())
					&& offer.getEndTime().after(product.getCreatedOn())) {
				if (offer.getDiscountType().equals("%")) {
					System.out.println("Discount applicable on " + productCode + " is " + offer.getDiscount() + "%");
				} else {
					System.out.println("Discount applicable on " + productCode + " is Rs " + offer.getDiscount());
				}
			} else
				System.out.println("No discount available");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Sales Service generated exception");
		}

	}

	public static void main(String[] args) {
		SalesService salesService = new SalesService();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the product code");
		String productCode = scanner.nextLine();
		salesService.getFinalCost(productCode);
	}
}
