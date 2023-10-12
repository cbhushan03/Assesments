package com.cbt.model;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductItem {
	String name;
	int unitPrice;
	boolean hasSpecialOffer;
	int specialUnit;
	int specialUnitPrice;
}
