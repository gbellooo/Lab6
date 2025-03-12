import java.sql.*;

public class FuncionarioDAO {
    private final Connection con;

    public FuncionarioDAO(Connection con) {
        this.con = con;
    }

    public void adicionarFuncionario(String nome, String cargo) throws SQLException {
        String sql = "INSERT INTO Funcionario (nome, cargo) VALUES (?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, nome);
        stmt.setString(2, cargo);
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            System.out.println("Funcionário inserido com sucesso! ID: " + rs.getInt(1));
        }
    }

    public void atualizarFuncionario(int id, String nome, String cargo) throws SQLException {
        String sql = "UPDATE Funcionario SET nome = ?, cargo = ? WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setString(2, cargo);
        stmt.setInt(3, id);
        int rowsUpdated = stmt.executeUpdate();
        System.out.println(rowsUpdated > 0 ? "Funcionário atualizado!" : "Funcionário não encontrado.");
    }

    public void listarFuncionarios() throws SQLException {
        String sql = "SELECT * FROM Funcionario";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + " | Nome: " + rs.getString("nome") + " | Cargo: " + rs.getString("cargo"));
        }
    }

    public void removerFuncionario(int id) throws SQLException {
        String sql = "DELETE FROM Funcionario WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        int rowsDeleted = stmt.executeUpdate();
        System.out.println(rowsDeleted > 0 ? "Funcionário removido!" : "Funcionário não encontrado.");
    }
}