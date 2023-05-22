package midi;
import java.io.*;
import javax.sound.midi.*;
public class sera {
    public static void main(String[] args) {
        // Ruta del archivo de texto
        String filename = "melody.txt";

        // Tempo inicial y velocidad de la nota
        int tempo = 120;
        int velocity = 1;

        try {
            // Leer el archivo de texto
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            // Crear una secuencia MIDI
            Sequence sequence = new Sequence(Sequence.PPQ, 255);

            // Crear una pista MIDI
            Track track = sequence.createTrack();

            // Variables para las notas y duraciones
            long noteValue = 0;
            long durationValue = 0;

            // Leer el archivo de texto línea por línea
            String line;
            while ((line = reader.readLine()) != null) {
                // Separar las notas y duraciones en cada línea
                String[] noteData = line.split(" ");
                for (String nd : noteData) {
                    // Obtener la nota y duración
                    String note = nd.substring(0, 1);
                    String duration = nd.substring(1);

                    // Convertir la nota y duración a valores MIDI
                    int pitch = getPitch(note);
                    if (pitch != -1) {
                        long durationMs = getDuration(duration, tempo);
                    

                        // Crear eventos de nota MIDI y añadirlos a la pista
                        track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, pitch, velocity), noteValue));
                        noteValue += durationMs;
                        track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 0, pitch, velocity), noteValue));
                    } else {
                        System.out.println("Nota inválida: " + note);
                    }
                    
                   
                }
            }

            // Guardar la secuencia MIDI en un archivo
            File midiFile = new File("melody.mid");
            MidiSystem.write(sequence, 1, midiFile);

            // Cerrar el lector
            reader.close();

            System.out.println("Archivo MIDI generado exitosamente.");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo de texto.");
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            System.out.println("Error al crear la secuencia MIDI.");
            e.printStackTrace();
        }
    }

    // Método para obtener el valor MIDI de una nota
    private static int getPitch(String note) {
        switch (note.toUpperCase()) {
            case "C":
                return 60;
            case "D":
                return 62;
            case "E":
                return 64;
            case "F":
                return 65;
            case "G":
                return 67;
            case "A":
                return 69;
            case "B":
                return 71;
            default:
                return -1;
        }
    }

    // Método para obtener la duración de una nota en milisegundos
    private static long getDuration(String duration, int tempo) {
        long value = 0;
        if (duration.length() >= 1) {
            value = Long.parseLong(duration);
        }
        long durationMs = (long) (60000.0 / tempo * value);
        return durationMs;
    }
}