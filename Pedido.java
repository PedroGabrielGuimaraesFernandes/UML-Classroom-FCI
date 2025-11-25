import java.time.LocalDateTime;

public class Pedido {
    private String id;
    private String usuarioId;
    private Endereco origem;
    private Endereco destino;
    private float peso;
    private LocalDateTime dataPedido;
    private LocalDateTime dataEntrega;
    private Drone droneAssociado;
    private boolean entregue;
    private String status;
    
    public Pedido(String id, String usuarioId, Endereco origem, Endereco destino, 
                  float peso, LocalDateTime dataPedido) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
        this.dataPedido = dataPedido;
        this.entregue = false;
        this.status = "RECEBIDO";
    }
    
    public boolean validarPeso() {
        return peso > 0 && peso <= 10.0f; // máximo 10kg
    }
    
    public float calcularCusto() {
        float custoBase = 15.0f;
        float custoPeso = peso * 2.0f;
        return custoBase + custoPeso;
    }
    
    public boolean atualizarStatus(String novoStatus) {
        this.status = novoStatus;
        
        if ("ENTREGUE".equals(novoStatus)) {
            this.entregue = true;
            this.dataEntrega = LocalDateTime.now();
        }
        
        return true;
    }
    
    // Getters e Setters
    public String getId() { return id; }
    public String getUsuarioId() { return usuarioId; }
    public Endereco getOrigem() { return origem; }
    public Endereco getDestino() { return destino; }
    public float getPeso() { return peso; }
    public LocalDateTime getDataPedido() { return dataPedido; }
    public LocalDateTime getDataEntrega() { return dataEntrega; }
    public Drone getDroneAssociado() { return droneAssociado; }
    public boolean isEntregue() { return entregue; }
    public String getStatus() { return status; }
    
    public void setDroneAssociado(Drone droneAssociado) { 
        this.droneAssociado = droneAssociado; 
    }
    public void setDataEntrega(LocalDateTime dataEntrega) { 
        this.dataEntrega = dataEntrega; 
    }
}