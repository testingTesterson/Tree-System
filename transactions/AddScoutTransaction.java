package transactions;

import java.util.Properties;

import impresario.IView;
import javafx.scene.Scene;
import model.Scout;

public class AddScoutTransaction extends Transaction {

	private String addScoutErrorMessage = "";
	private String addScoutStatusMessage = "";
	
	private Scout myScout;
	
	//----------------------------------------------------------
	protected AddScoutTransaction(String transType, Scout s) throws Exception {
		super(transType, s);
		myScout = s;
	}

	//----------------------------------------------------------
	protected void setDependencies() {
		// TODO Auto-generated method stub
		dependencies = new Properties();
		dependencies.setProperty("AddScout", "TransactionError");
		dependencies.setProperty("CancelAddScout", "CancelTransaction");
		dependencies.setProperty("UpdateScout", "TransactionError");
		dependencies.setProperty("CancelUpdateScout", "CancelTransaction");
		dependencies.setProperty("RemoveScout", "TransactionError");
		dependencies.setProperty("CancelRemoveScout", "CancelTransaction");
		dependencies.setProperty("Done", "CancelTransaction");
		
		myRegistry.setDependencies(dependencies);
	}
	
	//----------------------------------------------------------
	public void subscribe(String arg0, IView arg1) {
		// TODO Auto-generated method stub
		
	}

	//----------------------------------------------------------
	protected Scene createView() {
		// TODO Auto-generated method stub
		return null;
	}

	//----------------------------------------------------------
	public Object getState(String key) {
		if (key.equals("TransactionError"))
			return addScoutErrorMessage;
		
		if (key.equals("UpdateStatusMessage"))
			return addScoutStatusMessage;
	
		if (key.equals("Scout"))
			return myScout;
			
		return null;
	}

	//----------------------------------------------------------
	public void stateChangeRequest(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

}
