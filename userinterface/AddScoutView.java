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

/** The class containing the Account View for the ATM application */
// ==============================================================
public class AddScoutView extends View {

	// GUI components
	protected TextField lastName;
	protected TextField firstName;
	protected TextField middleName;
	protected TextField dateOfBirth;
	protected TextField phoneNumber;
	protected TextField email;
	protected TextField troopID;
	protected TextField dateStatusUpdated;
	protected ComboBox<String> status;
	protected TreeLotCoordinator myTLC;

	protected Button submitButton;
	protected Button doneButton;

	// For showing error message
	protected MessageView statusLog;

	// constructor for this class -- takes a model object
	// ----------------------------------------------------------
	public AddScoutView(TreeLotCoordinator tlc) {
		super(tlc, "ScoutView");

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

		populateFields();

	}

	// Create the title container
	// -------------------------------------------------------------
	private Node createTitle() {
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);

		Text titleText = new Text(" Add Scout ");
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

		Text accNumLabel = new Text(" Last Name: ");
		Font myFont = Font.font("Helvetica", FontWeight.BOLD, 12);
		accNumLabel.setFont(myFont);
		accNumLabel.setWrappingWidth(150);
		accNumLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(accNumLabel, 0, 1);

		lastName = new TextField();
		lastName.setEditable(true);
		grid.add(lastName, 1, 1);

		Text acctTypeLabel = new Text(" First Name : ");
		acctTypeLabel.setFont(myFont);
		acctTypeLabel.setWrappingWidth(150);
		acctTypeLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(acctTypeLabel, 0, 2);

		firstName = new TextField();
		firstName.setEditable(true);
		grid.add(firstName, 1, 2);

		Text balLabel = new Text(" Middle Name: ");
		balLabel.setFont(myFont);
		balLabel.setWrappingWidth(150);
		balLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(balLabel, 0, 3);

		middleName = new TextField();
		middleName.setEditable(true);
		grid.add(middleName, 1, 3);

		Text balLabel1 = new Text(" Date Of Birth: ");
		balLabel1.setFont(myFont);
		balLabel1.setWrappingWidth(150);
		balLabel1.setTextAlignment(TextAlignment.RIGHT);
		grid.add(balLabel1, 0, 4);

		dateOfBirth = new TextField();
		dateOfBirth.setEditable(true);
		grid.add(dateOfBirth, 1, 4);

		Text balLabel2 = new Text(" Phone Number: ");
		balLabel2.setFont(myFont);
		balLabel2.setWrappingWidth(150);
		balLabel2.setTextAlignment(TextAlignment.RIGHT);
		grid.add(balLabel2, 0, 5);

		phoneNumber = new TextField();
		phoneNumber.setEditable(true);
		grid.add(phoneNumber, 1, 5);

		Text balLabel3 = new Text(" Email: ");
		balLabel3.setFont(myFont);
		balLabel3.setWrappingWidth(150);
		balLabel3.setTextAlignment(TextAlignment.RIGHT);
		grid.add(balLabel3, 0, 6);

		email = new TextField();
		email.setEditable(true);
		grid.add(email, 1, 6);

		Text balLabel4 = new Text(" Troop ID: ");
		balLabel4.setFont(myFont);
		balLabel4.setWrappingWidth(150);
		balLabel4.setTextAlignment(TextAlignment.RIGHT);
		grid.add(balLabel4, 0, 7);

		troopID = new TextField();
		troopID.setEditable(true);
		grid.add(troopID, 1, 7);

		Text balLabel5 = new Text(" Status: ");
		balLabel5.setFont(myFont);
		balLabel5.setWrappingWidth(150);
		balLabel5.setTextAlignment(TextAlignment.RIGHT);
		grid.add(balLabel5, 0, 8);

		status = new ComboBox<String>();
		status.getItems().addAll("Active", "Inactive");

		grid.add(status, 1, 8);

		Text balLabel6 = new Text(" Date Status Updated: ");
		balLabel6.setFont(myFont);
		balLabel6.setWrappingWidth(150);
		balLabel6.setTextAlignment(TextAlignment.RIGHT);
		grid.add(balLabel6, 0, 9);

		dateStatusUpdated = new TextField();
		dateStatusUpdated.setEditable(true);
		grid.add(dateStatusUpdated, 1, 9);

		MessageView mv = new MessageView("");
		mv.setFont(myFont);
		mv.setWrappingWidth(350);

		grid.add(mv, 0, 10);

		HBox doneCont = new HBox(10);
		doneCont.setAlignment(Pos.CENTER);
		submitButton = new Button("Submit");
		submitButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		submitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				clearErrorMessage();

				if (lastName.getText() == null || firstName.getText() == null) {
					mv.displayErrorMessage("The 'First Name' and 'Last Name' fields cannot be empty.");
				} else {
					Properties p1 = new Properties();
					p1.setProperty("lastName", lastName.getText());
					p1.setProperty("firstName", firstName.getText());
					p1.setProperty("middleName", middleName.getText());
					p1.setProperty("dateOfBirth", dateOfBirth.getText());
					p1.setProperty("phoneNumber", phoneNumber.getText());
					p1.setProperty("email", email.getText());
					p1.setProperty("troopID", troopID.getText());
					p1.setProperty("status", status.getValue().toString());
					p1.setProperty("dateStatusUpdated", dateStatusUpdated.getText());

					Scout b1 = new Scout(p1);
					b1.updateStateInDatabase();
					lastName.setText(null);
					firstName.setText(null);
					middleName.setText(null);
					dateOfBirth.setText(null);
					phoneNumber.setText(null);
					email.setText(null);
					troopID.setText(null);
					dateStatusUpdated.setText(null);

					mv.displayMessage("Scout inserted successfully!");
				}
			}
		});

		doneCont.getChildren().add(submitButton);

		doneButton = new Button("Done");
		doneButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		
		/** Still needs to be changed to the observer pattern
		doneButton.setOnAction(new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent e) {
				clearErrorMessage();
				TreeLotCoordinator tlc = new TreeLotCoordinator();

				tlc.transactionDone();
			}
		});
		*/
		doneCont.getChildren().add(doneButton);

		vbox.getChildren().add(grid);
		vbox.getChildren().add(doneCont);

		return vbox;
	}

	// Create the status log field
	// -------------------------------------------------------------
	protected MessageView createStatusLog(String initialMessage) {
		statusLog = new MessageView(initialMessage);

		return statusLog;
	}

	// -------------------------------------------------------------
	public void populateFields() {
		lastName.setText((String) myModel.getState("lastName"));
		firstName.setText((String) myModel.getState("firstName"));
		middleName.setText((String) myModel.getState("middleName"));
		dateOfBirth.setText((String) myModel.getState("dateOfBirth"));
		phoneNumber.setText((String) myModel.getState("phoneNumber"));
		email.setText((String) myModel.getState("email"));
		troopID.setText((String) myModel.getState("troopID"));
		dateStatusUpdated.setText((String) myModel.getState("dateStatusUpdated"));

	}

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
