package carAccessories;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProductCategory {
    private String name;
    private List<Product> products;
	private static Logger logger = Logger.getLogger(Users.class.getName());

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
	public void printProducts() {
		configureLogger();
		int i = 0;
		for (Product product : products) {
			String index = i + ". ";
			logger.info(index);
			product.print();
			i++;
		}
		
	}

}
