package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.cheese.ExpiryDate;
import seedu.address.model.cheese.ManufactureDate;
import seedu.address.model.cheese.MaturityDate;
import seedu.address.model.customer.Address;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.customer.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Customer[] getSampleCustomers() {
        return new Customer[]{
            new Customer(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Customer(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Customer(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Customer(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Customer(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Customer(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Customer sampleCustomer : getSampleCustomers()) {
            sampleAb.addCustomer(sampleCustomer);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Cheese[] getSampleCheeses() {
        return new Cheese[]{
            new Cheese(new CheeseType("Brie"), new ManufactureDate("2021-03-10"),
                new MaturityDate("2021-04-10"), new ExpiryDate("2022-04-10")),
            new Cheese(new CheeseType("Gouda"), new ManufactureDate("2021-03-10"),
                new MaturityDate("2021-05-10"), new ExpiryDate("2022-05-10")),
            new Cheese(new CheeseType("Parmesan"), new ManufactureDate("2021-03-10"),
                new MaturityDate("2021-04-10"), new ExpiryDate("2022-04-10")),
        };
    }
}
