package seedu.address.ui.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.order.Order;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays the information of an {@code Order}.
 */
public class OrderCard extends UiPart<Region> {

    private static final String FXML = "OrderListCard.fxml";

    public final Order order;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label cheeseType;
    @FXML
    private Label quantity;
    @FXML
    private Label orderDate;
    @FXML
    private Label completedDate;

    /**
     * Creates an {@code OrderCard} with the given {@code Order} and index to display.
     */
    public OrderCard(Order order, int displayedIndex) {
        super(FXML);
        this.order = order;
        id.setText(displayedIndex + ". ");
        cheeseType.setText(order.getCheeseType().toString());
        quantity.setText(order.getQuantity().toString());
        orderDate.setText(order.getOrderDate().toString());
        completedDate.setText(order.getCompletedDate().toString());

        // TODO: Add in customer details. To do that, we must be able to access
        //       the Customer object from within the Order class.
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OrderCard)) {
            return false;
        }

        OrderCard card = (OrderCard) other;
        return id.getText().equals(card.id.getText())
                && order.equals(card.order);
    }
}
