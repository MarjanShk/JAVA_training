package addressbook.model;

public class ContactData {
    private int id;
    private String name;
    private String middlename;
    private String secondname;
    private String homePhoneNumber;
    private String email;
    private String group;

    public ContactData(int id, String name, String middlename, String secondname, String homePhoneNumber, String email, String group) {
        this.id = id;
        this.name = name;
        this.middlename = middlename;
        this.secondname = secondname;
        this.homePhoneNumber = homePhoneNumber;
        this.email = email;
        this.group = group;
    }

    public ContactData(String name, String middlename, String secondname, String homePhoneNumber, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.middlename = middlename;
        this.secondname = secondname;
        this.homePhoneNumber = homePhoneNumber;
        this.email = email;
        this.group = group;
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

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondname='" + secondname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return secondname != null ? secondname.equals(that.secondname) : that.secondname == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (secondname != null ? secondname.hashCode() : 0);
        return result;
    }
}
