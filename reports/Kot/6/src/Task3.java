import java.util.List;
import java.util.ArrayList;

interface Command {
    void execute();
}

class CreateOrderCommand implements Command {
    private Order order;
    private List<MenuItem> itemsToAdd;

    public CreateOrderCommand(Order order, List<MenuItem> itemsToAdd) {
        this.order = order;
        this.itemsToAdd = itemsToAdd;
    }

    @Override
    public void execute() {
        order.addItems(itemsToAdd);
        System.out.println("Заказ сформирован: " + order);
    }
}

class CancelOrderCommand implements Command {
    private Order order;

    public CancelOrderCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        order.cancel();
        System.out.println("Заказ отменен: " + order);
    }
}

class RepeatOrderCommand implements Command {
    private OrderHistory orderHistory;
    private int orderIndex;

    public RepeatOrderCommand(OrderHistory orderHistory, int orderIndex) {
        this.orderHistory = orderHistory;
        this.orderIndex = orderIndex;
    }

    @Override
    public void execute() {
        Order orderToRepeat = orderHistory.getOrder(orderIndex);
        Order repeatedOrder = new Order();
        repeatedOrder.addItems(orderToRepeat.getItems());
        System.out.println("Повторный заказ сформирован: " + repeatedOrder);
    }
}

class Order {
    private List<MenuItem> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addItems(List<MenuItem> itemsToAdd) {
        items.addAll(itemsToAdd);
    }

    public void cancel() {
        items.clear();
    }

    public List<MenuItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Заказ: " + items;
    }
}

class OrderHistory {
    private List<Order> orders;

    public OrderHistory() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order getOrder(int index) {
        return orders.get(index);
    }
}

class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}

class Waiter {
    private List<Command> orders;

    public Waiter() {
        this.orders = new ArrayList<>();
    }

    public void takeOrder(Command command) {
        orders.add(command);
    }

    public void placeOrders() {
        for (Command command : orders) {
            command.execute();
        }
        orders.clear();
    }
}

public class Task3 {
    public static void main(String[] args) {
        MenuItem pepperoni = new MenuItem("Пепперони", 10.99);
        MenuItem margarita = new MenuItem("Маргарита", 5.99);

        Order order = new Order();

        Waiter waiter = new Waiter();

        List<MenuItem> itemsToAdd = new ArrayList<>();
        itemsToAdd.add(pepperoni);
        itemsToAdd.add(margarita);
        Command createOrderCommand = new CreateOrderCommand(order, itemsToAdd);

        Command cancelOrderCommand = new CancelOrderCommand(order);

        OrderHistory orderHistory = new OrderHistory();
        orderHistory.addOrder(order);
        Command repeatOrderCommand = new RepeatOrderCommand(orderHistory, 0);

        waiter.takeOrder(createOrderCommand);
        waiter.takeOrder(repeatOrderCommand);
        waiter.takeOrder(cancelOrderCommand);

        waiter.placeOrders();
    }
}
