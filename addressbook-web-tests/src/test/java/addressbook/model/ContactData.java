package addressbook.model;

public class ContactData {
    private  String name;
    private  String middlename;
    private  String secondname;
    private  String homePhoneNumber;
    private  String email;

    public ContactData(String name, String middlename, String secondname, String homePhoneNumber, String email) {
        this.name = name;
        this.middlename = middlename;
        this.secondname = secondname;
        this.homePhoneNumber = homePhoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getSecondname() {
        return secondname;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
