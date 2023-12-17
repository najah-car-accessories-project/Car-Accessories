package carAccessories;

import java.util.ArrayList;

public class Product {
    private String name;
    private String descriptions;
    private ArrayList<String> images;
    private double price;
    private boolean isAvailable;

	public Product() {
		this.name = "";
		this.descriptions = "";
	    this.images = new ArrayList<String>();
	     this.price = 0.0;
	     this.isAvailable = false;
	            
	}
 void print() {
	 	System.out.println("Product Name: " + name + " - " + "Product Price: " + price + " - " + "Product Availability: " + (isAvailable ? "In Stock" : "Out Of Stock" ) + " - "  + "Product Description: " + descriptions);
 }

	public Product(String name, String descriptions, ArrayList<String> images, double price,
			boolean isAvailable) {
		this.name = name;
		this.descriptions = descriptions;
		this.images = images;
		this.price = price;
		this.isAvailable = isAvailable;
	}



    public String getDescriptions() {
    	        return descriptions;
    }


    public String getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

	public void setName(String name) {
		this.name = name;
	}
    

    
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
