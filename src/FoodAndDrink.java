
public class FoodAndDrink {
    private String name;
    private float price;
    private Menu menu;

    public FoodAndDrink(String name, float price) {
        this.name = name;
        this.price = price;
    }
    
    // Getters and Setters for name and price
    
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
    public void removeFromMenu() {
        if (menu != null) {
            menu.removeFoodAndDrink(this);
        }
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Menu getMenu() {
		return menu;
	}
    
    
}
