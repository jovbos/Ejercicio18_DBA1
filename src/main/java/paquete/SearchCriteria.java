package paquete;

import lombok.Data;

import java.util.Date;

@Data
public class SearchCriteria {

    private String usuario;

    private String name;

    private String surname;

    private Date created_date;

    private String condition;
}
