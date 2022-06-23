package paquete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {

    @Autowired
    PersonaRepositorio personaRepositorio;

    @Autowired
    PersonaRepositorioImp criteriaRepository;

    public PersonaDTO addPersona(PersonaDTO personaDTO) throws Exception {
        if (personaDTO.usuario() == null || personaDTO.password() == null || personaDTO.name() == null || personaDTO.company_email() == null
        ||personaDTO.personal_email() == null ||personaDTO.city() == null ||personaDTO.active() == null ||personaDTO.created_date() == null){throw new Exception("Faltan campos imprescindibles");}
        if (personaDTO.usuario().length() > 10){throw new Exception("El campo usuario no puede contener mas de 10 digitos");}
        Persona persona = new Persona() ;
        persona.setName(personaDTO.name());
        persona.setUsuario(personaDTO.usuario());
        persona.setImage_url(personaDTO.image_url());
        persona.setTermination_date(personaDTO.termination_date());
        persona.setActive(personaDTO.active());
        persona.setCreated_date(personaDTO.created_date());
        persona.setCity(personaDTO.city());
        persona.setPersonal_email(personaDTO.personal_email());
        persona.setSurname(personaDTO.surname());
        persona.setPassword(personaDTO.password());
        persona.setCompany_email(personaDTO.company_email());
        personaRepositorio.save(persona);

        PersonaDTO output = new PersonaDTO(persona.getId(), persona.getUsuario(), persona.getPassword(), persona.getName(), persona.getSurname(), persona.getCompany_email(), persona.getPersonal_email(), persona.getCity(), persona.getActive(), persona.getCreated_date(), persona.getImage_url(), persona.getTermination_date());
        return output;
    }

    public Persona findPersona(Integer id) throws Exception {

        return personaRepositorio.findById(id).orElseThrow(() -> new Exception("Not found"));
    }

    public List<PersonaDTO> findUsuario(String usuario){

        List<Persona> personaList = personaRepositorio.findByUsuario(usuario);
        List<PersonaDTO> personaDTOList = new ArrayList<>();

        personaList.forEach(persona -> {
            PersonaDTO personaDTO = new PersonaDTO(persona.getId(), persona.getUsuario(), persona.getPassword(), persona.getName(), persona.getSurname(), persona.getCompany_email(), persona.getPersonal_email(), persona.getCity(), persona.getActive(), persona.getCreated_date(), persona.getImage_url(), persona.getTermination_date());
            personaDTOList.add(personaDTO);
        });
        return personaDTOList;
    }

    public List<PersonaDTO> findAll() {
        Iterable<Persona> personaList = personaRepositorio.findAll();
        List<PersonaDTO> personaDTOList = new ArrayList<>();

        personaList.forEach(persona -> {
            PersonaDTO personaDTO = new PersonaDTO(persona.getId(), persona.getUsuario(), persona.getPassword(), persona.getName(), persona.getSurname(), persona.getCompany_email(), persona.getPersonal_email(), persona.getCity(), persona.getActive(), persona.getCreated_date(), persona.getImage_url(), persona.getTermination_date());
            personaDTOList.add(personaDTO);
        });
        return personaDTOList;
    }
    public void deletePersona(Persona persona) {
        personaRepositorio.delete(persona);
    }

    public PersonaDTO updatePersona(Persona persona, PersonaDTO personaDTO) {


        persona.setUsuario(personaDTO.usuario());
        persona.setImage_url(personaDTO.image_url());
        persona.setTermination_date(personaDTO.termination_date());
        persona.setActive(personaDTO.active());
        persona.setCreated_date(personaDTO.created_date());
        persona.setCity(personaDTO.city());
        persona.setPersonal_email(personaDTO.personal_email());
        persona.setSurname(personaDTO.surname());
        persona.setPassword(personaDTO.password());
        persona.setCompany_email(personaDTO.company_email());
        personaRepositorio.save(persona);

        PersonaDTO output = new PersonaDTO(persona.getId(), persona.getUsuario(), persona.getPassword(), persona.getName(), persona.getSurname(), persona.getCompany_email(), persona.getPersonal_email(), persona.getCity(), persona.getActive(), persona.getCreated_date(), persona.getImage_url(), persona.getTermination_date());
        return output;
    }

    public Page<Persona> getCriteriaPersonas(PersonaPage personaPage, SearchCriteria searchCriteria) {

        return criteriaRepository.findAllFilter(personaPage, searchCriteria);
    }


}
