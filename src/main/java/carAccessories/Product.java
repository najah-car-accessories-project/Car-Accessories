package carAccessories;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Product {
	private String name;
	private String descriptions;
	private List<String> images;
	private double price;
	private boolean isAvailable;
	private static final Logger LOGGER = Logger.getLogger(Product.class.getName());
	private static final String HORIZONTAL_HR = "--------------------------------";
	private static final String INDEX_FORMAT = "{0}. ";

	void print() {
		LOGGER.fine(HORIZONTAL_HR);
	    LOGGER.fine("\t\t\t\tProduct Details");
	    LOGGER.log(Level.FINE, INDEX_FORMAT, new Object[] { "Name: " + this.name });
	    LOGGER.log(Level.FINE, INDEX_FORMAT, new Object[] { "Description: " + this.descriptions });
	    LOGGER.log(Level.FINE, INDEX_FORMAT, new Object[] { "Price: " + this.price });
	    LOGGER.log(Level.FINE, INDEX_FORMAT, new Object[] { "Availability: " + (this.isAvailable ? "In Stock" : "Out of Stock") });
	    LOGGER.log(Level.FINE, INDEX_FORMAT, new Object[] { "Images: " + this.images });
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
