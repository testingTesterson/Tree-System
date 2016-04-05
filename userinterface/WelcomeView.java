package userinterface;

import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Scout;
import model.ScoutCollection;
import model.TreeLotCoordinator;
import javafx.*;

import java.awt.Graphics;
import java.awt.Shape;
import java.util.Properties;

import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Position.Bias;

// project imports
import impresario.IModel;

public class WelcomeView extends View {

	// GUI components

	protected TextField title;

	protected TreeLotCoordinator myTLC;

	protected Button sellTreeButton;
	protected Button openShiftButton;
	protected Button closeShiftButton;
	protected Button manageScoutButton;
	protected Button manageTreesButton;
	protected Button manageTreeTypesButton;
	// For showing error message
	protected MessageView statusLog;

	// constructor for this class -- takes a model object
	// ----------------------------------------------------------
	public WelcomeView(TreeLotCoordinator tlc) {

		super(tlc, null);

		myTLC = tlc;

		// create a container for showing the contents
		VBox container = new VBox(10);
		container.setPadding(new Insets(15, 5, 5, 5));

		// Add a title for this panel
		container.getChildren().add(createTitle());

		// create our GUI components, add them to this Container
		container.getChildren().add(createFormContent());

		container.getChildren().add(createStatusLog("             "));

		getChildren().add(container);

	}

	// Create the title container
	// -------------------------------------------------------------
	private Node createTitle() {
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);

		Text titleText = new Text(" Select an Option ");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setWrappingWidth(300);
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKGREEN);
		container.getChildren().add(titleText);

		return container;
	}

	// Create the main form content
	// -------------------------------------------------------------
	private VBox createFormContent() {
		VBox vbox = new VBox(10);

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		HBox doneCont = new HBox(10);
		doneCont.setAlignment(Pos.CENTER);
		sellTreeButton = new Button("Sell Tree");
		sellTreeButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		sellTreeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				clearErrorMessage();

			}

		});

		doneCont.getChildren().add(sellTreeButton);

		openShiftButton = new Button("Open Shift");
		openShiftButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		openShiftButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

			}
		});
		doneCont.getChildren().add(openShiftButton);

		closeShiftButton = new Button("Close Shift");
		closeShiftButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		closeShiftButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

			}
		});
		doneCont.getChildren().add(closeShiftButton);

		HBox row2 = new HBox(10);
		doneCont.setAlignment(Pos.CENTER);
		manageScoutButton = new Button("Manage Scouts");
		manageScoutButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		
		/**	Needs to be changed to the observer pattern
		manageScoutButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				clearErrorMessage();
				myLibrarian.createAndShowScoutChoiceView();
			}

		});
		 */
		row2.getChildren().add(manageScoutButton);

		manageTreesButton = new Button("Manage Trees");
		manageTreesButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		manageTreesButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				clearErrorMessage();

			}

		});

		row2.getChildren().add(manageTreesButton);

		manageTreeTypesButton = new Button("Manage Tree Types");
		manageTreeTypesButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		manageTreeTypesButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				clearErrorMessage();

			}

		});

		row2.getChildren().add(manageTreeTypesButton);

		vbox.getChildren().add(grid);
		vbox.getChildren().add(doneCont);
		vbox.getChildren().add(row2);

		return vbox;
	}

	// Create the status log field
	// -------------------------------------------------------------
	protected MessageView createStatusLog(String initialMessage) {
		statusLog = new MessageView(initialMessage);

		return statusLog;
	}

	// -------------------------------------------------------------

	/**
	 * Update method
	 */
	// ---------------------------------------------------------

	// ----------------------------------------------------------
	public void displayErrorMessage(String message) {
		statusLog.displayErrorMessage(message);
	}

	/**
	 * Display info message
	 */
	// ----------------------------------------------------------
	public void displayMessage(String message) {
		statusLog.displayMessage(message);
	}

	/**
	 * Clear error message
	 */
	// ----------------------------------------------------------
	public void clearErrorMessage() {
		statusLog.clearErrorMessage();
	}

	@Override
	public void updateState(String key, Object value) {
		// TODO Auto-generated method stub

	}

}
