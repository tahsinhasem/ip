package Tasks;

import Exceptions.InvalidArgumentException;
import Exceptions.InvalidDescriptionException;
import Exceptions.InvalidFormatException;
import Utils.utils;

public class Deadline extends Task {

    public static Deadline from(String s) throws InvalidFormatException, InvalidDescriptionException, InvalidArgumentException {
        //Expects a string in the format "deadline <description> /by <deadline_date>"

        //get rid of the command
        String rest = utils.discardFirstWord(s.trim()).trim();

        String[] arr = rest.split(" ");

        int by_occurences = utils.countOccurrences(arr, "/by");

        if (by_occurences == 0 || by_occurences > 1){
            throw new InvalidFormatException("Invalid format. Follow this format: deadline <description> /by <deadline date>. Provide one and only one '/by'.");
        }

        //they will not be -1 as I have already checked for their occurences
        int by_index = utils.findIndex(arr, "/by");

        //description is from 0 to by_index
        String description = "";
        for (int i = 0; i < by_index; i++){
            description += arr[i] + " ";
        }
        description = description.trim();
        if (description.isEmpty()){
            throw new InvalidDescriptionException("The description of a deadline cannot be empty.");
        }

        String by = "";
        for (int i = by_index+1; i < arr.length; i++){
            by += arr[i] + " ";
        }
        by = by.trim();
        if (by.isEmpty()){
            throw new InvalidArgumentException("The 'by' of a deadline cannot be empty. Follow this format: deadline <description> /by <deadline date>");
        }

        return new Deadline(description, by);
    }

    private final String deadline_date;
    public Deadline(String description, String by) {
        super(description);
        this.deadline_date = by;
    }

    @Override
    public void markAsDone() {
        super.markAsDone();
    }

    @Override
    public void markAsUndone() {
        super.markAsUndone();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline_date + ")";
    }

    public String getBy() {
        return deadline_date;
    }
}
