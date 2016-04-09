package addressbook.generators;

import addressbook.model.GroupData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Admin on 06.04.2016.
 */
public class GenerateGroups {

    @Parameter(names = "-c", description = "Groups count")
    int count;

    @Parameter(names = "-f", description = "Target file")
    String file;

    @Parameter(names = "-d", description = "Data format")
    String format;

    public static void main(String[] args) throws IOException {

        GenerateGroups generator = new GenerateGroups();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();

    }

    private void run() throws IOException {
        ArrayList<GroupData> groups = generateGroups(count);
        if (format.equalsIgnoreCase("csv")) {
            saveGroupsAsCSV(groups, new File(file));
        } else if (format.equalsIgnoreCase("xml")) {
            saveGroupsAsXML(groups, new File(file));
        } else if (format.equalsIgnoreCase("json")) {
            saveGroupsAsJSON(groups, new File(file));
        } else {
            System.out.println("Format not recognized" + format);
        }
    }

    private void saveGroupsAsJSON(ArrayList<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveGroupsAsXML(ArrayList<GroupData> groups, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        try (Writer writer = new FileWriter(file)) {
            String xml = xStream.toXML(groups);
            writer.write(xml);
        }
    }

    private static void saveGroupsAsCSV(ArrayList<GroupData> groups, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (GroupData group : groups) {
                writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
            }
        }
    }

    private static ArrayList<GroupData> generateGroups(int count) {
        ArrayList<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName("test_" + i).withHeader("header_" + i).withFooter("footer_" + i));
        }
        return groups;
    }
}
