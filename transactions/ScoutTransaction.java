package transactions;

import application.Scout;
import impresario.IView;
import javafx.scene.Scene;

public class ScoutTransaction extends Transaction {

	private String addScoutErrorMessage = "";
	private String addScoutStatusMessage = "";
	
	private Scout myScout;
	
	
	protected ScoutTransaction(String transType, Object model) throws Exception {
		super(transType, model);
		// TODO Auto-generated constructor stub
	}

	
	public void subscribe(String arg0, IView arg1) {
		// TODO Auto-generated method stub
		
	}

	protected void setDependencies() {
		// TODO Auto-generated method stub
		
	}

	protected Scene createView() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getState(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public void stateChangeRequest(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

}
