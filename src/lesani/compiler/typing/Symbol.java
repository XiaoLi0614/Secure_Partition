package lesani.compiler.typing;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class Symbol {
	// If intern() is faster than a normal map with String domain.
	// Converting Strings to Symbols and then mapping from Symbols pays off.
	public static Symbol symbol(String s) {
		String sIntern = s.intern();
		return new Symbol(sIntern);
	}

	private String name;
	private Symbol(String n) { name = n; }
	public String toString() { return name; }

	public boolean equals(Object obj) {
		//System.out.println("Called");
		return (obj instanceof Symbol) && (name == ((Symbol) obj).name);
	}

	public int hashCode() {
		return name.hashCode();
	}
}

/*
// Old:
public class symbol {
	private static HashMap<String, symbol> map = new HashMap<String, symbol>();
	public static symbol symbol(String n) {
		String u = n.intern();
		symbol s = map.get(u);
		if (s == null) { s = new symbol(u); map.put(u, s); }
		return s;
	}

	private String name;
	private symbol(String n) { name = n; }
	public String toString() { return name; }

}
*/

