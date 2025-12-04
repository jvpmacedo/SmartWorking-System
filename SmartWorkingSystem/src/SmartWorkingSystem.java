// Arquivo: SmartWorkingSystem.java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SmartWorkingSystem {

    // Agora a lista aceita qualquer classe que herde de Usuario (Polimorfismo)
    private static List<Usuario> usuariosBD = new ArrayList<>();
    private static List<Espaco> espacosBD = new ArrayList<>();
    private static List<Reserva> reservasBD = new ArrayList<>();
    
    private static Usuario usuarioLogado = null;

    public static void main(String[] args) {
        inicializarDados(); 
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== SISTEMA SMARTWORKING ===");

        while (true) {
            if (usuarioLogado == null) {
                System.out.println("\n--- TELA INICIAL ---");
                System.out.println("1. Fazer Login");
                System.out.println("2. Cadastrar-se");
                System.out.println("0. Sair");
                System.out.print("Escolha: ");
                String opcao = scanner.nextLine();

                switch (opcao) {
                    case "1": exibirMenuLogin(scanner); break;
                    case "2": fluxoCadastro(scanner); break; // Novo fluxo
                    case "0": return;
                    default: System.out.println("Opção inválida.");
                }
            } else {
                exibirMenuPrincipal(scanner);
            }
        }
    }

    // --- CADASTRO INTELIGENTE (ATUALIZADO) ---
    private static void fluxoCadastro(Scanner scanner) {
        System.out.println("\n--- TIPO DE CADASTRO ---");
        System.out.println("1. Membro (Quero alugar espaços)");
        System.out.println("2. Administrador (Sou dono de um espaço)");
        System.out.print("Escolha: ");
        String tipo = scanner.nextLine();

        // Dados Comuns a todos
        System.out.print("Nome Completo: ");
        String nome = scanner.nextLine();
        
        System.out.print("E-mail (Login): ");
        String email = scanner.nextLine();

        // Validação de E-mail Único (RN01)
        for (Usuario u : usuariosBD) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                System.out.println("ERRO: Este e-mail já está em uso!");
                return;
            }
        }

        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        System.out.print("Telefone Celular: ");
        String telefone = scanner.nextLine();

        int novoId = usuariosBD.size() + 1;

        if (tipo.equals("1")) {
            // Cria Membro
            Membro novoMembro = new Membro(novoId, nome, email, senha, telefone);
            usuariosBD.add(novoMembro);
            System.out.println("SUCESSO: Conta de Membro criada!");

        } else if (tipo.equals("2")) {
            // Cria Administrador e pede dados extras
            System.out.println("\n--- DADOS DO SEU ESPAÇO/EMPRESA ---");
            System.out.print("Razão Social: ");
            String razao = scanner.nextLine();
            
            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();
            
            System.out.print("E-mail da Empresa: ");
            String emailEmp = scanner.nextLine();
            
            System.out.print("Endereço Completo: ");
            String endereco = scanner.nextLine();
            
            System.out.print("Descrição do Lugar: ");
            String descricao = scanner.nextLine();

            Administrador novoAdmin = new Administrador(novoId, nome, email, senha, telefone, 
                                                        razao, cnpj, emailEmp, endereco, descricao);
            
            // Simulação de adicionar fotos
            System.out.println("Deseja adicionar fotos agora? (S/N)");
            if (scanner.nextLine().equalsIgnoreCase("S")) {
                System.out.print("Digite o nome do arquivo da foto (ex: sala.jpg): ");
                novoAdmin.adicionarFoto(scanner.nextLine());
            }

            usuariosBD.add(novoAdmin);
            System.out.println("SUCESSO: Conta de Administrador criada!");
        } else {
            System.out.println("Opção inválida. Cadastro cancelado.");
        }
    }

    // --- MENUS EXISTENTES (Levemente ajustados) ---

    private static void exibirMenuLogin(Scanner scanner) {
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (fazerLogin(email, senha)) {
            System.out.println("Bem-vindo, " + usuarioLogado.getNome());
            // Verifica o tipo para dar boas vindas personalizadas
            if (usuarioLogado instanceof Administrador) {
                System.out.println("Painel de Gestão do Proprietário: " + ((Administrador) usuarioLogado).getNomeEmpresa());
            }
        } else {
            System.out.println("ERRO: Credenciais inválidas.");
        }
    }

    private static void exibirMenuPrincipal(Scanner scanner) {
        System.out.println("\n--- MENU ---");
        
        // Menu dinâmico dependendo de quem logou
        if (usuarioLogado instanceof Membro) {
            System.out.println("1. Visualizar Espaços");
            System.out.println("2. Fazer Reserva");
            System.out.println("3. Minhas Reservas");
        } else if (usuarioLogado instanceof Administrador) {
            System.out.println("1. Visualizar Meus Espaços");
            System.out.println("2. Ver Reservas no meu Local (Relatório)");
            System.out.println("3. Ver Detalhes da Minha Empresa");
        }
        
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
        
        String opcao = scanner.nextLine();

        if (opcao.equals("0")) {
            usuarioLogado = null;
            return;
        }

        // Lógica simples para os testes funcionais anteriores continuarem funcionando para Membros
        if (usuarioLogado instanceof Membro) {
            switch (opcao) {
                case "1": listarEspacos(); break;
                case "2": fluxoReserva(scanner); break;
                case "3": listarMinhasReservas(); break;
                default: System.out.println("Opção inválida");
            }
        } else if (usuarioLogado instanceof Administrador) {
             switch (opcao) {
                case "1": listarEspacos(); break; // Admin vê tudo por enquanto
                case "2": listarMinhasReservas(); break; // Reutilizando logica para teste
                case "3": ((Administrador) usuarioLogado).exibirDetalhesEmpresa(); break;
                default: System.out.println("Opção inválida");
            }
        }
    }

    // --- MÉTODOS AUXILIARES (Login, Reserva, Listar) ---
    // (Mantenha os métodos fazerLogin, listarEspacos, fluxoReserva, criarReserva iguais aos anteriores,
    // apenas lembrando que agora eles usam a lista de Usuarios que contém Membros e Admins)

    private static boolean fazerLogin(String email, String senha) {
        for (Usuario u : usuariosBD) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getSenha().equals(senha)) {
                usuarioLogado = u;
                return true;
            }
        }
        return false;
    }
    
    private static void listarEspacos() {
        System.out.println("\n--- ESPAÇOS DISPONÍVEIS ---");
        for (Espaco e : espacosBD) {
            System.out.println(e.toString());
        }
    }

    private static void fluxoReserva(Scanner scanner) {
        // (Copie o código do fluxoReserva que te mandei antes aqui)
        // Por brevidade, mantive a mesma lógica:
        listarEspacos();
        System.out.print("\nID do espaço: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Espaco espaco = espacosBD.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
            if(espaco == null) { System.out.println("Não encontrado"); return; }
            
            System.out.print("Data (dd/MM/yyyy HH:mm): ");
            String dataStr = scanner.nextLine();
            System.out.print("Duração (horas): ");
            int duracao = Integer.parseInt(scanner.nextLine());
            
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime inicio = LocalDateTime.parse(dataStr, fmt);
            
            criarReserva(usuarioLogado, espaco, inicio, duracao);
            System.out.println("Reserva Confirmada!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void criarReserva(Usuario user, Espaco espaco, LocalDateTime inicio, int horas) {
        LocalDateTime fim = inicio.plusHours(horas);
        for (Reserva r : reservasBD) {
            if (r.getEspaco().getId() == espaco.getId()) {
                if (inicio.isBefore(r.getFim()) && fim.isAfter(r.getInicio())) {
                    throw new IllegalArgumentException("Conflito de horário!");
                }
            }
        }
        reservasBD.add(new Reserva(user, espaco, inicio, horas));
    }

    private static void listarMinhasReservas() {
        System.out.println("--- RESERVAS ---");
        for(Reserva r : reservasBD) System.out.println(r.toString());
    }

    // --- DADOS INICIAIS (ATUALIZADO PARA USAR NOVAS CLASSES) ---
    private static void inicializarDados() {
        // Criando um Membro de teste
        usuariosBD.add(new Membro(1, "Isabele", "isa@smart.com", "123456", "9999-8888"));
        
        // Criando um Admin de teste
        Administrador admin = new Administrador(2, "Carlos Dono", "carlos@admin.com", "admin123", "9888-7777",
                "Coworking Top Ltda", "12.345.678/0001-99", "contato@coworkingtop.com", 
                "Av Rio Branco, 100", "Melhor espaço do Rio");
        admin.adicionarFoto("fachada.jpg");
        usuariosBD.add(admin);

        // Espaços
        espacosBD.add(new Espaco(1, "Sala 01", "Reunião", 420.00));
        espacosBD.add(new Espaco(2, "Mesa A", "Compartilhada", 25.00));
    }
}
