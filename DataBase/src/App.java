import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://db.znowbrlmqemdxotytadv.supabase.co:5432/postgres";
        //String url = "jdbc:postgresql://postgres:20102003@db.znowbrlmqemdxotytadv.supabase.co:5432/postgres";
        String username = "postgres";
        String password = "20102003";
        Connection con = DriverManager.getConnection(url, username, password);

        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(con);
        CrachaDAO crachaDAO = new CrachaDAO(con);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Adicionar Funcionário");
            System.out.println("2. Atualizar Funcionário");
            System.out.println("3. Listar Funcionários");
            System.out.println("4. Remover Funcionário");
            System.out.println("5. Criar Crachá");
            System.out.println("6. Atualizar Crachá");
            System.out.println("7. Listar Crachás");
            System.out.println("8. Remover Crachá");
            System.out.println("9. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 9) break;

            try {
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Cargo: ");
                        String cargo = scanner.nextLine();
                        funcionarioDAO.adicionarFuncionario(nome, cargo);
                    }
                    case 2 -> {
                        System.out.print("ID do Funcionário: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Novo Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Novo Cargo: ");
                        String cargo = scanner.nextLine();
                        funcionarioDAO.atualizarFuncionario(id, nome, cargo);
                    }
                    case 3 -> funcionarioDAO.listarFuncionarios();
                    case 4 -> {
                        System.out.print("ID do Funcionário: ");
                        int id = scanner.nextInt();
                        funcionarioDAO.removerFuncionario(id);
                    }
                    case 5 -> {
                        System.out.print("ID do Funcionário: ");
                        int funcionarioId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Código do Crachá: ");
                        String codigo = scanner.nextLine();
                        crachaDAO.criarCracha(funcionarioId, codigo);
                    }
                    case 6 -> {
                        System.out.print("ID do Funcionário: ");
                        int funcionarioId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Novo Código do Crachá: ");
                        String novoCodigo = scanner.nextLine();
                        crachaDAO.atualizarCracha(funcionarioId, novoCodigo);
                    }
                    case 7 -> crachaDAO.listarCrachas();
                    case 8 -> {
                        System.out.print("ID do Funcionário: ");
                        int funcionarioId = scanner.nextInt();
                        crachaDAO.removerCracha(funcionarioId);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
        con.close();
    }
}