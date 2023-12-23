package carAccessories;

import java.util.ArrayList;
import java.util.List;



public class ProductCategory {
    private String name;
    private List<Product> products;

    public ProductCategory(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }
	public void addProduct(Product product) {
		products.add(product);
	}
	public List<Product> getProducts() {
    	return products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public List<Product> getAllProducts() {
			return products;
	}

	public void printProducts() {
		int i = 0;
		for (Product product : products) {
			System.out.println(i+". ");
			product.print();
			i++;
		}
		
	}

}
