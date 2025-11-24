import java.util.ArrayList;
import java.util.List;

public class Drone {
    private String id;
    private String modelo;
    private float capacidadeDeCarga;
    private int bateria;
    private boolean livre;
    private Localizacao localizacaoAtual;
    private List<String> historico;
    
    public Drone(String id, String modelo, float capacidadeDeCarga, 
                 int bateria, Localizacao localizacaoAtual) {
        this.id = id;
        this.modelo = modelo;
        this.capacidadeDeCarga = capacidadeDeCarga;
        this.bateria = bateria;
        this.livre = true;
        this.localizacaoAtual = localizacaoAtual;
        this.historico = new ArrayList<>();
    }
    
    public boolean verificarDisponibilidade() {
        return livre && bateria > 20 && capacidadeDeCarga > 0;
    }
    
    public boolean atualizarStatus(boolean status) {
        this.livre = status;
        return true;
    }
    
    public float calcularAutonomia() {
        return (bateria / 100.0f) * 60.0f; // 60 minutos de voo com 100% de bateria
    }
    
    public void adicionarEntregaHistorico(String entregaId) {
        historico.add(entregaId);
    }
    
    // Getters e Setters
    public String getId() { return id; }
    public String getModelo() { return modelo; }
    public float getCapacidadeDeCarga() { return capacidadeDeCarga; }
    public int getBateria() { return bateria; }
    public boolean isLivre() { return livre; }
    public Localizacao getLocalizacaoAtual() { return localizacaoAtual; }
    public List<String> getHistorico() { return historico; }
    
    public void setBateria(int bateria) { this.bateria = bateria; }
    public void setLocalizacaoAtual(Localizacao localizacaoAtual) { 
        this.localizacaoAtual = localizacaoAtual; 
    }
}