package paquete;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controlador {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/addPersona")
    public PersonaDTO addPersona(@RequestBody PersonaDTO personaDTO) throws Exception {
        PersonaDTO output = personaService.addPersona(personaDTO);

        return output;
    }

    @PutMapping("/updatePersona/{id}")
    public PersonaDTO updatePersona(@PathVariable("id") Integer id, @RequestBody PersonaDTO personaDTO) throws Exception {
        Persona persona = personaService.findPersona(id);
        PersonaDTO output = personaService.updatePersona(persona, personaDTO);
        return output;
    }

    @DeleteMapping("/deletePersona/{id}")
    public String deletePersona(@PathVariable("id") Integer id) throws Exception{
        Persona persona = personaService.findPersona(id);
        personaService.deletePersona(persona);
        return "Persona borrada";
    }

    @GetMapping("/getPersona/findId/{id}")
    public PersonaDTO getPersonaId(@PathVariable("id") Integer id) throws Exception{
        PersonaDTO output = modelMapper.map(personaService.findPersona(id), PersonaDTO.class);
        return output;
    }

    @GetMapping("/getPersona/findUser/{usuario}")
    public List<PersonaDTO> getPersonaId(@PathVariable("usuario") String usuario) throws Exception{
        return personaService.findUsuario(usuario);
    }

    @GetMapping("/getPersona/findAll")
    public List<PersonaDTO> getAll() {
        return personaService.findAll();
    }

    @GetMapping("/getPersona/filter")
    public Page<Persona> getPersonaFilter(PersonaPage personaPage, SearchCriteria searchCriteria) {

        return personaService.getCriteriaPersonas(personaPage, searchCriteria);
    }
}

