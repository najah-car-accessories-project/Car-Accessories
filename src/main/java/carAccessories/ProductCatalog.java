package carAccessories;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
    private List<ProductCategory> categories;
    
    public ProductCatalog() {
        this.categories = new ArrayList<>();
    }


	void addCategory(ProductCategory category) {
		categories.add(category);
	}

    public void removeCategory(ProductCategory categoryToRemove) {        
        for (int i = 0; i < categories.size(); i++) {
	        if (categoryToRemove.getName().equalsIgnoreCase(categories.get(i).getName())) {
	            categories.remove(i);
	            break;
	        }
	    }
    }


    public boolean containsCategory(ProductCategory category) {
        for (ProductCategory existingCategory : categories) {
            if (existingCategory.getName().equals(category.getName())) {
                return true;
            }
        }
        return false;
    }

	public void updateCategory(ProductCategory updatedCategory) {
	    for (int i = 0; i < categories.size(); i++) {
	        if (updatedCategory.getName().equals(categories.get(i).getName())) {
	            categories.set(i, updatedCategory);
	            break;
	        }
	    }
	}

	public List<ProductCategory> getAllCategories() {
		return categories;
	}

	public void addProductToCategory(Product newProduct, String category) {
		
		for (ProductCategory productCategory : categories) {
			if (productCategory.getName().equalsIgnoreCase(category)) {
				productCategory.addProduct(newProduct);
			}
		}
	}

	public void printCategories() {
		int i = 0;
		for (ProductCategory category : categories) {
			System.out.println(i+  ". " +category.getName());
			i++;

		}
		
	}

	public void printProducts() {
		int i = 0;
		for (ProductCategory category : categories) {
			System.out.println(i + ". " + category.getName());
			System.out.println("---------------");
			category.printProducts();
			i++;
		}
		
	}

	void printCatalog() {
		
		System.out.println("=======================================");
		System.out.println("Categorys");
		System.out.println("=======================================");
		for (ProductCategory category : categories) {
			System.out.println(category.getName());
			category.printProducts();
			System.out.println("=======================================");
		}

	}


	public List<Product> searchProducts(String searchKey) {
		List<Product> products = new ArrayList<>();
		for (ProductCategory productCategory : categories) {
			for (Product product : productCategory.getProducts()) {
				if (product.getName().contains(searchKey) || product.getDescriptions().contains(searchKey)) {
					products.add(product);
				}
			}
		}
		return products;
	}


	public List<Product>  getAllProducts() {
		
		List<Product> products = new ArrayList<>();
		for (ProductCategory productCategory : categories) {
			for (Product product : productCategory.getProducts()) {
				products.add(product);
			}
		}
		return products;

	}

	
	



		
	


}
