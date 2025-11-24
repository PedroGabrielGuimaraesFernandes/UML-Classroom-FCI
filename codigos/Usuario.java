import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;
    private Endereco endereco;
    private List<Pedido> historicoPedidos;
    
    public Usuario(String id, String nome, String email, String telefone, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataCadastro = LocalDateTime.now();
        this.endereco = endereco;
        this.historicoPedidos = new ArrayList<>();
    }
    
    public boolean validarDados() {
        return nome != null && !nome.isEmpty() &&
               email != null && email.contains("@") &&
               telefone != null && telefone.matches("\\d{10,11}");
    }
    
    public Pedido solicitarEntrega(Endereco destino, float peso) {
        Pedido pedido = new Pedido(
            "PED" + System.currentTimeMillis(),
            this.id,
            this.endereco,
            destino,
            peso,
            LocalDateTime.now()
        );
        
        historicoPedidos.add(pedido);
        return pedido;
    }
    
    public List<Pedido> consultarHistorico() {
        return new ArrayList<>(historicoPedidos);
    }
    
    // Getters e Setters
    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public Endereco getEndereco() { return endereco; }
    public List<Pedido> getHistoricoPedidos() { return historicoPedidos; }
}