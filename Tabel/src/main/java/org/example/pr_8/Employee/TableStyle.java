package org.example.pr_8.Employee;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TableStyle {
    public static void applyStyle(TableView<?> table, Stage stage, String titleText) {
        Label title = new Label(titleText);
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        DropShadow shadow = new DropShadow();
        shadow.setRadius(10.0);
        shadow.setOffsetY(4.0);
        shadow.setColor(Color.color(0.4, 0.4, 0.4, 0.3));
        table.setEffect(shadow);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setStyle("""
                -fx-background-color: white;
                -fx-border-color: #dcdcdc;
                -fx-border-radius: 8px;
                -fx-background-radius: 8px;
                """);

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #b6d6fc, #0078f8);");
        root.getChildren().addAll(title, table);

        Scene scene = new Scene(root, 1500, 600);
        scene.getStylesheets().add(
                TableStyle.class.getResource("/table-styler.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.setTitle(titleText);
        stage.show();
    }
}
