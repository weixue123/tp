package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommandStub;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCheesesCommand;
import seedu.address.logic.commands.ListCustomersCommand;
import seedu.address.logic.commands.ListOrdersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.CustomerIdStub;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.NameContainsKeywordsPredicate;
import seedu.address.testutil.CustomerBuilder;
import seedu.address.testutil.CustomerUtil;
import seedu.address.testutil.EditCustomerDescriptorBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Customer customer = new CustomerBuilder().withId(CustomerIdStub.getNextId()).build();
        AddCommand command = (AddCommand) parser.parseCommand(CustomerUtil.getAddCommand(customer));
        assertEquals(new AddCommand(customer), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_CUSTOMER.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_CUSTOMER), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Customer customer = new CustomerBuilder().withId(CustomerIdStub.getNextId()).build();
        EditCommand.EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder(customer).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CUSTOMER.getOneBased() + " " + CustomerUtil.getEditCustomerDescriptorDetails(descriptor));
        command = new EditCommandStub(command, descriptor);
        assertEquals(new EditCommand(INDEX_FIRST_CUSTOMER, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listCustomers() throws Exception {
        assertTrue(parser.parseCommand(ListCustomersCommand.COMMAND_WORD) instanceof ListCustomersCommand);
        assertTrue(parser.parseCommand(ListCustomersCommand.COMMAND_WORD + " 3") instanceof ListCustomersCommand);
    }

    @Test
    public void parseCommand_listCheeses() throws Exception {
        assertTrue(parser.parseCommand(ListCheesesCommand.COMMAND_WORD) instanceof ListCheesesCommand);
        assertTrue(parser.parseCommand(ListCheesesCommand.COMMAND_WORD + " 3") instanceof ListCheesesCommand);
    }

    @Test
    public void parseCommand_listOrders() throws Exception {
        assertTrue(parser.parseCommand(ListOrdersCommand.COMMAND_WORD) instanceof ListOrdersCommand);
        assertTrue(parser.parseCommand(ListOrdersCommand.COMMAND_WORD + " 3") instanceof ListOrdersCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
