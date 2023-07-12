package Codebase;

public class Passenger extends Person
{
    private String email;
    private String phoneNumber;
    private String cardNumber;
    private int securityCode;
    private String passport;

    public Passenger(){}

    public Passenger(String firstName, String secondName, int age, String gender,String email, String phoneNumber, String passport, String cardNumber,int securityCode)
    {
        super(firstName, secondName, age, gender);
        this.setSecurityCode(securityCode);
        this.setCardNumber(cardNumber);
        this.setPassport(passport);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null|| email.trim().equals("") ){
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+\\.com$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    public String getFirstName() {
        return super.getFirstName();
    }

    public String getSecondName() {
        return super.getSecondName();
    }

    public void setSecondName(String secondName) {
        super.setSecondName(secondName);
    }

    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    public String getPassport() {
        return passport;
    }

    public void setGender(String gender) {
        super.setGender(gender);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        if(cardNumber == null|| cardNumber.trim().equals("") ){
            throw new IllegalArgumentException("cardNumber cannot be empty");
        }
        this.cardNumber = cardNumber;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public void setAge(int age) {
        super.setAge(age);
    }

    public void setPassport(String passport) {
        if (passport.length() == 0) {
            throw new IllegalArgumentException("Passport number cannot be empty");
        } else if (passport.length() > 9 ){
            throw new IllegalArgumentException("Passport number should not be more than 9 characters long");
        } else {
            this.passport = passport;
        }
    }

    @Override
    public String getGender() {
        return super.getGender();
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 0) {
            throw new IllegalArgumentException("phoneNumber cannot be empty");
        }
        if (phoneNumber.matches("^(?:\\+61 |0)[4-5]\\d{8}$"))
            this.phoneNumber = phoneNumber;
        else throw new IllegalArgumentException("Invalid phone number");
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public String toString()
    {
        return "Passenger{" + " Fullname= "+ super.getFirstName()+" "+super.getSecondName()+
                " ,email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passport='" + passport +
                '}';
    }
}
