package comp9024.ivanteong;

public class Main {

	/**
	 *
	 * @author Hui Wu
	 */

	public static void main(String[] args) throws Exception {
		/**
		 * Construct a trie named trie1
		 */
		CompressedSuffixTrie trie1 = new CompressedSuffixTrie("file1");

		System.out.println("ACTTCGTAAG is at: " + trie1.findString("ACTTCGTAAG"));

		System.out.println("AAAACAACTTCG is at: " + trie1.findString("AAAACAACTTCG"));

		System.out.println("ACTTCGTAAGGTT : " + trie1.findString("ACTTCGTAAGGTT"));

		System.out.println(CompressedSuffixTrie.similarityAnalyser("file2", "file3", "file4"));
	}

}