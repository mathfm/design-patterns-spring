package dio.springcurso.gof.controller;

import dio.springcurso.gof.model.Cliente;
import dio.springcurso.gof.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {
    private final ClienteService clienteService;

    public ClienteRestController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos(){
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorCliente(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.buscarCliente(id));
    }
    @PostMapping
    public ResponseEntity<Cliente> insertCliente(@RequestBody Cliente cliente){
        clienteService.insertCliente(cliente);
        return ResponseEntity.ok(cliente);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        clienteService.atualizarCliente(id, cliente);
        return ResponseEntity.ok(clienteService.buscarCliente(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> removerCliente(@PathVariable Long id){
        clienteService.removerCliente(id);
        return ResponseEntity.ok().build();

    }

}
