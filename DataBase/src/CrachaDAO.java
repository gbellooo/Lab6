import java.sql.*;

public class CrachaDAO {
    private final Connection con;

    public CrachaDAO(Connection con) {
        this.con = con;
    }

    public void criarCracha(int funcionarioId, String codigo) throws SQLException {
        String sql = "INSERT INTO Cracha (funcionario_id, codigo) VALUES (?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, funcionarioId);
        stmt.setString(2, codigo);
        stmt.executeUpdate();
        System.out.println("Crachá cadastrado com sucesso!");
    }

    public void atualizarCracha(int funcionarioId, String novoCodigo) throws SQLException {
        String sql = "UPDATE Cracha SET codigo = ? WHERE funcionario_id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, novoCodigo);
        stmt.setInt(2, funcionarioId);
        int rowsUpdated = stmt.executeUpdate();
        System.out.println(rowsUpdated > 0 ? "Crachá atualizado!" : "Funcionário não possui crachá.");
    }

    public void listarCrachas() throws SQLException {
        String sql = "SELECT Cracha.codigo, Funcionario.nome FROM Cracha " +
                "JOIN Funcionario ON Cracha.funcionario_id = Funcionario.id";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println("Funcionário: " + rs.getString("nome") + " | Crachá: " + rs.getString("codigo"));
        }
    }

    public void removerCracha(int funcionarioId) throws SQLException {
        String sql = "DELETE FROM Cracha WHERE funcionario_id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, funcionarioId);
        int rowsDeleted = stmt.executeUpdate();
        System.out.println(rowsDeleted > 0 ? "Crachá removido!" : "Funcionário não possui crachá.");
    }
}