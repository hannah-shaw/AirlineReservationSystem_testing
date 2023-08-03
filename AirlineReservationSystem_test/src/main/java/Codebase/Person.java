package Codebase;
public abstract class Person //abstract class Person
{
    private String firstName;
    private String secondName;
    private int age;
    private String gender;

    public Person(){}

    public Person(String firstName, String secondName, int age, String gender){
        this.setFirstName(firstName);
        this.setSecondName(secondName);
        this.setAge(age);
        this.setGender(gender);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age <= 0) {
            throw new IllegalArgumentException("Age should be positive");
        }
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if(gender == null || gender.trim().equals("") ) {
            throw new IllegalArgumentException("Gender cannot be empty");
        }
        else if (!gender.equals("Man") && !gender.equals("Woman") && !gender.equals("Non-binary|gender diverse")
                && !gender.equals("Prefer not to say") && !gender.equals("Other")) {
            throw new IllegalArgumentException("Invalid gender field value");
        }
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setFirstName(String firstName) {
        if(firstName.trim().equals("") || firstName == null){
            throw new IllegalArgumentException("Firstname cannot be empty");
        }
        if(!firstName.matches("^[a-zA-Z]*$")) {
            throw new IllegalArgumentException("Firstname should not start with a number or symbol and can contain only lower-case and upper-case alphabet letters");
        }
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        if(secondName.trim().equals("") || secondName == null){
            throw new IllegalArgumentException("secondName cannot be empty");
        }
        if(!secondName.matches("^[a-zA-Z]*$")) {
            throw new IllegalArgumentException("secondName should not start with a number or symbol and can contain only lower-case and upper-case alphabet letters");
        }
        this.secondName = secondName;
    }
}
