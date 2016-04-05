package views;

import application.MainStageContainer;
import impresario.IModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import userinterface.View;

public class TreeLotCoordinatorView extends View {

	//GUI Components
	protected Label commonActions;
	protected Label otherActions;
	
	protected Button sellTree;
	protected Button openShift;
	protected Button closeShift;
	
	protected Label scout;
	protected Label tree;
	protected Label treeType;
	
	protected Button addScout;
	protected Button updateScout;
	protected Button removeScout;
	
	protected Button addTree;
	protected Button updateTree;
	protected Button removeTree;
	
	protected Button addTreeType;
	protected Button updateTreeType;
	
	
	//Constructor
	public TreeLotCoordinatorView(IModel treeLotCoordinator) {
		super(treeLotCoordinator, "TreeLotCoordinatorView");
		
		VBox container = new VBox(10);
		container.setPadding(new Insets(15, 10, 10, 5));
		
		container.getChildren().add(createTitle());
		container.getChildren().add(createFormContent());
		
		getChildren().add(container);
		//myModel.subscribe("TransactionError", this); Remove?
	}

	//Creates the title of our GUI
	//----------------------------------------------------------	
	private Node createTitle() {
			
		HBox container = new HBox();
		container.setPadding(new Insets(10, 0, 0, 0));
		container.setAlignment(Pos.CENTER);
				
		//Change below if desired
		Text titleText = new Text("Boy Scout Tree Sales");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		titleText.setWrappingWidth(300);
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKOLIVEGREEN);
		container.getChildren().add(titleText);
					
		return container;
	}
	
	
	//----------------------------------------------------------
	@SuppressWarnings("unchecked")
	private VBox createFormContent() {
		//All non-title GUI stuff goes inside here
		
		VBox formContainer = new VBox(15);
		
		//I can change these to text should I chose
		commonActions = new Label("Common Actions:");
		otherActions = new Label("Other Actions:");
		
		VBox topSection = new VBox(15);
		topSection.setAlignment(Pos.CENTER_LEFT);
		topSection.setPadding(new Insets(10, 10, 10, 10));
		topSection.setStyle("-fx-border-color: black; -fx-border-style: hidden hidden solid hidden;");
		
		HBox topRow = new HBox(15);
		topRow.setAlignment(Pos.CENTER);
		topRow.setPadding(new Insets(10, 10, 10, 10));
		
		sellTree = new Button("Sell Tree");
		openShift = new Button("Open Shift");
		closeShift = new Button("Close Shift");
		/**
		 * Action Events for Sell Tree, Open Shift & Close Shift
		 * We don't need these yet
		sellTree.setOnAction(e -> {
			myModel.stateChangeRequest("SellTree", null);
		});
		
		openShift.setOnAction(e -> {
			myModel.stateChangeRequest("OpenShift", null);
		});
		
		closeShift.setOnAction(e -> {
			myModel.stateChangeRequest("CloseShift", null);
		});
		*/
		
		//Make sure to change the following Labels/Buttons to work with locale
		scout = new Label("Scout");
		tree = new Label("Tree");
		treeType = new Label("Tree Type");
		
		addScout = new Button("Add");
		updateScout = new Button("Update");
		removeScout = new Button("Remove");
		
		addTree = new Button("Add");
		updateTree = new Button("Update");
		removeTree = new Button("Remove");
		
		addTreeType = new Button("Add");
		updateTreeType = new Button("Update");
	
		//ActionEvents, note they are lambda expressions
		addScout.setOnAction(e -> {
			myModel.stateChangeRequest("AddScout", null);
		});
		
		updateScout.setOnAction(e -> {
			myModel.stateChangeRequest("UpdateScout", null);
		});
		
		removeScout.setOnAction(e -> {
			myModel.stateChangeRequest("RemoveScout", null);
		});
		
		addTree.setOnAction(e -> {
			myModel.stateChangeRequest("AddTree", null);
		});
		
		updateTree.setOnAction(e -> {
			myModel.stateChangeRequest("UpdateTree", null);
		});
		
		removeTree.setOnAction(e -> {
			myModel.stateChangeRequest("RemoveTree", null);
		});
		
		addTreeType.setOnAction(e -> {
			myModel.stateChangeRequest("AddTreeType", null);
		});
		
		updateTreeType.setOnAction(e -> {
			myModel.stateChangeRequest("UpdateTreeType", null);
		});
		
		
		////////////////////////////////////////////////////
		TitledPane scoutPane = new TitledPane();
		scoutPane.setAnimated(false);
		scoutPane.setText("Manage Scouts");
		HBox scoutHbox = new HBox(15);
		scoutHbox.setAlignment(Pos.CENTER);
		scoutHbox.setPadding(new Insets(10, 10, 20, 10));
		scoutHbox.getChildren().addAll(addScout, updateScout, removeScout);
		scoutPane.setContent(scoutHbox);
		////////////////////////////////////////////////////
		TitledPane treePane = new TitledPane();
		treePane.setAnimated(false);
		treePane.setText("Manage Trees");
		HBox treeHbox = new HBox(15);
		treeHbox.setAlignment(Pos.CENTER);
		treeHbox.setPadding(new Insets(10, 10, 20, 10));
		treeHbox.getChildren().addAll(addTree, updateTree, removeTree);
		treePane.setContent(treeHbox);
		////////////////////////////////////////////////////
		TitledPane treeTypePane = new TitledPane();
		treeTypePane.setAnimated(false);
		treeTypePane.setText("Manage Tree Types");
		HBox treeTypeHbox = new HBox(15);
		treeTypeHbox.setAlignment(Pos.CENTER);
		treeTypeHbox.setPadding(new Insets(10, 10, 20, 10));
		treeTypeHbox.getChildren().addAll(addTreeType, updateTreeType);
		treeTypePane.setContent(treeTypeHbox);
		
		
		Accordion accordion = new Accordion();
		accordion.getPanes().addAll(scoutPane, treePane, treeTypePane);
		
		//This event ensures the Accordion will resize when a panel is opened
		accordion.heightProperty().addListener(new ChangeListener() {
			@Override 
			public void changed(ObservableValue o, Object oldVal, Object newVal) {
				MainStageContainer.resizeStage();
			}
		});
		
		//Add all layouts to the form container
		topRow.getChildren().addAll(sellTree, openShift, closeShift);
		topSection.getChildren().addAll(commonActions, topRow);
		formContainer.getChildren().addAll(topSection, otherActions);
		
		formContainer.getChildren().add(accordion);
		
		return formContainer;
	}
	
	
	
	//----------------------------------------------------------
	public void updateState(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
