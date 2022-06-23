package paquete;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Persona {

    //Las anotaciones @NonNull están comentadas ya que la validación está realizada por otro método.

    @Id
    @GeneratedValue
    @Column(name="ID")
    private Integer id;

    @Column(name="Usuario")
    private String usuario;

//    @NonNull
    @Column(name="Password")
    private String password;

//    @NonNull
    @Column(name="Name")
    private String name;

    @Column(name="Surname")
    private String surname;

//    @NonNull
    @Column(name="Company_Email")
    private String company_email;

//    @NonNull
    @Column(name="Personal_Email")
    private String personal_email;

//    @NonNull
    @Column(name="City")
    private String city;

//    @NonNull
    @Column(name="Active")
    private Boolean active;

//    @NonNull
    @Column(name="Created_Date")
    private Date created_date;

    @Column(name="Image_url")
    private String image_url;

    @Column(name="Termination_Date")
    private Date termination_date;


}

