package dio.springcurso.gof.service;

import dio.springcurso.gof.model.Cliente;

public interface ClienteService {

    Iterable<Cliente> buscarTodos();

    Cliente buscarCliente(Long id);

    void insertCliente(Cliente cliente);

    void atualizarCliente(Long id,Cliente cliente);

    void removerCliente(Long id);
}
