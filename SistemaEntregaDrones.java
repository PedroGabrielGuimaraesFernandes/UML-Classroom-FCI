import java.time.LocalDateTime;
import java.util.List;

public class SistemaEntregaDrones {
    private GerenciadorDrones gerenciadorDrones;
    
    public SistemaEntregaDrones() {
        this.gerenciadorDrones = new GerenciadorDrones();
        inicializarSistema();
    }
    
    private void inicializarSistema() {
        // Inicializar alguns drones para teste
        Localizacao base = new Localizacao(-23.5505, -46.6333); // São Paulo
        gerenciadorDrones.adicionarDrone(new Drone("DR001", "DJI Mavic 3", 5.0f, 100, base));
        gerenciadorDrones.adicionarDrone(new Drone("DR002", "DJI Phantom 4", 3.5f, 85, base));
        gerenciadorDrones.adicionarDrone(new Drone("DR003", "Autel Evo II", 4.0f, 45, base));
        
        System.out.println("Sistema de entrega com drones inicializado!");
    }
    
    public void processarPedido(Usuario usuario, Endereco destino, float peso) {
        System.out.println("\n=== PROCESSANDO NOVO PEDIDO ===");
        
        // 1. Cliente faz pedido
        Pedido pedido = usuario.solicitarEntrega(destino, peso);
        System.out.println("Pedido recebido: " + pedido.getId());
        
        // 2. Verifica disponibilidade de drones
        Drone drone = gerenciadorDrones.alocarDrone();
        if (drone == null) {
            System.out.println("Nenhum drone disponível no momento!");
            gerenciadorDrones.tratarDroneIndisponivel();
            return;
        }
        
        pedido.setDroneAssociado(drone);
        System.out.println("Drone designado: " + drone.getId());
        
        // 3. Prepara entrega
        pedido.atualizarStatus("EM_PREPARACAO");
        System.out.println("Preparando entrega...");
        
        // 4. Drone decola
        pedido.atualizarStatus("EM_VOO");
        System.out.println("Drone decolou!");
        
        // 5. Simulação de entrega
        try {
            Thread.sleep(2000); // Simula tempo de voo
            pedido.atualizarStatus("EM_ENTREGA");
            System.out.println("Drone em entrega...");
            
            Thread.sleep(1000);
            pedido.atualizarStatus("ENTREGUE");
            drone.adicionarEntregaHistorico(pedido.getId());
            System.out.println("Entrega concluída com sucesso!");
            
        } catch (InterruptedException e) {
            System.out.println("Falha durante a entrega!");
            gerenciadorDrones.tratarFalhaEntrega();
        }
        
        // 6. Drone retorna
        drone.atualizarStatus(true);
        System.out.println("Drone retornou à base");
    }
    
    public static void main(String[] args) {
        SistemaEntregaDrones sistema = new SistemaEntregaDrones();
        
        // Criar usuário de teste
        Endereco enderecoUsuario = new Endereco(
            "END001", "São Paulo", "SP", "01001-000", 
            "Av. Paulista", "1000", "Apto 101"
        );
        
        Usuario usuario = new Usuario(
            "USER001", "João Silva", "joao@email.com", 
            "11999999999", enderecoUsuario
        );
        
        // Criar endereço de destino
        Endereco destino = new Endereco(
            "END002", "São Paulo", "SP", "01310-000", 
            "Rua Augusta", "500", "Sala 201"
        );
        
        // Processar pedido
        sistema.processarPedido(usuario, destino, 2.5f);
        
        // Monitorar frota
        sistema.gerenciadorDrones.monitorarFrota();
        
        // Consultar histórico
        List<Pedido> historico = usuario.consultarHistorico();
        System.out.println("\n=== HISTÓRICO DO USUÁRIO ===");
        for (Pedido pedido : historico) {
            System.out.printf("Pedido: %s | Status: %s | Data: %s%n",
                pedido.getId(), pedido.getStatus(), pedido.getDataPedido());
        }
    }
}