package addressbook.generators;

import addressbook.model.ContactData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Admin on 09.04.2016.
 */
public class GenerateContacts {

    @Parameter(names = "-c", description = "Count of group")
    int count;
    @Parameter(names = "-f", description = "Target file")
    String file;

    public static void main(String[] args) throws IOException {
        GenerateContacts generator = new GenerateContacts();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }catch (Exception ex){
            jCommander.usage();
            return;
        }
        generator.run();

    }
    private void run() throws IOException {
        ArrayList<ContactData> contacts = generateContacts(count);
        save(contacts, new File(file));
    }

    private static void save(ArrayList<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        Gson json = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String contactsToJson = json.toJson(contacts);
        writer.write(contactsToJson);
        writer.close();
    }

    private static ArrayList<ContactData> generateContacts(int count) {
        ArrayList<ContactData> contactDatas = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            contactDatas.add(new ContactData().
                    withName("name_"+i).withMiddlename("middle_"+i).withSecondname("second_"+i).
                    withHomePhone("093345678"+i).withMobilePhone("093112233"+i).withWorkPhone("095445566"+i).
                    withEmail("example1"+i+"@gmail.com").withEmail2("example2"+i+"@gmail.com").withEmail3("example3"+i+"@gmail.com").
                    withGroup("name88").withAddress("City street №"+i ).withGroup("testGroup").withPhoto("src/test/resources/photo.png"));
        }
        return contactDatas;
    }
}
