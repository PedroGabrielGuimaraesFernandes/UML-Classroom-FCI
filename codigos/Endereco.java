public class Endereco {
    private String id;
    private String cidade;
    private String estado;
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    
    public Endereco(String id, String cidade, String estado, String cep, 
                   String rua, String numero, String complemento) {
        this.id = id;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }
    
    public boolean validarEndereco() {
        return cidade != null && !cidade.isEmpty() &&
               estado != null && !estado.isEmpty() &&
               cep != null && cep.matches("\\d{5}-\\d{3}") &&
               rua != null && !rua.isEmpty() &&
               numero != null && !numero.isEmpty();
    }
    
    // Getters e Setters
    public String getId() { return id; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getCep() { return cep; }
    public String getRua() { return rua; }
    public String getNumero() { return numero; }
    public String getComplemento() { return complemento; }
}