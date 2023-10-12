package com.cbt.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.cbt.model.ProductItem;
import com.cbt.model.Purchase;

public class Main {
	private HashMap<String, ProductItem> productList ;
	private static List<Purchase> purchases;
	
	public Main() {
		this.productList = new HashMap<>();
		purchases = new ArrayList<Purchase>();
	}
	
	public static void main(String[] args) {

		Main main = new Main();

		ProductItem p1 = new ProductItem("A", 50, true, 3, 130);
		ProductItem p2 = new ProductItem("B", 30, true, 2, 45);
		ProductItem p3 = new ProductItem("C", 20, false, 0, 0);
		ProductItem p4 = new ProductItem("D", 15, false, 0, 0);

		main.productList.put("A", p1);
		main.productList.put("B", p2);
		main.productList.put("C", p3);
		main.productList.put("D", p4);
		
		System.out.println("Following are the available products with us.\n");
		main.productList.entrySet()
		.forEach(e-> System.out.println(e.toString()));
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\nPlease enter the product item code.\n");
		
		while (sc.hasNext()) {
			String val = sc.next();
			
			if(!val.equalsIgnoreCase("q")) {
				main.buyTransaction(val);
				System.out.println("Item added successfully. \n");
			}else {
				System.out.println("\nThank you for shopping with us.\n");
				main.displayResult(main.productList);
			}
			 
		}
		
		sc.close();

	}

	public void displayResult(HashMap<String, ProductItem> products) {
		System.out.println("Final total after discount: "+purchases.stream()
		.collect(Collectors.groupingBy(Purchase::getProduct,Collectors.counting()))
		.entrySet()
		.stream()
		.map(e->{
			int regularPrice = 0,offerPrice = 0, offerQuantity = 0;
			if(products.containsKey(e.getKey())) {
				
				ProductItem p = products.get(e.getKey());
				 
				if(p.isHasSpecialOffer()) {
					regularPrice  = (e.getValue().intValue()% p.getSpecialUnit())*p.getUnitPrice();
					offerQuantity =  (e.getValue().intValue() / p.getSpecialUnit()) * p.getSpecialUnit();
					offerPrice =  (offerQuantity /p.getSpecialUnit()) * p.getSpecialUnitPrice();
				}else {
					regularPrice  = (e.getValue().intValue())*p.getUnitPrice();
				}
			}
			return regularPrice + offerPrice;
		}).reduce(0, (subtotal, element) -> subtotal + element));
		
	}
	
	public void currentBuying() {
		System.out.println("Current total: "+ purchases.stream()
				.collect(Collectors.groupingBy(Purchase::getProduct, Collectors.summingInt(Purchase::getAmount)))
				.entrySet().stream().map(e -> {
					return e.getValue();
				}).reduce(0, (subtotal, element) -> subtotal + element));

	}
	
	
	
	public int buyTransaction( String product) {
		int regularPrice = 0,offerPrice = 0, result = 0 ;
		
		Purchase purchase = new Purchase();
		purchase.setProduct(product);
		
		if(productList.containsKey(purchase.getProduct())) {
			ProductItem p = productList.get(purchase.getProduct());
			 
			regularPrice  = (1)*p.getUnitPrice();
			
			
			result = regularPrice + offerPrice;
			
			purchase.setAmount(result);
			
			purchases.add(purchase);
			
			currentBuying();
			
		}
		return result;
	}

}
