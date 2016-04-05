package model;

import java.awt.Event;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JFrame;
import database.*;
import exception.InvalidPrimaryKeyException;
import impresario.IView;
import userinterface.ScoutCollectionView;

import userinterface.MainStageContainer;

import userinterface.View;
import userinterface.ViewFactory;
import javafx.*;
import javafx.scene.Scene;
import model.EntityBase;
import java.sql.SQLException;
import java.util.Properties;
import model.EntityBase;

public class ScoutCollection extends EntityBase implements IView {

	private static final String myTableName = "Scout";

	private Vector<Scout> scout;
	protected TreeLotCoordinator myTLC;

	public ScoutCollection() {
		super(myTableName);

		scout = new Vector<Scout>();
	}

	public ScoutCollection(TreeLotCoordinator tlc) {
		super(myTableName);
		myStage = MainStageContainer.getInstance();
		myTLC = tlc;
		persistentState = new Properties();
	}

	public void findScoutsWithFirstNameLike(String name) {

		String query = "SELECT * FROM " + myTableName + " WHERE firstName LIKE '%" + name + "%'";

		Vector allDataRetrieved = getSelectQueryResult(query);

		if (allDataRetrieved != null) {

			for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++) {
				Properties nextAccountData = (Properties) allDataRetrieved.elementAt(cnt);

				Scout scout = new Scout(nextAccountData);

				if (scout != null) {
					addScout(scout);
				}
			}

		}
	}

	public Object getState(String key)

	{
		if (key.equals("Scout"))
			return scout;
		else if (key.equals("ScoutList"))
			return this;
		return null;
	}

	protected void initializeSchema(String tableName) {
		// TODO Auto-generated method stub
		if (mySchema == null) {
			mySchema = getSchemaInfo(tableName);
		}
	}

	public void stateChangeRequest(String key, Object value) {
		myRegistry.updateSubscribers(key, this);
	}

	private void addScout(Scout s) {
		// accounts.add(a);
		int index = findIndexToAdd(s);
		scout.insertElementAt(s, index); // To build up a collection sorted on
											// some key
	}

	private int findIndexToAdd(Scout s) {
		// users.add(u);
		int low = 0;
		int high = scout.size() - 1;
		int middle;

		while (low <= high) {
			middle = (low + high) / 2;

			Scout midSession = scout.elementAt(middle);

			int result = Scout.compare(s, midSession);

			if (result == 0) {
				return middle;
			} else if (result < 0) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}

		}
		return low;
	}

	public void updateState(String key, Object value) {
		stateChangeRequest(key, value);
	}

	// ------------------------------------------------------------------
	public void display() {
		for (int i = 0; i < scout.size(); i++) {
			System.out.println(scout.get(i).toString());
		}
	}

	public void createAndShowScoutCollectionView() {
		Scene currentScene = (Scene) myViews.get("ScoutCollectionView");

		if (currentScene == null) {
			// create our initial view
			View newView = new ScoutCollectionView(this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("ScoutCollectionView", currentScene);
		}

		swapToView(currentScene);

	}

}
