module kprog.main {
  requires java.desktop;
  requires org.slf4j;
  requires javafx.controls;
  requires javafx.fxml;
  exports environment.testprograms;
  exports examples.javafx.bidirectional;
  exports examples.javafx.firststeps;
  exports examples.javafx.fxml;
  exports examples.javafx.listcell;
  exports examples.javafx.misc;
  exports examples.javafx.modal;
  exports examples.javafx.mvvm;
  exports examples.javafx.observable;
  exports examples.io;
  exports examples.designpattern.observer;
  exports examples.designpattern.singleton;
  exports prog.javafx.main;
  exports prog.ex10.solution.javafx4pizzadelivery.gui;
  exports prog.ex09.solution.editpizzascreen.gui;
  exports prog.ex09.solution.editpizzascreen.pizzadelivery;
  exports prog.ex09.exercise.editpizzascreen.pizzadelivery;
}
