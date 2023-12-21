package carAccessories;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProductCatalog {
	private List<ProductCategory> categories;
	private static final Logger LOGGER = Logger.getLogger(ProductCatalog.class.getName());
	private static final String HORIZONTAL_HR = "--------------------------------";

	public ProductCatalog() {
		this.categories = new ArrayList<>();
	}

	void addCategory(ProductCategory category) {
		categories.add(category);
	}

<<<<<<< HEAD
=======
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

>>>>>>> 016c0e869197b18dd26b9881abc1421778eb8855
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
			LOGGER.fine(i + ". " + category.getName());
			i++;

		}

	}

	public void printProducts() {
		int i = 0;
		for (ProductCategory category : categories) {
			LOGGER.fine(i + ". " + category.getName());
			LOGGER.fine("---------------");
			category.printProducts();
			i++;
		}

	}

	void printCatalog() {

		LOGGER.fine(HORIZONTAL_HR);
		LOGGER.fine("Categorys");
		LOGGER.fine(HORIZONTAL_HR);
		for (ProductCategory category : categories) {
			LOGGER.fine(category.getName());
			category.printProducts();
			LOGGER.fine(HORIZONTAL_HR);
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

	public List<Product> getAllProducts() {

		List<Product> products = new ArrayList<>();
		for (ProductCategory productCategory : categories) {
			for (Product product : productCategory.getProducts()) {
				products.add(product);
			}
		}
		return products;

	}

}
