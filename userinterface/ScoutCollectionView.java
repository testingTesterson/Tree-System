package userinterface;

import java.awt.Graphics;
import java.awt.Shape;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.text.BadLocationException;
import javax.swing.text.Position.Bias;

// project imports
import impresario.IModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Scout;
import model.ScoutCollection;

public class ScoutCollectionView extends View {

	protected TableView<ScoutTableModel> tableOfScouts;
	protected Button cancelButton;

	private ScoutCollection myScoutCollection;

	protected MessageView statusLog;

	// --------------------------------------------------------------------------
	public ScoutCollectionView(ScoutCollection bc) {
		super(bc, "ScoutCollectionView");
		myScoutCollection = bc;

		// create a container for showing the contents
		VBox container = new VBox(10);
		container.setPadding(new Insets(15, 5, 5, 5));

		// create our GUI components, add them to this panel
		container.getChildren().add(createTitle());
		container.getChildren().add(createFormContent());

		// Error message area
		container.getChildren().add(createStatusLog("                                            "));

		getChildren().add(container);

		populateFields();
	}

	// --------------------------------------------------------------------------
	protected void populateFields() {

		getEntryTableModelValues();
	}

	// --------------------------------------------------------------------------
	protected void getEntryTableModelValues() {

		ObservableList<ScoutTableModel> tableData = FXCollections.observableArrayList();
		try {

			ScoutCollection b1 = (ScoutCollection) myModel.getState("ScoutList");
			Vector entryList = (Vector) b1.getState("scout");
			Enumeration entries = entryList.elements();

			while (entries.hasMoreElements() == true) {

				Scout nextScout = (Scout) entries.nextElement();
				Vector<String> view = nextScout.getEntryListView();

				// add this list entry to the list
				ScoutTableModel nextTableRowData = new ScoutTableModel(view);
				tableData.add(nextTableRowData);

			}

			tableOfScouts.setItems(tableData);

		} catch (Exception e) {// SQLException e) {
			// Need to handle this exception
		}
	}

	// Create the title container
	// -------------------------------------------------------------
	private Node createTitle() {
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);

		Text titleText = new Text(" Matching Scouts ");
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

		Text prompt = new Text("LIST OF Scouts");
		prompt.setWrappingWidth(350);
		prompt.setTextAlignment(TextAlignment.CENTER);
		prompt.setFill(Color.BLACK);
		grid.add(prompt, 0, 0, 2, 1);

		tableOfScouts = new TableView<ScoutTableModel>();
		tableOfScouts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		TableColumn radioButton = new TableColumn(" ");
		radioButton.setMinWidth(100);
		radioButton.setCellValueFactory(new PropertyValueFactory<ScoutTableModel, RadioButton>("button1"));

		TableColumn serviceChargeColumn2 = new TableColumn("Scout ID");
		serviceChargeColumn2.setMinWidth(100);
		serviceChargeColumn2.setCellValueFactory(new PropertyValueFactory<ScoutTableModel, String>("scoutID"));

		TableColumn serviceChargeColumn = new TableColumn("Last Name");
		serviceChargeColumn.setMinWidth(100);
		serviceChargeColumn.setCellValueFactory(new PropertyValueFactory<ScoutTableModel, String>("lastName"));

		TableColumn accountNumberColumn = new TableColumn("First Name");
		accountNumberColumn.setMinWidth(100);
		accountNumberColumn.setCellValueFactory(new PropertyValueFactory<ScoutTableModel, String>("firstName"));

		TableColumn bookIdColumn = new TableColumn("Middle Name");
		bookIdColumn.setMinWidth(100);
		bookIdColumn.setCellValueFactory(new PropertyValueFactory<ScoutTableModel, String>("middleName"));

		TableColumn accountTypeColumn = new TableColumn("Date Of Birth");
		accountTypeColumn.setMinWidth(100);
		accountTypeColumn.setCellValueFactory(new PropertyValueFactory<ScoutTableModel, String>("dateOfBirth"));

		TableColumn balanceColumn = new TableColumn("Phone Number");
		balanceColumn.setMinWidth(100);
		balanceColumn.setCellValueFactory(new PropertyValueFactory<ScoutTableModel, String>("phoneNumber"));

		TableColumn balanceColumn2 = new TableColumn("Email");
		balanceColumn2.setMinWidth(100);
		balanceColumn2.setCellValueFactory(new PropertyValueFactory<ScoutTableModel, String>("email"));

		TableColumn balanceColumn3 = new TableColumn("Troop ID");
		balanceColumn3.setMinWidth(100);
		balanceColumn3.setCellValueFactory(new PropertyValueFactory<ScoutTableModel, String>("troopID"));

		TableColumn balanceColumn4 = new TableColumn("Status");
		balanceColumn4.setMinWidth(100);
		balanceColumn4.setCellValueFactory(new PropertyValueFactory<ScoutTableModel, String>("status"));

		TableColumn balanceColumn5 = new TableColumn("Date Status Updated");
		balanceColumn5.setMinWidth(100);
		balanceColumn5.setCellValueFactory(new PropertyValueFactory<ScoutTableModel, String>("dateStatusUpdated"));

		tableOfScouts.getColumns().addAll(radioButton, serviceChargeColumn2, balanceColumn, accountNumberColumn,
				serviceChargeColumn, bookIdColumn, accountTypeColumn, balanceColumn2, balanceColumn3, balanceColumn4,
				balanceColumn5);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(115, 150);
		scrollPane.setContent(tableOfScouts);

		cancelButton = new Button("Back");
		/** Need to change to the observer pattern
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			
			@Override
			public void handle(ActionEvent e) {

				clearErrorMessage();
				Librarian l = new Librarian();

				l.transactionDone();

			}
		});
		*/
		HBox btnContainer = new HBox(100);
		btnContainer.setAlignment(Pos.CENTER);

		btnContainer.getChildren().add(cancelButton);

		vbox.getChildren().add(grid);
		vbox.getChildren().add(scrollPane);
		vbox.getChildren().add(btnContainer);

		return vbox;
	}

	protected MessageView createStatusLog(String initialMessage) {
		statusLog = new MessageView(initialMessage);

		return statusLog;
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
	/*
	 * //-----------------------------------------------------------------------
	 * --- public void mouseClicked(MouseEvent click) { if(click.getClickCount()
	 * >= 2) { processAccountSelected(); } }
	 */

	@Override
	public void updateState(String key, Object value) {
		// TODO Auto-generated method stub

	}

}
