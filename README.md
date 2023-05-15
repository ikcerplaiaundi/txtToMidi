# txtToMidi
translate "melody.txt" to "melody.midi".
This is a Java program that reads a melody in text format from a file, and converts it to a MIDI sequence that can be played by a MIDI synthesizer. The program uses the javax.sound.midi package to create and manipulate MIDI events and sequences.

The main method reads the filename, initial tempo and note velocity from variables, creates a new sequence and track, and initializes variables for note and duration values. Then it reads the melody file line by line, splits the note and duration values for each line, converts them to MIDI values using helper methods, creates MIDI note events for each note, adds them to the track, and saves the MIDI sequence to a file. The program also includes helper methods to map note names to MIDI pitch values and to calculate the duration of a note in milliseconds based on the tempo.
