package userinterface;

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

public class LanguageSelectionView extends View {

	//GUI Components
	protected Button english;
	protected Button french;
	
	
	//Constructor
	public LanguageSelectionView(IModel treeLotCoordinator) {
		super(treeLotCoordinator, "LanguageSelectionView");
		
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
		container.setPadding(new Insets(10, 10, 10, 10));
		container.setAlignment(Pos.CENTER);
				
		//Change below if desired
		Text titleText = new Text("Select a Language\n[Select a Language French]");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		titleText.setWrappingWidth(300);
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKOLIVEGREEN);
		container.getChildren().add(titleText);
					
		return container;
	}
	
	
	//----------------------------------------------------------
	private VBox createFormContent() {
		//All non-title GUI stuff goes inside here
		
		VBox formContainer = new VBox(15);
		formContainer.setPadding(new Insets(10, 10, 40, 10));
		
		HBox buttons = new HBox(15);
		buttons.setPadding(new Insets(10, 10, 10, 10));
		buttons.setAlignment(Pos.CENTER);
		
		english = new Button("English");
		french = new Button("French");
		
		buttons.getChildren().addAll(english, french);
		formContainer.getChildren().add(buttons);
		
		return formContainer;
	}
	
	
	
	//----------------------------------------------------------
	public void updateState(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
