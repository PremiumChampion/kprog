package examples.javafx.bidirectional;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

/**
 * Demonstrates the usage of a bidirectional binding.
 */
public class BidirectionalSliderDemo extends Application {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(BidirectionalSliderDemo.class);

  @Override
  public void start(final Stage primaryStage) throws Exception {
    Slider slider = new Slider(0, 1, 0.1);
    slider.setShowTickMarks(true);
    slider.setShowTickLabels(true);
    slider.setMajorTickUnit(0.1);
    slider.setBlockIncrement(0.1);
    slider.snapToTicksProperty().set(true);
    SimpleDoubleProperty doubleProperty = new SimpleDoubleProperty(0.8);
    slider.valueProperty().bindBidirectional(doubleProperty);
    Button upButton = new Button("up");
    Button downButton = new Button("down");
    Label valueLabel = new Label();
    Bindings.bindBidirectional(valueLabel.textProperty(), doubleProperty,
        new NumberStringConverter());
    upButton.setOnAction((e) -> doubleProperty.setValue(doubleProperty.getValue() + 0.1));
    downButton.setOnAction((e) -> doubleProperty.setValue(doubleProperty.getValue() - 0.1));
    HBox hbox = new HBox();
    hbox.getChildren().addAll(downButton, valueLabel, upButton);
    VBox root = new VBox();
    root.getChildren().addAll(slider, hbox);
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
