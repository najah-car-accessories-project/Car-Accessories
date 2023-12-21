package carAccessories;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCatalogTest {

	private static Admin admin;
	private ProductCatalog productCatalog;
	private ProductCategory audioCategory;
	private static Product product;
	private static List<Product> productList;
	private static int index;
	private  static String searchKey;

	public ProductCatalogTest() {
		productCatalog = new ProductCatalog();
		audioCategory = new ProductCategory("Audio Category");
		admin = new Admin("admin", "pass", "admin");
		productCatalog.addCategory(audioCategory);
		index = 0;
	}

	@Given("the Admin is logged in")
	public void the_admin_is_logged_in() {
		assertTrue(admin.signIn("admin", "pass"));
	}

	@When("enter product details including name, description, price, and availability")
	public void enter_product_details_including_name_description_price_and_availability() {
		product = new Product("JBL Pass", "desc", new ArrayList<String>(), 100.0, true);

	}

	@Then("the product is added to the specified category in the catalog")
	public void the_product_is_added_to_the_specified_category_in_the_catalog() {
		productCatalog.addProductToCategory(product, audioCategory.getName());
		productCatalog.addProductToCategory(new Product("Bioner Pass", "desc", new ArrayList<String>(), 100.0, true),
				audioCategory.getName());
		assertNotNull(audioCategory.getAllProducts().get(0));
		
	}

	@When("enter product index")
	public void enter_product_index() {
		index = 0;
	}

	@Then("the product is either updated with new details or removed from the catalog")
	public void the_product_is_either_updated_with_new_details_or_removed_from_the_catalog() {

		productCatalog.addProductToCategory(product, audioCategory.getName());
		productCatalog.addProductToCategory(new Product("Bioner Pass", "desc", new ArrayList<String>(), 100.0, true),
				audioCategory.getName());

		audioCategory.getAllProducts().get(index).setName("New Product Name");
		assertEquals(audioCategory.getAllProducts().get(index).getName(), "New Product Name");
		audioCategory.getAllProducts().remove(index);
		assertEquals(audioCategory.getAllProducts().size(), 1);
	}

	@When("they select to view all products")
	public void they_select_to_view_all_products() {

		productList = productCatalog.getAllCategories().get(0).getAllProducts();
	}

	@Then("they see a list of all products with their details")
	public void they_see_a_list_of_all_products_with_their_details() {
		productCatalog.printCatalog();

		assertNotNull(productList);
	}

	@When("they add a new category with name")
	public void they_add_a_new_category_with_name() {
		productCatalog.addCategory(new ProductCategory("New Category"));
	}

	@Then("the new category is added to the product catalog")
	public void the_new_category_is_added_to_the_product_catalog() {
		assertEquals(productCatalog.getAllCategories().size(), 2);
	}

	@Given("a product category exists")
	public void a_product_category_exists() {
		assertEquals(productCatalog.getAllCategories().size(),1);
	}

	@When("they choose to edit or delete the category")
	public void they_choose_to_edit_or_delete_the_category() {
		
	}

	@Then("the category is either updated with new details or removed from the catalog")
	public void the_category_is_either_updated_with_new_details_or_removed_from_the_catalog() {
		productCatalog.getAllCategories().get(0).setName("Audio Category super");
		assertEquals(productCatalog.getAllCategories().get(0).getName(), "Audio Category super");
		productCatalog.getAllCategories().remove(0);
		assertEquals(productCatalog.getAllCategories().size(), 0);
	}

	@When("they select to view all product categories")
	public void they_select_to_view_all_product_categories() {
		
	}

	@Then("they see a list of all categories")
	public void they_see_a_list_of_all_categories() {
		assertEquals(productCatalog.getAllCategories().size(), 1);
		productCatalog.printCategories();
	}

	@Given("a list of products in the catalog")
	public void a_list_of_products_in_the_catalog() {
		productCatalog.addProductToCategory(product, audioCategory.getName());
		productCatalog.addProductToCategory(new Product("Bioner Pass", "desc", new ArrayList<String>(), 100.0, true),
				audioCategory.getName());
		assertEquals(productCatalog.getAllProducts().size(), 2);
	}

	@When("a User searches by search key")
	public void a_user_searches_by_search_key() {
		searchKey = "Pass";
	}

	@Then("they see a list of products matching the search key")
	public void they_see_a_list_of_products_matching_the_search_key() {
		productCatalog.addProductToCategory(new Product("Bioner Pass ", "300 wat", new ArrayList<String>(), 100.0, true),
				audioCategory.getName());
		productCatalog.addProductToCategory(new Product("JBL", "Pass", new ArrayList<String>(), 100.0, true),
				audioCategory.getName());
		productCatalog.addProductToCategory(new Product("Bioner normal", "desc", new ArrayList<String>(), 100.0, true),
				audioCategory.getName());
		audioCategory.printProducts();

		productList = productCatalog.searchProducts(searchKey);
		assertEquals(productList.size(), 3);
	}

}
