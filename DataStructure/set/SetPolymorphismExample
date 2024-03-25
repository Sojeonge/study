package set_ex;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetPolymorphismExample {
	public static void main(String[] args) {
		Set<String> hashSet = new HashSet<>();
		Set<String> linkedHashSet = new LinkedHashSet<>();
		Set<String> treeSet = new TreeSet<>();

		addElements(hashSet);
		addElements(linkedHashSet);
		addElements(treeSet);

		printSet(hashSet);
		printSet(linkedHashSet);
		printSet(treeSet);
	}

	public static void addElements(Set<String> set) {
		set.add("One");
		set.add("Two");
		set.add("Three");
	}

	public static void printSet(Set<String> set) {
		System.out.println(set.getClass().getSimpleName() + ": " + set);
	}
}

