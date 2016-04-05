package model;

import java.util.Hashtable;
import java.util.Properties;

import event.Event;
import impresario.IModel;
import impresario.IView;
import impresario.ModelRegistry;
import javafx.scene.Scene;
import javafx.stage.Stage;
import transactions.Transaction;
import transactions.TransactionFactory;
import userinterface.TreeLotCoordinatorView;
import userinterface.View;
import userinterface.ViewFactory;
import userinterface.WelcomeView;
import userinterface.WindowPosition;

/**
 * TreeLotCoordinator - Main interface agent to the tree sales system
 * 
 * @author Amanda
 *
 */

public class TreeLotCoordinator implements IView, IModel {

	// Impresario Required:
	private Properties dependencies;
	private ModelRegistry myRegistry;

	// GUI Related:
	private Stage myStage;
	private Hashtable<String, Scene> myViews;
	
	private String transactionErrorMessage = "";

	// ----------------------------------------------------------
	// Constructor
	public TreeLotCoordinator() {
		myStage = MainStageContainer.getInstance();
		myViews = new Hashtable<String, Scene>();

		// Create the Registry Object, not required if inheriting from
		// EntityBase
		myRegistry = new ModelRegistry("TreeLotCoordinator");
		if (myRegistry == null) {
			new Event(Event.getLeafLevelClassName(this), "TreeLotCoordinator", "Could not instantiate Registry",
					Event.ERROR);
		}

		setDependencies();
		createAndShowWelcomeView();
		//createAndShowTreeLotCoordinatorView(); //This is just for testing
	}

	// -----------------------------------------------------------------------------------
	private void setDependencies() {
		dependencies = new Properties();
		// Not sure what to actually put here
		// dependencies.setProperty("Login", "LoginError");
		// dependencies.setProperty("Deposit", "TransactionError");
		myRegistry.setDependencies(dependencies);
	}

	// -----------------------------------------------------------------------------------
	public Object getState(String arg0) {
		// What values go inside here?
		return null;
	}

	// -----------------------------------------------------------------------------------
	public void stateChangeRequest(String key, Object value) {
		if (key.equals("TreeLotCoordinatorView")) {
			createAndShowTreeLotCoordinatorView();
		}
		else {
			String transType = key;
			doTransaction(transType);
		}
			myRegistry.updateSubscribers(key, this);
	}

	// -----------------------------------------------------------------------------------
	
	public void doTransaction(String type) {
		try
		{
			//Transaction trans = TransactionFactory.createTransaction(type);

			//trans.subscribe("CancelTransaction", this);
//			trans.stateChangeRequest("DoYourJob", "");
		}
		catch (Exception ex)
		{
			transactionErrorMessage = "FATAL ERROR: TRANSACTION FAILURE: Unrecognized transaction!!";
			new Event(Event.getLeafLevelClassName(this), "createTransaction",
					"Transaction Creation Failure: Unrecognized transaction " + ex.toString(),
					Event.ERROR);
		}
	}

	// -----------------------------------------------------------------------------------
	public void updateState(String arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	// -----------------------------------------------------------------------------------
	public void subscribe(String key, IView subscriber) {
		myRegistry.subscribe(key, subscriber);
	}

	// -----------------------------------------------------------------------------------
	public void unSubscribe(String key, IView subscriber) {
		myRegistry.unSubscribe(key, subscriber);
	}

	// -----------------------------------------------------------------------------
	private void createAndShowTreeLotCoordinatorView() {
		Scene currentScene = (Scene) myViews.get("TreeLotCoordinatorView");

		if (currentScene == null) {
			// View newView = ViewFactory.createView("TreeLotCoordinatorView",
			// this);
			View newView = new TreeLotCoordinatorView(this); // Replace this
																// when we have
																// a factory
			currentScene = new Scene(newView);
			myViews.put("TreeLotCoordinatorView", currentScene);
		}
		swapToView(currentScene);
	}

	///////////////////////////////////////////////////////////////////////////////
	// POSSIBLY CHANGE THIS
	// -----------------------------------------------------------------------------
	private void createAndShowWelcomeView() {
		Scene currentScene = (Scene) myViews.get("WelcomeView");

		if (currentScene == null) {
			// View newView = ViewFactory.createView("TreeLotCoordinatorView",
			// this);
			View newView = new WelcomeView(this); // Replace this when
															// we have a factory
			currentScene = new Scene(newView);
			myViews.put("WelcomeView", currentScene);
		}
		swapToView(currentScene);
	}
	////////////////////////////////////////////////////////////////////////////////

	// -----------------------------------------------------------------------------
	public void swapToView(Scene newScene) {
		if (newScene == null) {
			System.out.println("TreeLotCoordinator.swapToView(): Missing view for display");
			new Event(Event.getLeafLevelClassName(this), "swapToView", "Missing view for display ", Event.ERROR);
			return;
		}

		myStage.setScene(newScene);
		myStage.sizeToScene();

		// Place in center
		WindowPosition.placeCenter(myStage);
	}

}
