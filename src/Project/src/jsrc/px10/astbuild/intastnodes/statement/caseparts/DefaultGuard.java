package jsrc.px10.astbuild.intastnodes.statement.caseparts;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 11:37:46 AM
 */
public class DefaultGuard extends SwitchGuard {
	private static DefaultGuard theInstance = new DefaultGuard();

    public static DefaultGuard instance() {
		return theInstance;
	}

	private DefaultGuard() {
	}
}
