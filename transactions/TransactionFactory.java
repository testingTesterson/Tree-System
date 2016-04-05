package transactions;

import impresario.IModel;

public class TransactionFactory {

	public static Transaction createScoutTransaction(String transType, IModel moddel) throws Exception {

		Transaction trans = null;

		if (transType.equals("AddScout")) {
			trans = new ScoutTransaction(String);
		}

		else if (transType.equals("UpdateScout")) {
			trans = new UpdateRemoveScoutTransaction("update");
		}

		else if (transType.equals("RemoveScout")) {
			trans = new UpdateRemoveScoutTransaction("remove");
		}
		return trans;
	}

	public static Transaction createTreeTransaction(String transType) throws Exception {

		Transaction trans = null;

		if (transType.equals("AddTree")) {
			trans = new AddTreeTransaction();
		}

		else if (transType.equals("UpdateTree")) {
			trans = new UpdateRemoveTreeTransaction("update");
		}

		else if (transType.equals("RemoveTree")) {
			trans = new UpdateRemoveTreeTransaction("remove");
		}

		return trans;
	}

	public static Transaction createTreeTypeTransaction(String transType) throws Exception {

		Transaction trans = null;

		if (transType.equals("AddTreeType")) {
			trans = new AddTreeTypeTransaction();
		}

		else if (transType.equals("UpdateTreeType")) {
			trans = new UpdateTreeTypeTransaction();
		}
		return trans;
	}

}
