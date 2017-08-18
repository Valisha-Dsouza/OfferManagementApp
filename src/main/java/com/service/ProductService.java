package com.service;

import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.dao.ProductDao;
import com.entity.Product;

public class ProductService {

	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		Scanner scanner = new Scanner(System.in);
		int option;
		try {
			do {
				System.out.println(
						"****MENU*****\n1:View products\n2:Insert product\n3:Delete product\n4:Update product\n5:exit");
				option = Integer.parseInt(scanner.nextLine());
				String name, code, created;
				int stockCount;
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				switch (option) {
				case 1:
					for (Product product : productDao.selectAll()) {
						System.out.println(product.getCode() + "\t" + product.getName() + "\t" + product.getStock()
								+ "\t" + product.getCreatedOn());
					}
					break;
				case 2:
					System.out.println("Enter the product name");
					name = scanner.nextLine();
					System.out.println("Enter the product code");
					code = scanner.nextLine();
					System.out.println("Enter the number of stock");
					stockCount = Integer.parseInt(scanner.nextLine());
					System.out.println("Enter the product created date in yyyy-MM-dd");
					created = scanner.nextLine();
					productDao.insert(new Product(code, name, stockCount, simpleDateFormat.parse(created)));
					break;
				case 3:
					System.out.println("Enter the product code to delete");
					code = scanner.nextLine();
					productDao.delete(code);
					break;
				case 4:
					System.out.println("Enter the code of the product that needs to be updated");
					code = scanner.nextLine();
					System.out.println("Enter the updated product name");
					name = scanner.nextLine();
					System.out.println("Enter the updated stock count");
					stockCount = Integer.parseInt(scanner.nextLine());
					System.out.println("Enter the updated created date in yyyy-MM-dd");
					created = scanner.nextLine();
					productDao.update(new Product(code, name, stockCount, simpleDateFormat.parse(created)));
					break;
				case 5:
				default:
					System.out.println("Exiting");
				}
			} while (option < 5);
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			System.out.println("Exception in implementing product based access");
		}
	}
}
