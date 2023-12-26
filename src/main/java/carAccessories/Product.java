package carAccessories;
import java.util.List;
import java.util.logging.Logger;

public class Product {
	private String name;
	private String descriptions;
	private List<String> images;
	private double price;
	private boolean isAvailable;
	private static Logger logger = Logger.getLogger(Product.class.getName());
	private static final String HORIZONTAL_HR = "--------------------------------";


	public void print() {
		configureLogger();
	    logger.fine(HORIZONTAL_HR);
	    logger.fine("\t\t\t\tProduct Details");
	    String product = "Name: "+this.name + "\nDescription: "+this.descriptions + "\nPrice: "+this.price + "\nAvailability: "+(this.isAvailable ? "In Stock" : "Out of Stock") + "\n";
	    logger.info(product);
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
	public Product(String name, String descriptions, List<String>images, double price, boolean isAvailable) {
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

	public List<String> getImages() {
		return images;
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
