package com.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.dao.OfferDao;
import com.entity.Offer;
import com.entity.Product;

public class OfferService {
	public static void main(String[] args) {
		OfferDao offerDao = new OfferDao();
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		int option;
		try {
			do {
				System.out.println(
						"****MENU*****\n1:View offers\n2:Insert offer\n3:Delete offer\n4:Update offer\n5:exit");
				option = Integer.parseInt(scanner.nextLine());
				String discountType, code;
				float discount;
				String created, expiry;
				switch (option) {
				case 1:
					for (Offer offer : offerDao.selectAll()) {
						System.out.println(offer.getCode() + "\t" + offer.getStartTime() + "\t" + offer.getEndTime()
								+ "\t" + offer.getDiscount() + "\t" + offer.getDiscountType());
					}
					break;
				case 2:
					System.out.println("Enter the offer code");
					code = scanner.nextLine();
					System.out.println("Enter the offer start date in yyyy-mm-dd");
					created = scanner.nextLine();
					System.out.println("Enter the offer expiry date in yyyy-mm-dd ");
					expiry = scanner.nextLine();
					System.out.println("Enter the discount amount");
					discount = Float.parseFloat(scanner.nextLine());
					System.out.println("Enter the discount type");
					discountType = scanner.nextLine();
					offerDao.insert(new Offer(code, simpleDateFormat.parse(created), simpleDateFormat.parse(expiry),
							discount, discountType));
					break;
				case 3:
					System.out.println("Enter the offer code to delete");
					offerDao.delete(scanner.nextLine());
					break;
				case 4:
					System.out.println("Enter the code of the offer that needs to be updated");
					code = scanner.nextLine();
					System.out.println("Enter the updated offer start date in yyyy-mm-dd");
					created = scanner.nextLine();
					System.out.println("Enter the updated offer expiry date in yyyy-mm-dd");
					expiry = scanner.nextLine();
					System.out.println("Enter the updated discount amount");
					discount = Float.parseFloat(scanner.nextLine());
					System.out.println("Enter the updated discount type");
					discountType = scanner.nextLine();
					offerDao.update(new Offer(code, simpleDateFormat.parse(created), simpleDateFormat.parse(expiry),
							discount, discountType));
					break;
				case 5:
				default:
					System.out.println("Exiting");
				}
			} while (option < 5);
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			System.out.println("Exception in implementing offer based services");
		}
	}
}
