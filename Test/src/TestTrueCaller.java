import Exceptions.ImmutablePhonebookExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTrueCaller {
    private Caller caller;
    private Phonebook phonebook;

    @BeforeEach
    public void setUp() {
        caller = new Caller("PhoneNumber");
        phonebook = new Phonebook();
    }

    @Test
    public void testAddName() {
        caller.addName("Name");
        assertEquals("name", caller.getMostCommonName());
    }

    @Test
    public void testGetMostCommonNameWhenNoNamesAdded() {
        assertEquals("", caller.getMostCommonName());
    }

    @Test
    public void testAddMultipleDifferentNames() {
        caller.addName("Chiamie");
        caller.addName("Onoja");
        caller.addName("Bruno");

        String mostCommon = caller.getMostCommonName();
        assertTrue(mostCommon.equalsIgnoreCase("Chiamie") ||
                mostCommon.equalsIgnoreCase("Onoja") ||
                mostCommon.equalsIgnoreCase("Bruno"));
    }


    @Test
    public void testThatYouGetMostCommonName() {
        caller.addName("Sub-Name");
        caller.addName("Name");
        caller.addName("Name");
        caller.addName("Name");
        assertEquals("name", caller.getMostCommonName());
    }

    @Test
    public void testThatRegardlessOfCaseSenitivityItIsSavedAsOneName() {
        caller.addName("Chiamaka");
        caller.addName("CHIAMAKA");
        caller.addName("chiamaka");

        assertEquals(3, caller.getNameCount("chiamaka"));
    }

    @Test
    public void testThatYouCanGetPhoneNumber() {
        assertEquals("PhoneNumber", caller.getPhoneNumber());
    }

    @Test
    public void testThatNumberIsntSpamIfNotLoggedAsSpam() {
        assertFalse(caller.isSpam());
    }

    @Test
    public void testThatNumberIsSpamIfLoggedAsSpam() {
        caller.markAsSpam();
        assertTrue(caller.isSpam());
    }

    @Test
    public void testPhoneNumberRemainsConstant() {
        caller.addName("Name");
        caller.markAsSpam();
        assertEquals("PhoneNumber", caller.getPhoneNumber());
    }

    @Test
    public void testThatYouCanAddAContactThatDoesntExist() {
        phonebook.addCaller("PhoneNumber");
        assertEquals("PhoneNumber", caller.getPhoneNumber());
    }

    @Test
    public void testThatGetCallersReturnsAllAddedCallers(){
        phonebook.addCaller("PhoneNumber");
        phonebook.addCaller("PhoneNumber2");
        phonebook.addCaller("PhoneNumber3");

        phonebook.getCallers();

        assertEquals(3, phonebook.getCallers().size());
    }

    @Test
    public void testThatGetCallersReturnsUnmodifiableCopy() {
        phonebook.addCaller("PhoneNumber");
        phonebook.getCallers();

        assertEquals(1, phonebook.getCallers().size());
        assertThrows(ImmutablePhonebookExceptions.class, () -> {
            phonebook.put("PhoneNumber", new Caller("PhoneNumber"));
        });
    }

}
