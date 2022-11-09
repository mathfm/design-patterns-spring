package dio.springcurso.gof.service.impl;

import dio.springcurso.gof.model.Cliente;
import dio.springcurso.gof.model.ClienteRepository;
import dio.springcurso.gof.model.Endereco;
import dio.springcurso.gof.model.EnderecoRepository;
import dio.springcurso.gof.service.ClienteService;
import dio.springcurso.gof.service.ViaCepService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, ViaCepService viaCepService) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
    }
    private final ViaCepService viaCepService;
    private final EnderecoRepository enderecoRepository;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarCliente(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null); // TODO
    }

    @Override
    public void insertCliente(Cliente cliente) {
        salvarClienteCep(cliente);
    }

    private void salvarClienteCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
           Endereco novoEndereco = viaCepService.consultarCep(cep);
           enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    public void atualizarCliente(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()){
            salvarClienteCep(cliente);
        }
    }

    @Override
    public void removerCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
