package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.residence.Residence;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Residence> PREDICATE_SHOW_ALL_RESIDENCES = unused -> true;
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' residence tracker file path.
     */
    Path getResidenceTrackerFilePath();

    /**
     * Sets the user prefs' residence tracker file path.
     */
    void setResidenceTrackerFilePath(Path residenceTrackerFilePath);

    /**
     * Replaces address book data with the data in {@code residenceTracker}.
     */
    void setResidenceTracker(ReadOnlyResidenceTracker residenceTracker);

    /** Returns the ResidenceTracker */
    ReadOnlyResidenceTracker getResidenceTracker();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasResidence(Residence residence);

    /**
     * Deletes the given person.
     * The person must exist in the residence tracker.
     */
    void deleteResidence(Residence target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the residence tracker.
     */
    void addResidence(Residence residence);

    /**
     * Replaces the given residence {@code target} with {@code editedResidence}.
     * {@code target} must exist in the residence tracker.
     * The residence identity of {@code editedPerson} must not be the same as another existing residence in the
     * residence tracker.
     */

    void setResidence(Residence target, Residence editedResidence);

    /** Returns an unmodifiable view of the filtered residence list */
    ObservableList<Residence> getFilteredResidenceList();

    //Add just to prevent breaking
    ObservableList<Person> getFilteredPersonList();


    /**
     * Updates the filter of the filtered residence list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredResidenceList(Predicate<Residence> predicate);
}
