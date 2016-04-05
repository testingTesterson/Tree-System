package model;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JFrame;
import exception.InvalidPrimaryKeyException;
import database.*;
import impresario.IView;
import userinterface.MainStageContainer;
import userinterface.View;
import userinterface.ViewFactory;
import javafx.*;
import model.EntityBase;
import userinterface.AddScoutView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Scout extends EntityBase implements IView {
	private static final String myTableName = "Scout";
	protected Properties dependencies;
	private String updateStatusMessage = "";
	protected Stage myStage;
	
	protected TreeLotCoordinator myTLC;
	
	public Scout(String scoutID) throws InvalidPrimaryKeyException {
		super(myTableName);

		String query = "SELECT * FROM " + myTableName + " WHERE (ScoutID = " + scoutID + ")";

		Vector allDataRetrieved = getSelectQueryResult(query);

		// You must get one account at least
		if (allDataRetrieved != null) {
			int size = allDataRetrieved.size();

			// There should be EXACTLY one account. More than that is an error
			if (size != 1) {
				throw new InvalidPrimaryKeyException("Multiple accounts matching id : " + scoutID + " found.");
			} else {
				// copy all the retrieved data into persistent state
				Properties retrievedAccountData = (Properties) allDataRetrieved.elementAt(0);
				persistentState = new Properties();

				Enumeration allKeys = retrievedAccountData.propertyNames();
				while (allKeys.hasMoreElements() == true) {
					String nextKey = (String) allKeys.nextElement();
					String nextValue = retrievedAccountData.getProperty(nextKey);

					if (nextValue != null) {
						persistentState.setProperty(nextKey, nextValue);
					}
				}

			}
		}
		// If no account found for this user name, throw an exception
		else {
			throw new InvalidPrimaryKeyException("No account matching id : " + scoutID + " found.");
		}
	}

	private void setDependencies() {
		dependencies = new Properties();

		myRegistry.setDependencies(dependencies);
	}

	public Scout(Properties props) {
		super(myTableName);
		setDependencies();
		setData(props);

		persistentState = new Properties();
		Enumeration allKeys = props.propertyNames();
		while (allKeys.hasMoreElements() == true) {
			String nextKey = (String) allKeys.nextElement();
			String nextValue = props.getProperty(nextKey);

			if (nextValue != null) {
				persistentState.setProperty(nextKey, nextValue);
			}
		}
	}

	public Scout(TreeLotCoordinator tlc) {
		super(myTableName);
		myStage = MainStageContainer.getInstance();
		myTLC = tlc;
		persistentState = new Properties();
	}

	public void setData(Properties props) {
		Enumeration allKeys = props.propertyNames();
		while (allKeys.hasMoreElements() == true) {
			String nextKey = (String) allKeys.nextElement();
			String nextValue = props.getProperty(nextKey);

			if (nextValue != null) {
				persistentState.setProperty(nextKey, nextValue);
			}
		}
	}

	public Object getState(String key) {
		{
			if (key.equals("UpdateStatusMessage") == true)
				return updateStatusMessage;

			return persistentState.getProperty(key);
		}
	}

	protected void initializeSchema(String tableName) {
		if (mySchema == null) {
			mySchema = getSchemaInfo(myTableName);
		}
	}

	public void stateChangeRequest(String key, Object value) {
		{

			myRegistry.updateSubscribers(key, this);
		}

		/** Called via the IView relationship */

	}

	public void updateState(String key, Object value) {
		stateChangeRequest(key, value);
	}

	public void updateStateInDatabase() {

		try {
			if (persistentState.getProperty("scoutID") != null) {

				Properties whereClause = new Properties();
				whereClause.setProperty("scoutID", persistentState.getProperty("scoutID"));
				updatePersistentState(mySchema, persistentState, whereClause);
				updateStatusMessage = "Scout data for ScoutID : " + persistentState.getProperty("scoutID")
						+ " updated successfully in database!";
				System.out.println(updateStatusMessage);
			} else {

				Integer scoutID = insertAutoIncrementalPersistentState(mySchema, persistentState);
				persistentState.setProperty("scoutID", "" + scoutID.intValue());
				updateStatusMessage = "Scout data for new scout : " + persistentState.getProperty("scoutID")
						+ " installed successfully in database!";
				System.out.println(updateStatusMessage);

			}
		} catch (SQLException ex) {
			updateStatusMessage = "Error in installing scout data in database!";
		}
		// DEBUG System.out.println("updateStateInDatabase " +
		// updateStatusMessage);
	}

	public static int compare(Scout a, Scout b) {
		String aNum = (String) a.getState("scoutID");
		String bNum = (String) b.getState("scoutID");

		return aNum.compareTo(bNum);
	}

	// ------------------------------------------------------
	public String toString() {
		return persistentState.toString();
	}

	public void createAndShowScoutView() {
		Scene currentScene = (Scene) myViews.get("ScoutView");

		if (currentScene == null) {
			// create our initial view
			View newView = new AddScoutView(myTLC); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("ScoutView", currentScene);
		}

		swapToView(currentScene);

	}

	public Vector<String> getEntryListView() {
		Vector<String> v = new Vector<String>();

		v.addElement(persistentState.getProperty("scoutID"));
		v.addElement(persistentState.getProperty("lastName"));
		v.addElement(persistentState.getProperty("firstName"));
		v.addElement(persistentState.getProperty("middleName"));
		v.addElement(persistentState.getProperty("dateOfBirth"));
		v.addElement(persistentState.getProperty("phoneNumber"));
		v.addElement(persistentState.getProperty("email"));
		v.addElement(persistentState.getProperty("troopID"));
		v.addElement(persistentState.getProperty("status"));
		v.addElement(persistentState.getProperty("dateStatusUpdated"));

		return v;
	}

}
