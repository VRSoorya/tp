package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Name;
import seedu.address.model.booking.Phone;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.residence.ResidenceAddress;
import seedu.address.model.residence.ResidenceName;
import seedu.address.model.tag.CleanStatusTag;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndex} into two {@code Index} and returns it as an Index array.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index[] parseTwoIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        String[] spiltIndex = trimmedIndex.split("\\s+");
        assert (spiltIndex.length == 2);
        if ((!StringUtil.isNonZeroUnsignedInteger(spiltIndex[0]))
                || (!StringUtil.isNonZeroUnsignedInteger(spiltIndex[1]))) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        Index residenceIndex = Index.fromOneBased(Integer.parseInt(spiltIndex[0]));
        Index bookingIndex = Index.fromOneBased(Integer.parseInt(spiltIndex[1]));
        Index[] indexArray = {residenceIndex, bookingIndex};
        return indexArray;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ResidenceName parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!ResidenceName.isValidResidenceName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new ResidenceName(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseVisitorName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!ResidenceName.isValidResidenceName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static ResidenceAddress parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!ResidenceAddress.isValidResidenceAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new ResidenceAddress(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String bookingDetails} into an {@code Booking}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code booking} is invalid.
     */
    public static Booking parseBooking(Name visitorName, Phone phone,
                                       String start, String end) throws ParseException {
        requireNonNull(start);
        requireNonNull(end);
        try {
            LocalDate startTime = LocalDate.parse(start.trim(), DateTimeFormatter.ofPattern("ddMMuu"));
            LocalDate endTime = LocalDate.parse(end.trim(), DateTimeFormatter.ofPattern("ddMMuu"));
            if (!Booking.isValidBookingTime(startTime, endTime)) {
                throw new ParseException(Booking.MESSAGE_CONSTRAINTS);
            }
            return new Booking(visitorName, phone, startTime, endTime);
        } catch (Exception exception) {
            throw new ParseException("Date is not in the expected format: DDMMYY");
        }
    }

    /**
     * Parses a {@code String clean status(y or n)} into a {@code CleanStatusTag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code cleanstatus} is invalid.
     */
    public static CleanStatusTag parseCleanStatusTag(String cleanStatus) throws ParseException {
        requireNonNull(cleanStatus);
        String trimmedTag = cleanStatus.trim();
        if (!CleanStatusTag.isValidCleanStatusTag(trimmedTag)) {
            throw new ParseException(CleanStatusTag.getMessageConstraints());
        }
        return new CleanStatusTag(trimmedTag);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
