package lk.ijse.dep.pos.controller;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.pos.AppInitializer;
import lk.ijse.dep.pos.business.custom.OrderBO;
import lk.ijse.dep.pos.business.custom.impl.OrderBOImpl;
import lk.ijse.dep.pos.dto.OrderDTO2;
import lk.ijse.dep.pos.util.OrderTM;
import lk.ijse.dep.pos.util.SearchOrderTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SearchOrdersFormController {
    public TextField txtSearch;
    public TableView<SearchOrderTM> tblOrders;

    private OrderBO orderBO = AppInitializer.ctx.getBean(OrderBO.class);

    public void initialize() throws Exception {

        // Let's map
        tblOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));

        loadTable();

        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    loadTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void loadTable() throws Exception {
        ObservableList<SearchOrderTM> olOrders = tblOrders.getItems();
        olOrders.clear();

        List<OrderDTO2> orders = orderBO.getOrderInfo("%"+txtSearch.getText()+"%");
        if(orders!=null){
        for (OrderDTO2 order:orders){
            olOrders.add(new SearchOrderTM(order.getOrderId(),
                    order.getOrderDate().toLocalDate(),
                    order.getCustomerId(),
                    order.getCustomerName(),order.getTotal()));

        }
            tblOrders.setItems(olOrders);
        }
        System.out.println(olOrders);


    }

    @FXML
    private void navigateToHome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/dep/pos/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.txtSearch.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public void tblOrders_OnMouseClicked(MouseEvent mouseEvent) throws Exception {

      /*  if (mouseEvent.getClickCount() == 2) {

            URL resource = this.getClass().getResource("/view/PlaceOrderForm.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(resource);
            Parent root = fxmlLoader.load();
            Scene placeOrderScene = new Scene(root);
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(placeOrderScene);
            secondaryStage.centerOnScreen();
            secondaryStage.setTitle("View Order");
            secondaryStage.setResizable(false);

            PlaceOrderFormController ctrl = fxmlLoader.getController();
            OrderTM selectedOrder = tblOrders.getSelectionModel().getSelectedItem();
            ctrl.initializeForSearchOrderForm(selectedOrder.getOrderId());

            secondaryStage.show();
        }
        Integer orderId = tblOrders.getSelectionModel().getSelectedItem().getOrderId();
        if (mouseEvent.getClickCount() == 2) {
            URL resource = this.getClass().getResource("/view/PlaceOrderForm.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(resource);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage secondaryStage = (Stage) (searchOrderForm.getScene().getWindow());
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("View Order");
            secondaryStage.centerOnScreen();
            secondaryStage.show();
            PlaceOrderFormController placeOrderController = fxmlLoader.getController();
            placeOrderController.getSearchOrderDetails(orderId);
            }*/

    }

}


