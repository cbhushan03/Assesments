# Assesments
The following main project component classes

# Project Components

## Package com.cbt.model

**Purchase** : To track current purchase.

**ProductItem** : To manage a product category with offers.

## Main Class

The following method is used to apply the discount based on **ProductItem** class elements and display the final amount.

```java
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
```



