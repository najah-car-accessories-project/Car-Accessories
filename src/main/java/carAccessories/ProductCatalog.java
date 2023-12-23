package carAccessories;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ProductCatalog {
	private List<ProductCategory> categories;
	private static final Logger LOGGER = Logger.getLogger(ProductCatalog.class.getName());
	private static final String HORIZONTAL_HR = "--------------------------------";

	static {
		LOGGER.setLevel(Level.FINE);
		Handler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.FINE);
		consoleHandler.setFormatter(new Formatter() {
			@Override
<<<<<<< HEAD
			public String format(LogRecord logRecord) {
				return logRecord.getMessage() + System.lineSeparator();
=======
			public String format(LogRecord record) {
				return record.getMessage() + System.lineSeparator();
>>>>>>> 13ff115b9e13653810dc42c6433f49d8919c5da2
			}
		});
		LOGGER.addHandler(consoleHandler);
		LOGGER.setUseParentHandlers(false);
	}

	public ProductCatalog() {
		this.categories = new ArrayList<>();
	}

	void addCategory(ProductCategory category) {
		categories.add(category);
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
<<<<<<< HEAD
			LOGGER.info(i + ". ");
=======
>>>>>>> 13ff115b9e13653810dc42c6433f49d8919c5da2
			LOGGER.info(category.getName());
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
		String searchKeyLower = searchKey.toLowerCase();
		for (ProductCategory productCategory : categories) {
			for (Product product : productCategory.getProducts()) {
				if (product.getName().toLowerCase().contains(searchKeyLower) ||
						product.getDescriptions().toLowerCase().contains(searchKeyLower)) {
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
