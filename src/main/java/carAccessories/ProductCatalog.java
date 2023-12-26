package carAccessories;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductCatalog {
	private List<ProductCategory> categories;
	private static  Logger logger = Logger.getLogger(ProductCatalog.class.getName());
	private static final String HORIZONTAL_HR = "--------------------------------";


	public ProductCatalog() {
		this.categories = new ArrayList<>();
	}

	void addCategory(ProductCategory category) {
		categories.add(category);
	}

	public List<ProductCategory> getAllCategories() {
		return categories;
	}
	private void configureLogger() {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.getHandlers()[0].setFormatter(new SimpleFormatter());
    }

    private class SimpleFormatter extends java.util.logging.SimpleFormatter {
        @Override
        public synchronized String format(java.util.logging.LogRecord logRecord) {
            return logRecord.getMessage() + "\n";
        }
    }
	public void addProductToCategory(Product newProduct, String category) {

		for (ProductCategory productCategory : categories) {
			if (productCategory.getName().equalsIgnoreCase(category)) {
				productCategory.addProduct(newProduct);
			}
		}
	}

	public void printCategories() {
		 configureLogger();
		int i = 0;
		for (ProductCategory category : categories) {
	        String index = i + ". ";
	        logger.info(index);
			logger.info(category.getName());
			i++;

		}

	}

	void printCatalog() {
		 configureLogger();
		logger.fine(HORIZONTAL_HR);
		logger.fine("Categorys");
		logger.fine(HORIZONTAL_HR);
		for (ProductCategory category : categories) {
			logger.fine(category.getName());
			category.printProducts();
			logger.fine(HORIZONTAL_HR);
		}

	}

	public List<Product> searchProducts(String searchKey) {
		List<Product> products = new ArrayList<>();
		String searchKeyLower = searchKey.toLowerCase();
		for (ProductCategory productCategory : categories) {
			for (Product product : productCategory.getProducts()) {
				if (product.getName().toLowerCase().contains(searchKeyLower)
						|| product.getDescriptions().toLowerCase().contains(searchKeyLower)) {
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
