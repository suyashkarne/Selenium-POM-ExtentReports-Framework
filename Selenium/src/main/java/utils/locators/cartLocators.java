package utils.locators;

public class cartLocators {
    public static final String  addToCart="//a[text()='Add to cart']";
    public static final String  home="//a[contains(text(),'Home')]";
    public static final String  cart="//a[text()='Cart']";
    public static final String  noOfProducts="//tbody[@id='tbodyid']/tr";
    public static final String  productPrice="//h3[@class='price-container']";
    public static final String  total="//h3[@id='totalp']";
    public static final String  rowsInCart="//tbody[@id='tbodyid']/tr";
    public static final String  cartDelete="//a[text()='Delete']";
    public static final String  productPriceInTable="(//tr[@class='success']/td)[3]";
}
