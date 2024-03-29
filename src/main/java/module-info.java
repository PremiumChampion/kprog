module kprog.main {
  requires java.desktop;
  requires org.slf4j;
  requires javafx.controls;
  requires javafx.fxml;
  requires java.rmi;
  exports environment.testprograms;
  exports examples.javafx.bidirectional;
  exports examples.javafx.firststeps;
  exports examples.javafx.fxml;
  exports examples.javafx.listcell;
  exports examples.javafx.misc;
  exports examples.javafx.modal;
  exports examples.javafx.mvvm;
  exports examples.javafx.observable;
  exports examples.javafx.addressbook;
  exports examples.io;
  exports examples.i18n;
  exports examples.dateandtime;
  exports examples.designpattern.observer;
  exports examples.designpattern.singleton;
  exports prog.javafx.main;
  exports prog.ex10.solution.javafx4pizzadelivery.gui;
  exports prog.ex15.monolingual.gui;
  exports prog.ex09.solution.editpizzascreen.gui;
  exports prog.ex09.solution.editpizzascreen.pizzadelivery;
  exports prog.ex09.exercise.editpizzascreen.pizzadelivery;
  opens examples.javafx.fxml to javafx.fxml;
  opens prog.ex10.solution.javafx4pizzadelivery.gui to javafx.fxml, javafx.graphics;
  exports prog.ex10.exercise.javafx4pizzadelivery.gui;
  exports prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery;
  exports prog.ex10.solution.javafx4pizzadelivery.gui.events;
  opens livesession.snake to javafx.fxml, javafx.graphics;
  exports livesession.snake;
  exports livesession.snake.ui;
  exports livesession.snake.ui.configure;
  exports livesession.snake.ui.gameover;
  exports livesession.snake.ui.mainmenu;
  exports livesession.snake.ui.pause;
  exports livesession.snake.ui.play;
  exports livesession.snake.ui.nodes;
  exports examples.rmi.shapes;
  exports prog.ex15.solution.i18ncountries.gui;
  exports prog.ex15.solution.i18ncountries;
  exports prog.ws21.solution.bookings.gui;
}
