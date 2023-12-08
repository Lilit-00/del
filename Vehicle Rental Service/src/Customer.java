import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {
 /*   @Serial
    private static final long serialVersionUID = -450981020981188647L;*/
    private String name;
    private String email;
    private  int numberLicense;

    public Customer(String name, String email,int numberLicense){
        this.name = name;
        this.email = email;
        this.numberLicense= numberLicense;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email;
    }

  /*  public boolean equals(Customer customer){
        return (Objects.equals(this.name, customer.getName()) && Objects.equals(this.email, customer.getEmail()));

    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}