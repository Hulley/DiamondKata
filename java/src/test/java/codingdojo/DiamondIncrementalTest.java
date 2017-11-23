package test.java.codingdojo;

// import org.junit.Ignore;
import org.junit.Test;

import main.java.codingdojo.Diamond;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * These test cases can be used to test-drive a solution to the diamond kata, in an incremental manner.
 *
 * Instructions:
 * 1. Make the first test case for Diamond A pass
 * 2. remove the '@Ignore' and enable the next test case.
 * 3. Uncomment the one line of the test case. Make it pass. Repeat until all lines are uncommented
 * 4. When all the lines of code in the test case are passing, enable the next test case.
 * 5. When all the test cases in this file are uncommented and passing, you should have a full working solution.
 */
public class DiamondIncrementalTest {

    @Test
    public void DiamondA_has_one_line_containing_A() {
        assertEquals("A", Diamond.print('A'));
    }

    @Test
    public void LetterSequence_is_list_of_letters_on_each_line_of_the_diamond() {
        assertCharacterSequence(new Diamond('A').getLetterSequence(), 'A');
        assertCharacterSequence(new Diamond('B').getLetterSequence(), 'A', 'B', 'A');
        assertCharacterSequence(new Diamond('C').getLetterSequence(), 'A', 'B', 'C', 'B', 'A');
        assertCharacterSequence(new Diamond('D').getLetterSequence(), 'A', 'B', 'C', 'D', 'C', 'B', 'A');
    }

    @Test
    public void IndentationSequence_is_a_list_of_indents_for_each_line_of_the_diamond() {
        assertIntegerSequence(new Diamond('A').getIndentationSequence(), 0);
        assertIntegerSequence(new Diamond('B').getIndentationSequence(), 1,0,1);
        assertIntegerSequence(new Diamond('C').getIndentationSequence(), 2,1,0,1,2);
        assertIntegerSequence(new Diamond('D').getIndentationSequence(), 3,2,1,0,1,2,3);
    }

    @Test
    public void BetweenSequence_is_a_list_of_how_many_spaces_there_are_between_repeated_letters_on_each_line_of_the_diamond() {
        assertIntegerSequence(new Diamond('A').getBetweenSequence(), 0);
        assertIntegerSequence(new Diamond('B').getBetweenSequence(), 0,1,0);
        assertIntegerSequence(new Diamond('C').getBetweenSequence(), 0,1,3,1,0);
        assertIntegerSequence(new Diamond('D').getBetweenSequence(), 0,1,3,5,3,1,0);
    }

    @Test
    public void OneRow_is_a_list_of_chars_in_one_diamond_row() {
        assertCharacterSequence(new Diamond('A').getOneRow('A', 0, 0), 'A');
        assertCharacterSequence(new Diamond('B').getOneRow('A', 1, 0), ' ', 'A');
        assertCharacterSequence(new Diamond('B').getOneRow('B', 0, 1), 'B', ' ', 'B');
        assertCharacterSequence(new Diamond('D').getOneRow('C', 1, 3), ' ', 'C', ' ', ' ', ' ', 'C');
    }

    @Test
    public void Rows_is_a_list_of_diamond_rows() {
        List<List<Character>> rows = new Diamond('B').getRows();
        assertCharacterSequence(rows.get(0), ' ', 'A');
        assertCharacterSequence(rows.get(1), 'B', ' ', 'B');
        assertCharacterSequence(rows.get(2), ' ', 'A');
    }

    @Test
    public void print_DiamondC() {
        assertEquals("  A\n" +
                     " B B\n" +
                     "C   C\n" +
                     " B B\n" +
                     "  A", Diamond.print('C'));
    }

    @Test
    public void print_DiamondD() {
            assertEquals("   A\n" +
                         "  B B\n" +
                         " C   C\n" +
                         "D     D\n" +
                         " C   C\n" +
                         "  B B\n" +
                         "   A", Diamond.print('D'));
    }
    
    @Test
    public void getNumberOfRows_is_number_of_diamond_rows() {
    	assert(new Diamond('A').getNumberOfRows() == 1);
    	assert(new Diamond('B').getNumberOfRows() == 3);
    	assert(new Diamond('C').getNumberOfRows() == 5);
    	assert(new Diamond('D').getNumberOfRows() == 7);
    }

    @Test
    public void lower_case_input_results_in_diamond_of_corresponding_capital() {
    	assertDiamondsEqual(new Diamond('a').getRows(), new Diamond('A').getRows());
    	assertDiamondsEqual(new Diamond('b').getRows(), new Diamond('B').getRows());
    	assertDiamondsEqual(new Diamond('c').getRows(), new Diamond('C').getRows());
    	assertDiamondsEqual(new Diamond('d').getRows(), new Diamond('D').getRows());

    }
    public void assertDiamondsEqual(List<List<Character>> first_d, List<List<Character>> second_d) {
    	assertArrayEquals(first_d.toArray(),second_d.toArray());
    }

    public void assertCharacterSequence(List<Character> sequence, Character... expectedChars) {
        assertArrayEquals(expectedChars, sequence.toArray());
    }

    public void assertIntegerSequence(List<Integer> sequence, Integer... expectedInts) {
        assertArrayEquals(expectedInts, sequence.toArray());
    }
}
