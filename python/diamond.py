
class Diamond:

	def __init__(self, middle_letter):
		self.middle_letter = middle_letter

		self.letter_sequence = []
		self.indents = []
		self.between = []
		
		# establish the letter sequence
		seq = []
		for c in range(ord('A'),ord(self.middle_letter)+1):
			seq.append(chr(c))
		for c in seq[-2::-1]:
			seq.append(c)
		self.letter_sequence = seq
		
		# establish the indentation pattern
		seq = []
		max_indent = ord(self.middle_letter)-ord('A')
		# range down to 0 (inclusive)
		for i in range(max_indent,-1,-1):
			seq.append(i)
		for i in seq[-2::-1]:
			seq.append(i)
		self.indents = seq
		
		# establish spaces between pattern
		seq =[]
		# instead of creating a new value for max_offset,
		# I'm using max_indent as it represents the same value
		for b in range(max_indent+1):
			seq.append(b if b==0 else b*2-1)
		for b in seq[-2::-1]:
			seq.append(b)
		self.between = seq
		
	def one_row(self,letter,indent,between):
		row = ""
		row += " " * indent
		row += letter
		row += " " * between
		if between > 0: row += letter
		return row
	
	def rows(self):
		rows = []
		for i in range(self.letter_sequence.__len__()):
			rows.append(self.one_row(self.letter_sequence[i], 
									self.indents[i], 
									self.between[i]))
		return rows
	
	def print_diamond(self):
		diamond = ""
		for row in self.rows():
			diamond += row+"\n"
		diamond = diamond[:-1]
		return diamond

if __name__ == "__main__":
	import sys

	if len(sys.argv) > 1:
		middle_letter = sys.argv[1][0]
		print Diamond(middle_letter).print_diamond()
	else:
		print "please supply one argument: the char of the diamond middle"