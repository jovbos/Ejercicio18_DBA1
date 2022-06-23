package paquete;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class PersonaPage {

    private Integer pageNumber = 0;

    private Integer pageSize = 10;

    private Sort.Direction sortDirection = Sort.Direction.ASC;

    private String sortBy = "name";


}
