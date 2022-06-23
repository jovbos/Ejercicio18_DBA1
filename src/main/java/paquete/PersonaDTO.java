package paquete;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;


public record PersonaDTO(Integer id, String usuario, String password, String name, String surname, String company_email, String personal_email, String city, Boolean active, Date created_date, String image_url, Date termination_date) {


    public PersonaDTO(Integer id, String usuario, String password, String name, String surname, String company_email, String personal_email, String city, Boolean active, Date created_date, String image_url, Date termination_date) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.company_email = company_email;
        this.personal_email = personal_email;
        this.city = city;
        this.active = active;
        this.created_date = created_date;
        this.image_url = image_url;
        this.termination_date = termination_date;
    }

    public Integer id() {
        return this.id;
    }

//    public void newId(Integer id) {
//        this.id=id;
//    }
//

    public String usuario() {
        return this.usuario;
    }


    public String password() {
        return this.password;
    }

    public String name() {
        return this.name;
    }

    public String surname() {
        return this.surname;
    }

    public String company_email() {
        return this.company_email;
    }

    public String personal_email() {
        return this.personal_email;
    }

    public String city() {
        return this.city;
    }


    public Boolean active() {
        return this.active;
    }


    public Date created_date() {
        return this.created_date;
    }


    public String image_url() {
        return this.image_url;
    }


    public Date termination_date() {
        return this.termination_date;
    }


}
