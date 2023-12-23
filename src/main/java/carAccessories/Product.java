package carAccessories;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Product {
	private String name;
	private String descriptions;
	private List<String> images;
	private double price;
	private boolean isAvailable;
	private static final Logger LOGGER = Logger.getLogger(Product.class.getName());
	private static final String HORIZONTAL_HR = "--------------------------------";
	private static final String INDEX_FORMAT = "{0}. ";
<<<<<<< HEAD
=======
	
>>>>>>> 13ff115b9e13653810dc42c6433f49d8919c5da2
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
<<<<<<< HEAD
	}

	public void print() {
	    LOGGER.fine(HORIZONTAL_HR);
	    LOGGER.fine("\t\t\t\tProduct Details");
	    LOGGER.log(Level.FINE, INDEX_FORMAT + "Name: {0}", new Object[]{this.name});
	    LOGGER.log(Level.FINE, INDEX_FORMAT + "Description: {0}", new Object[]{this.descriptions});
	    LOGGER.log(Level.FINE, INDEX_FORMAT + "Price: {0} ILS", new Object[]{this.price});
	    LOGGER.log(Level.FINE, INDEX_FORMAT + "Availability: {0}", new Object[]{this.isAvailable ? "In Stock" : "Out of Stock"});
	    if (images != null && !images.isEmpty()) {
	        LOGGER.log(Level.FINE, INDEX_FORMAT + "Images: {0}", new Object[]{images.toString()});
	    }
	    LOGGER.fine(HORIZONTAL_HR);
=======
>>>>>>> 13ff115b9e13653810dc42c6433f49d8919c5da2
	}

	 public void print() {
	        LOGGER.fine(HORIZONTAL_HR);
	        LOGGER.fine("\t\t\t\tProduct Details");
	        LOGGER.fine("Name: " + this.name);
	        LOGGER.fine("Description: " + this.descriptions);
	        LOGGER.fine("Price: " + this.price);
	        LOGGER.fine("Availability: " + (this.isAvailable ? "In Stock" : "Out of Stock"));
	        LOGGER.fine(HORIZONTAL_HR);
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
