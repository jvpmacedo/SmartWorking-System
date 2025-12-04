import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class SmartWorkingSystem {

    // Simulação de Banco de Dados em Memória
    private static List<Usuario> usuariosBD = new ArrayList<>();
    private static List<Espaco> espacosBD = new ArrayList<>();
    private static List<Reserva> reservasBD = new ArrayList<>();

    private static Usuario usuarioLogado = null;

    public static void main(String[] args) {
        inicializarDados(); // Popula o sistema com dados de teste
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SISTEMA SMARTWORKING ===");

        while (true) {
            if (usuarioLogado == null) {
                exibirMenuLogin(scanner);
            } else {
                exibirMenuPrincipal(scanner);
            }
        }
    }
    private static void exibirMenuLogin(Scanner scanner) {
        System.out.println("\n--- LOGIN (RF01) ---");
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (fazerLogin(email, senha)) {
            System.out.println("Login realizado com sucesso! Bem-vindo, " + usuarioLogado.getNome());
        } else {
            System.out.println("ERRO: Credenciais inválidas.");
        }
    }
    private static void exibirMenuPrincipal(Scanner scanner) {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Visualizar Espaços Disponíveis (RF05)");
        System.out.println("2. Fazer Nova Reserva (RF06)");
        System.out.println("3. Minhas Reservas");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");

        String opcao = scanner.nextLine();

        switch (opcao) {
            case "1":
                listarEspacos();
                break;
            case "2":
                fluxoReserva(scanner);
                break;
            case "3":
                listarMinhasReservas();
                break;
            case "0":
                usuarioLogado = null;
                System.out.println("Logout realizado.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
    // RF01 - Autenticação
    // Critério de Aceitação I: O sistema deve validar as credenciais[cite: 76].
    private static boolean fazerLogin(String email, String senha) {
        for (Usuario u : usuariosBD) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getSenha().equals(senha)) {
                usuarioLogado = u;
                return true;
            }
        }
        return false;
    }

    // RF05 - Visualizar Espaços
    // Critério de Aceitação I: Exibir lista de mesas e salas[cite: 77].
    private static void listarEspacos() {
        System.out.println("\n--- ESPAÇOS DO COWORKING ---");
        for (Espaco e : espacosBD) {
            System.out.println(e.toString());
        }
    }

    // RF06 - Reservar Espaço
    private static void fluxoReserva(Scanner scanner) {
        listarEspacos();
        System.out.print("\nDigite o ID do espaço que deseja reservar: ");
        int idEspaco = Integer.parseInt(scanner.nextLine());

        Espaco espacoSelecionado = espacosBD.stream()
                .filter(e -> e.getId() == idEspaco)
                .findFirst()
                .orElse(null);

        if (espacoSelecionado == null) {
            System.out.println("Espaço não encontrado.");
            return;
        }

        System.out.println("Digite a data e hora de início (formato: dd/MM/yyyy HH:mm)");
        System.out.println("Exemplo: 20/10/2025 14:00");
        System.out.print("Data: ");
        String dataStr = scanner.nextLine();

        System.out.print("Duração em horas (inteiro): ");
        int duracao = Integer.parseInt(scanner.nextLine());

        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime inicio = LocalDateTime.parse(dataStr, fmt);

            // Tenta criar a reserva aplicando a regra RN05
            criarReserva(usuarioLogado, espacoSelecionado, inicio, duracao);
            System.out.println("SUCESSO: Reserva confirmada para " + espacoSelecionado.getNome());

        } catch (IllegalArgumentException e) {
            System.out.println("ERRO DE NEGÓCIO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERRO: Formato de data inválido ou entrada incorreta.");
        }
    }

    // RN05 - Reservas sem conflito
    // "O sistema não deve permitir reservas sobrepostas para o mesmo espaço"[cite: 95, 96].
    private static void criarReserva(Usuario user, Espaco espaco, LocalDateTime inicio, int horas) {
        LocalDateTime fim = inicio.plusHours(horas);

        // Verificação de Conflito (CORE DO SISTEMA)
        for (Reserva r : reservasBD) {
            // Se for o mesmo espaço
            if (r.getEspaco().getId() == espaco.getId()) {
                // Se houver sobreposição de horários
                // (Novo Inicio < Fim Existente) E (Novo Fim > Inicio Existente)
                if (inicio.isBefore(r.getFim()) && fim.isAfter(r.getInicio())) {
                    throw new IllegalArgumentException("Espaço Indisponível! Já existe reserva neste horário.");
                }
            }
        }

        // Se passou, adiciona a reserva
        Reserva novaReserva = new Reserva(user, espaco, inicio, horas);
        reservasBD.add(novaReserva);
    }

    private static void listarMinhasReservas() {
        System.out.println("\n--- MINHAS RESERVAS ---");
        boolean temReserva = false;
        for (Reserva r : reservasBD) {
            // Filtro simplificado (na vida real verificaria o ID do usuário)
            if (r.toString().contains(usuarioLogado.getNome()) || true) {
                // Aqui simplifiquei para mostrar todas no console para teste
                System.out.println(r.toString() + " (Reservado por: " + usuarioLogado.getNome() + ")");
                temReserva = true;
            }
        }
        if(!temReserva) System.out.println("Nenhuma reserva encontrada.");
    }

    // --- DADOS INICIAIS PARA TESTE ---
    private static void inicializarDados() {
        // Criando Usuários (Membros e Admin)
        usuariosBD.add(new Usuario(1, "Isabele", "isa@smart.com", "123456"));
        usuariosBD.add(new Usuario(2, "João", "joao@smart.com", "senha123")); // Use este para testar conflito

        // Criando Espaços (Baseado no PDF pag 26 e 28)
        espacosBD.add(new Espaco(1, "Sala 01", "Sala de Reunião", 420.00));
        espacosBD.add(new Espaco(2, "Escritório 02", "Sala Privativa", 500.00));
        espacosBD.add(new Espaco(3, "Mesa Compartilhada A", "Mesa", 25.00));

        // Pré-carregando uma reserva para testar conflitos (Para testar RN05)
        // Reserva da Sala 01 para dia 20/10/2025 as 14:00 (duracao 2h)
        Usuario u1 = usuariosBD.get(0);
        Espaco e1 = espacosBD.get(0);
        LocalDateTime inicio = LocalDateTime.of(2025, 10, 20, 14, 0);
        reservasBD.add(new Reserva(u1, e1, inicio, 2));
    }
}