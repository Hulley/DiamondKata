package main.java.codingdojo;

import java.util.ArrayList;
import java.util.List;

public class Diamond {

    private final char middleLetter;
    private final List<List<Character>> rows;

    // iterative implementation
    public Diamond(char middleLetter) {
    	// user input is not checked and edge cases not handled. 
    	// To demonstrate one edge case and appropriate handling I
    	// created the unit test:
    	// lower_case_input_results_in_diamond_of_corresponding_capital()
    	
    	// convert any user input to uppercase
    	middleLetter = Character.toUpperCase(middleLetter);
    	
        this.middleLetter = middleLetter;
        this.rows = new ArrayList<List<Character>>();
        
        List<Character> letterSequence = getLetterSequence();
        List<Integer> indentationSequence = getIndentationSequence();
        List<Integer> betweenSequence = getBetweenSequence();
        
        int numRows = getNumberOfRows();
        
        for(int i=0; i<numRows; i++) {
        	List<Character> row = getOneRow(
        			letterSequence.get(i),
        			indentationSequence.get(i),
        			betweenSequence.get(i));
        	rows.add(row);
        }
    }

    // incremental implementation
//    public Diamond(char middleLetter) {
//    	this.middleLetter = middleLetter;
//    	this.rows = new ArrayList<List<Character>>();
//    	
//    	// starting out with the max indentation for 'A'
//    	int indentForRow = (middleLetter - 'A');
//    	// and the minimum space between (none)
//    	int betweenForRow = 0;
//    	
//    	// this loop handles the top half of the diamond (exluding the middle row)
//    	for(char c = 'A'; c < middleLetter; c++) {
//    		List<Character> row = new ArrayList<Character>();
//    		// add indentation
//    		for(int i = 0; i<indentForRow; i++) row.add(' ');
//    		// add letter
//    		row.add(c);
//    		// add between spaces
//    		for(int b = 0; b<betweenForRow; b++) row.add(' ');
//    		// add repeated letter if needed
//    		if(betweenForRow>0) row.add(c);
//    		rows.add(row);
//    		
//    		// increment the indentation down by one
//    		indentForRow--;
//    		// increment the space between according to place in diamond
//    		// this check only results true for the first row
//    		if(c=='A') betweenForRow++;
//    		else betweenForRow +=2;
//    	}
//    	// this loop handles the bottom half of the diamond (including the middle row)
//    	for(char c = middleLetter; c >= 'A'; c--) {
//    		List<Character> row = new ArrayList<Character>();
//    		// add indentation
//    		for(int i = 0; i<indentForRow; i++) row.add(' ');
//    		// add letter
//    		row.add(c);
//    		// add between spaces
//    		for(int b = 0; b<betweenForRow; b++) row.add(' ');
//    		// add repeated letter if needed
//    		if(betweenForRow>0) row.add(c);
//    		rows.add(row);
//    		
//    		// increment the indentation up by one
//    		indentForRow++;
//    		// increment the space between according to place in diamond
//    		// this check only results true for the second to last row
//    		if(c=='B') betweenForRow--;
//    		else betweenForRow -=2;
//    	}
//    }

    public List<List<Character>> getRows() {
    	return rows;
    }

    public static String print(char middleLetter) {
        Diamond diamond = new Diamond(middleLetter);
        StringBuffer result = new StringBuffer();
        // rows counter, to count up to numRows maximum
        int numRows = 2*(middleLetter - 'A') +1;
        int row = 0;
        for (List<Character> chars : diamond.getRows()) {
            for (Character c: chars) {
                result.append(c);
            }
            row++;
            // when row == numRows (last row) do not print a newline 
            if(row<numRows) result.append('\n');
        }
        return result.toString();
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            System.out.println(Diamond.print(args[0].charAt(0)));
        } else {
            System.out.println("please supply one argument: the char of the diamond middle");
        }
    }

    public int getNumberOfRows() {
    	return 2*(middleLetter - 'A') +1;
    }

    // The following methods correspond to the iterative approach
    /**
     * Gets the letter for each line of a diamond.
     * @return - A sequence that represents a letter for each line of the diamond.
     */
	public List<Character> getLetterSequence() {
		List<Character> letterSequrence = new ArrayList<Character>();
		// stepping up from A add each letter to the sequence, not including the middle letter
		for(char c = 'A'; c < middleLetter; c++) letterSequrence.add(c);
		// then from the middle letter (inclusive) step back down to A
		for(char c = middleLetter; c>='A'; c--) letterSequrence.add(c);
		return letterSequrence;
	}

	/**
	 * Gets the indentation required for each line of a diamond.
	 * @return - A sequence that represents an indentation for each line in the diamond. 
	 */
	public List<Integer> getIndentationSequence() {
		List<Integer> indentationSequence = new ArrayList<Integer>();
		int maxIndentation = (middleLetter - 'A');
		// stepping down from the max indentation 0 not included
		for(int i = maxIndentation; i>0; i--) indentationSequence.add(i);
		// then from 0 (inclusive) step back down to the max indentation
		for(int i=0; i<=maxIndentation; i++) indentationSequence.add(i);
		return indentationSequence;
	}

	/**
	 * Gets the spaces between repeated letters on each line of a diamond.
	 * @return - A sequence that represents the spaces between repeated letters on each line.
	 */
	public List<Integer> getBetweenSequence() {
		List<Integer> betweenSequence = new ArrayList<Integer>();
		int maxOffset = (middleLetter - 'A');
		for(int i=0;i<maxOffset;i++) betweenSequence.add(i==0?i:2*i-1);
		for(int i=maxOffset;i>=0;i--) betweenSequence.add(i==0?i:2*i-1);
		return betweenSequence;
	}

	/**
	 * Gets the printout of one line of a diamond.
	 * @param c - The character printed on the line
	 * @param i - The indentation of the line
	 * @param j - The spaces between repeated letters
	 * @return - A sequence that represents the output 'string'
	 */
	public List<Character> getOneRow(char c, int i, int j) {
		List<Character> oneRow = new ArrayList<Character>();
		for(int in=0; in<i; in++) oneRow.add(' ');
		oneRow.add(c);
		for(int jn=0; jn<j; jn++) oneRow.add(' ');
		if(j>0) oneRow.add(c);
		return oneRow;
	}
}
