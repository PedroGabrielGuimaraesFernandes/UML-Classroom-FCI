import java.util.ArrayList;
import java.util.List;

public class GerenciadorDrones {
    private List<Drone> frota;
    
    public GerenciadorDrones() {
        this.frota = new ArrayList<>();
    }
    
    public void adicionarDrone(Drone drone) {
        frota.add(drone);
    }
    
    public Drone encontrarDroneDisponivel() {
        for (Drone drone : frota) {
            if (drone.verificarDisponibilidade()) {
                return drone;
            }
        }
        return null;
    }
    
    public void monitorarFrota() {
        System.out.println("=== MONITORAMENTO DA FROTA ===");
        for (Drone drone : frota) {
            System.out.printf("Drone %s - Bateria: %d%% - Disponível: %s%n",
                drone.getId(), drone.getBateria(), drone.isLivre() ? "Sim" : "Não");
        }
    }
    
    public Drone alocarDrone() {
        Drone drone = encontrarDroneDisponivel();
        if (drone != null) {
            drone.atualizarStatus(false);
        }
        return drone;
    }
    
    public void verificarBateria() {
        for (Drone drone : frota) {
            if (drone.getBateria() < 20) {
                System.out.println("ALERTA: Drone " + drone.getId() + " com bateria baixa!");
            }
        }
    }
    
    public void tratarDroneIndisponivel() {
        System.out.println("Tratando drone indisponível...");
        // Lógica para realocar entrega ou notificar usuário
    }
    
    public void tratarFalhaEntrega() {
        System.out.println("Tratando falha de entrega...");
        // Lógica para tratar falhas na entrega
    }
    
    public List<Drone> getFrota() {
        return frota;
    }
}