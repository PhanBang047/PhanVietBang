package chương_4;

import java.util.ArrayList;

class Product {
    private String code;
    private String name;
    private double price;

    public Product(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class CartItem {
    Product product;
    int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getAmount() {
        return product.getPrice() * quantity;
    }
}

class Cart {
    private String code;
    private ArrayList<CartItem> items = new ArrayList<CartItem>();

    public Cart(String code) {
        this.code = code;
    }

    public void addItem(Product p, int qty) {
        // nếu sp đã có trong giỏ thì cộng thêm số lượng
        for (int i = 0; i < items.size(); i++) {
            CartItem it = items.get(i);
            if (it.product.getCode().equals(p.getCode())) {
                it.quantity = it.quantity + qty;
                return;
            }
        }
        items.add(new CartItem(p, qty));
    }

    public double total() {
        double sum = 0;
        for (CartItem it : items) {
            sum = sum + it.getAmount();
        }
        return sum;
    }

    public void showCart() {
        System.out.println("Giỏ hàng " + code);
        for (CartItem it : items) {
            System.out.printf("%s x%d = %,.0f%n", it.product.getName(), it.quantity, it.getAmount());
        }
        System.out.printf("Tổng tiền: %,.0f%n", total());
    }
}

class Main {
    public static void main(String[] args) {
        Product p1 = new Product("P01", "Áo thun", 100);
        Product p2 = new Product("P02", "Quần Jean", 200);

        Cart cart1 = new Cart("C01");
        cart1.addItem(p1, 2);
        cart1.addItem(p1, 1);

        // cart2 với cart1 trỏ chung 1 đối tượng
        Cart cart2 = cart1;
        cart2.addItem(p2, 1);

        System.out.println("In cart1 sau khi sửa cart2:");
        cart1.showCart();
    }
}